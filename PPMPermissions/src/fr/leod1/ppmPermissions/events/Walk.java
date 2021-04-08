package fr.leod1.ppmPermissions.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

import java.io.IOException;

public class Walk implements Listener {

    @EventHandler
    public void move(PlayerMoveEvent e){
        Player pl = e.getPlayer();
        plugin.sendTablist(pl);
    }
}
