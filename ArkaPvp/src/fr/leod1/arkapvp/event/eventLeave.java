package fr.leod1.arkapvp.event;

import fr.leod1.arkapvp.arkapvpgames.ArkaPvpGames;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static fr.leod1.arkapvp.ArkaPvp.InstanceOfMain;

public class eventLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        ArkaPvpGames Games = InstanceOfMain.arkaPvpGames;
        Player pl = e.getPlayer();
        e.setQuitMessage("");

        if (Games.getAllPlayerInPart().contains(pl)){
            Games.rmPlayerInPart(pl);
            Bukkit.broadcastMessage("[ArkaPvp] Le joueur "+pl.getName()+" viens de quitter la partie ("+Games.getAllPlayerInPart().size()+ "/"+ Games.getMaxPlayerInPart() + ")");
        }
    }
}
