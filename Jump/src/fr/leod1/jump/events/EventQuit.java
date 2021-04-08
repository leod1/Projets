package fr.leod1.jump.events;

import fr.leod1.jump.Jump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if (Jump.playerJumpManager.isOnJump(player.getName())) {
            Jump.playerJumpManager.cancelPlayerjumps(player);
        }

    }

}
