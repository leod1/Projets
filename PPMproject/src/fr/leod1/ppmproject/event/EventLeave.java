package fr.leod1.ppmproject.event;

import fr.leod1.ppmproject.DB.fileutils.fileUtils;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class EventLeave implements Listener {
    @EventHandler
    public void onLeave(PlayerQuitEvent e) throws IOException {
        Player pl = e.getPlayer();
        InstanceOfMain.rmCacheOnleave(pl);

        //
        File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder(),"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        fileUtils.createFile(file);
        PPMDataPlayer zebi = InstanceOfMain.getDataPlayer(pl);
        String json = InstanceOfMain.getProjectSerializationManager().serializePlayerData(zebi);
        fileUtils.save(file,json);
        //
    }
}
