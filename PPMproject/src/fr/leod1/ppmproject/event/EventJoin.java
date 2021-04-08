package fr.leod1.ppmproject.event;

import fr.leod1.ppmproject.DB.fileutils.fileUtils;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMCromas;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMcreatorProject;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class EventJoin implements Listener {


    @EventHandler
    public void joinevent(PlayerJoinEvent e) throws IOException {
        Player pl = e.getPlayer();
        InstanceOfMain.addCacheOnjoin(pl, new PPMcreatorProject(PPMCromas.smooth));


        //
        File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder()+"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        PPMDataPlayer json = InstanceOfMain.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));

        if (!(file.exists())){
            InstanceOfMain.addDataPlayer(pl,new PPMDataPlayer());
            return;
        }
        json.sortrmproject();
        InstanceOfMain.addDataPlayer(pl,json);
        //
    }
}
