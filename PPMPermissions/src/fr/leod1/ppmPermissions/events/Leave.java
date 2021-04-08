package fr.leod1.ppmPermissions.events;

import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.Utils.fileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class Leave implements Listener {

    @EventHandler
    public void leave(PlayerQuitEvent event) throws IOException {
        Player pl = event.getPlayer();
        plugin.playerDataCache.remove(pl);

        //Unload playerdata
        File DirectoryPlayerData = new File(plugin.getDataFolder()+"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        fileUtils.createFile(file);
        PlayerData zebi = plugin.playerData.get(pl);
        String json = plugin.getProjectSerializationManager().serializePlayerData(zebi);
        fileUtils.save(file,json);
        plugin.scoreboard.getTeam(pl.getName()).unregister();
        //Unload playerdata

    }
}
