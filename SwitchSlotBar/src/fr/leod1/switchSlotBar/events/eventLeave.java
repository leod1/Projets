package fr.leod1.switchSlotBar.events;

import fr.leod1.switchSlotBar.Db.fileUtils;
import fr.leod1.switchSlotBar.stockageSlots.SlotsBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;

import static fr.leod1.switchSlotBar.switchSlotBar.InstanceOfMain;

public class eventLeave implements Listener {

    @EventHandler
    public void fqsd(PlayerQuitEvent e){
        Player pl = e.getPlayer();

        //
        File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder(),"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        try {
            fileUtils.createFile(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        SlotsBar zebi = InstanceOfMain.playerCaches.get(pl);
        String json = InstanceOfMain.getProjectSerializationManager().serializePlayerData(zebi);
        fileUtils.save(file,json);
    }
}
