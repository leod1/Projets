package fr.leod1.jump.playerjump;

import fr.leod1.jump.Jump;
import fr.leod1.jump.Jumps.JumpObj;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class PlayerJumpManager {

    // HashMap<Nom du joueur, PlayerJump>

    private HashMap<String, PlayerJump> playerjumps = new HashMap<>();

    public HashMap<String, PlayerJump> getPlayerjumps() {
        return playerjumps;
    }

    public void addPlayerjumps(String playerName, PlayerJump playerJump) {
        playerjumps.put(playerName, playerJump);
    }

    public void addPlayerjumps(Player player, JumpObj jumpObj) {

        playerjumps.put(player.getName(), new PlayerJump(jumpObj.getName(), player.getInventory().getContents()));
        player.getInventory().clear();

        ItemStack slime = new ItemStack(Material.SLIME_BALL);
        ItemMeta MetaSlime = slime.getItemMeta();
        MetaSlime.setDisplayName(Jump.jump.getConfig().getString("Objects.Slime-ball").replace("&","§"));
        slime.setItemMeta(MetaSlime);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta Metabarrier = barrier.getItemMeta();
        Metabarrier.setDisplayName(Jump.jump.getConfig().getString("Objects.Barrier").replace("&","§"));
        barrier.setItemMeta(Metabarrier);

        player.getInventory().setItem(0,slime);
        player.getInventory().setItem(4,barrier);
        player.updateInventory();
    }

    public void finishPlayerjumps(Player player) {
        ItemStack[] item = Jump.playerJumpManager.getPlayerJump(player.getName()).getInventory();

        if(!(item == null)){
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null) {
                    player.getInventory().setItem(i, item[i]);
                } else {
                    player.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }
        }
        player.updateInventory();
        playerjumps.remove(player.getName());


    }

    public void cancelPlayerjumps(Player player) {
        JumpObj jump = Jump.jumpsManagers.getJumpObj(Jump.playerJumpManager.getPlayerJump(player.getName()).getJump());

        ItemStack[] item = Jump.playerJumpManager.getPlayerJump(player.getName()).getInventory();
        for (int i = 0; i < item.length; i++) {
            if (item[i] != null) {
                player.getInventory().setItem(i, item[i]);
            } else {
                player.getInventory().setItem(i, new ItemStack(Material.AIR));
            }
        }
        player.updateInventory();
        playerjumps.remove(player.getName());
        player.sendMessage("§a[§bJump§a] §5Vous venez de quitter la course §r");
        //player.teleport(new Location(Bukkit.getWorld(jump.getStart().getWorldName()), jump.getStart().getX(),jump.getStart().getY(),jump.getStart().getZ()));

    }

    public void cancelAlljumps() {

        for (String string : playerjumps.keySet()) {
            Player player = Bukkit.getPlayerExact(string);

            ItemStack[] item = Jump.playerJumpManager.getPlayerJump(player.getName()).getInventory();
            for (int i = 0; i < item.length; i++) {
                if (item[i] != null) {
                    player.getInventory().setItem(i, item[i]);
                } else {
                    player.getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            }
            player.updateInventory();
            player.sendMessage("§a[§bJump§a] §5Vous venez de quitter la course §r");
        }

        playerjumps.clear();

    }

    public PlayerJump getPlayerJump(String name) {
        return playerjumps.get(name);
    }

    public boolean isOnJump(String name) {
        if (playerjumps.containsKey(name)) return true;
        return false;
    }

}


