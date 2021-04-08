package fr.leod1.jump.events;

import fr.leod1.jump.Jump;
import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.Utils.JumpLoc;
import fr.leod1.jump.playerjump.PlayerJump;
import fr.leod1.jump.score.BestTimeScore;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;

import static fr.leod1.jump.Jump.*;

public class EventMove implements Listener {

    @EventHandler

    public void onPlayerMove(PlayerMoveEvent e) throws SQLException {

        Player pl = e.getPlayer();
        //pl.sendMessage(jump.getConfig().getString("messages.test").replace("&","§").replace("%player%",pl.getName()));
        if (Jump.playerJumpManager.isOnJump(pl.getName())) {
            pl.setGameMode(GameMode.SURVIVAL);
            pl.setFlying(false);
        }
        if (Jump.playerJumpManager.isOnJump(pl.getName())) {
            JumpObj jumpObj = Jump.jumpsManagers.getJumpObj(Jump.playerJumpManager.getPlayerJump(pl.getName()).getJump());
            if(jumpObj.getCheckPoints().size() != 0) {
                int i = 0;
                for (JumpLoc Jumploc : jumpObj.getCheckPoints()) {
                    if (pl.getLocation().getWorld().getName().equals(Jumploc.getWorldName())
                            && pl.getLocation().getBlockX() == Jumploc.getX()
                            && pl.getLocation().getBlockY() == Jumploc.getY()
                            && pl.getLocation().getBlockZ() == Jumploc.getZ()) {
                        if (Jump.playerJumpManager.getPlayerJump(pl.getName()).getCheckPoint() == i - 1){
                            PlayerJump pljump = Jump.playerJumpManager.getPlayerJump(pl.getName());
                            pljump.setCheckPoint(pljump.getCheckPoint() + 1);
                            String tkt = String.valueOf((i+1));
                            pl.sendMessage(jump.getConfig().getString("messages.Jump-On-Cp").replace("&","§").replace("%NumberCP%", tkt));
                            //pl.sendMessage("§a[§bJump§a] §5Checkpoint §r"+(i+1));
                        }
                        }
                    i++;
                }
            }
        }

        if(Jump.playerJumpManager.isOnJump(pl.getName())) {
            JumpObj jumpObj = Jump.jumpsManagers.getJumpObj(Jump.playerJumpManager.getPlayerJump(pl.getName()).getJump());
            if (pl.getVelocity().getY() < -1.2 || pl.getLocation().getY() < 0) {
                /** Fall tp au checkpoint**/
                PlayerJump pljump = Jump.playerJumpManager.getPlayerJump(pl.getName());
                Location location = pljump.getLocationOflastCP(jumpObj);
                location.setPitch(pl.getLocation().getPitch());
                location.setYaw(pl.getLocation().getYaw());
                pl.teleport(location);
            }
        }

        if (Jump.playerJumpManager.isOnJump(pl.getName())) {
            JumpObj jumpObj = Jump.jumpsManagers.getJumpObj(Jump.playerJumpManager.getPlayerJump(pl.getName()).getJump());

            if (jumpObj.getFinish() == null) return;
            if (pl.getLocation().getWorld().getName().equals(jumpObj.getFinish().getWorldName())
                    && pl.getLocation().getBlockX() == jumpObj.getFinish().getX()
                    && pl.getLocation().getBlockY() == jumpObj.getFinish().getY()
                    && pl.getLocation().getBlockZ() == jumpObj.getFinish().getZ()) {
                if(Jump.playerJumpManager.getPlayerJump(pl.getName()).getCheckPoint() + 1 == jumpObj.getCheckPoints().size()){
                    int time = (int) ((System.currentTimeMillis() - Jump.playerJumpManager.getPlayerJump(pl.getName()).getTimeStart()) / 1000);
                    jumpObj.addBestScores(new BestTimeScore(pl.getName(),System.currentTimeMillis() - Jump.playerJumpManager.getPlayerJump(pl.getName()).getTimeStart(),jumpObj.getName()));
                    Jump.playerJumpManager.finishPlayerjumps(pl);
                    int m = Math.floorDiv(time, 60);
                    int s = Math.floorMod(time, 60);
                    String times = m + ":" + s;
                    pl.sendMessage(jump.getConfig().getString("messages.Jump-end").replace("&","§").replace("%Jump-Name%", jumpObj.getName()).replace("%Time%", times));
                    //pl.sendMessage("§a[§bJump§a] §5Vous venez de finir la course §r" + jumpObj.getName() + "§5 en" + "§a§l " + m + ":" + s);
                } else {
                    pl.sendMessage(jump.getConfig().getString("messages.Not-All-Cp"));
                }

            }

        } else {

            JumpObj jumpObj = SQLite.getJumpFromStartBlock(pl);
            if (jumpObj != null) {
                Jump.playerJumpManager.addPlayerjumps(pl, jumpObj);
                pl.sendMessage(jump.getConfig().getString("messages.Jump-start").replace("&","§").replace("%Jump-Name%",jumpObj.getName()));
                //pl.sendMessage("§a[§bJump§a] §5Vous venez de commencer la course §r" + jumpObj.getName() + "§5.");

                new BukkitRunnable(){
                    @Override
                    public void run() {

                        if (!Jump.playerJumpManager.isOnJump(pl.getName())){
                            cancel();
                        } else {
                            int time = (int) ((System.currentTimeMillis() - Jump.playerJumpManager.getPlayerJump(pl.getName()).getTimeStart()) / 1000);
                            int m = Math.floorDiv(time, 60);
                            int s = Math.floorMod(time, 60);
                            title.sendActionBar(pl,"§a§l"+ m +":"+ s );
                        }
                    }
                }.runTaskTimer(jump.getInstance(),0,20);
            }

        }
    }
}

