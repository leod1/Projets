package fr.leod1.QbTeleporte.QbEvents;

import fr.leod1.QbTeleporte.QbPlayer.QbPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class JoinEvent implements Listener {

    @EventHandler
    public void zebi(PlayerJoinEvent e){
        Player pl = e.getPlayer();
        InstanceOfMain.playerCaches.put(pl,new QbPlayer());
        InstanceOfMain.playerCaches.get(pl).setPage(0);

    }
}
