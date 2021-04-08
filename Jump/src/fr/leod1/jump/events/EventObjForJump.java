package fr.leod1.jump.events;

import fr.leod1.jump.Jump;
import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.playerjump.PlayerJump;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventObjForJump implements Listener {

    @EventHandler
    public void Onrightclick(PlayerInteractEvent e){
        Player pl = e.getPlayer();
        Action acc = e.getAction();
        if (Jump.playerJumpManager.isOnJump(pl.getName())) {
            if (acc == Action.RIGHT_CLICK_AIR || acc == Action.RIGHT_CLICK_BLOCK) {
                if (pl.getItemInHand().getType() == Material.SLIME_BALL) {
                    if (pl.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Jump.jump.getConfig().getString("Objects.Slime-ball").replace("&", "§"))) {
                        //TP player last cp
                        PlayerJump pljump = Jump.playerJumpManager.getPlayerJump(pl.getName());
                        JumpObj jumpObj = Jump.jumpsManagers.getJumpObj(Jump.playerJumpManager.getPlayerJump(pl.getName()).getJump());
                        Location location = pljump.getLocationOflastCP(jumpObj);
                        location.setPitch(pl.getLocation().getPitch());
                        location.setYaw(pl.getLocation().getYaw());
                        pl.teleport(location);
                    }

                } else if (pl.getItemInHand().getType() == Material.BARRIER) {
                    if (pl.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Jump.jump.getConfig().getString("Objects.Barrier").replace("&", "§"))) {
                        if (Jump.playerJumpManager.isOnJump(pl.getName())) {
                            e.setCancelled(true);
                            Jump.playerJumpManager.cancelPlayerjumps(pl);
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void dropItem(PlayerDropItemEvent e){
        Player pl = e.getPlayer();
        if (Jump.playerJumpManager.isOnJump(pl.getName())){
            if(e.getItemDrop().getItemStack().getType() == Material.SLIME_BALL){
                if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(Jump.jump.getConfig().getString("Objects.Slime-ball").replace("&","§"))){
                    e.setCancelled(true);
                }

            } else if(e.getItemDrop().getItemStack().getType() == Material.BARRIER){
                if(e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(Jump.jump.getConfig().getString("Objects.Barrier").replace("&","§"))){
                    if (Jump.playerJumpManager.isOnJump(pl.getName())) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void ClickEnvent (InventoryClickEvent e){
        Player pl = (Player) e.getWhoClicked();

        if (Jump.playerJumpManager.isOnJump(pl.getName())){
            if(e.getCurrentItem().getType() == Material.SLIME_BALL){
                e.setCancelled(true);
            } else if(e.getCurrentItem().getType() ==  Material.BARRIER){
                e.setCancelled(true);
                }
            }
        }
    }


