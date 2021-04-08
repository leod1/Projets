package fr.leod1.jump.events;


import fr.leod1.jump.Jump;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventDie implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player player = e.getEntity();

        if (Jump.playerJumpManager.isOnJump(player.getName())) {
            Jump.playerJumpManager.cancelPlayerjumps(player);
        }

    }

}
