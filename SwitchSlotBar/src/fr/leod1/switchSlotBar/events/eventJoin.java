package fr.leod1.switchSlotBar.events;

import fr.leod1.switchSlotBar.Db.fileUtils;
import fr.leod1.switchSlotBar.stockageSlots.SlotsBar;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static fr.leod1.switchSlotBar.switchSlotBar.InstanceOfMain;

public class eventJoin implements Listener {

    @EventHandler
    public void erv(PlayerJoinEvent e){

        Player pl = e.getPlayer();

        File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder()+"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        SlotsBar json = null;


        try {
            json = InstanceOfMain.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (!(file.exists()) || json == null){
            ArrayList<ItemStack> qzd = new ArrayList<>();
            for (int i = 0; i < 9;i++){
                if (pl.getInventory().getItem(i) == null){
                    qzd.add(new ItemStack(Material.AIR));
                } else {
                    qzd.add(pl.getInventory().getItem(i));
                }
            }

            InstanceOfMain.playerCaches.put(pl,new SlotsBar(true,qzd));
            return;
        }

        InstanceOfMain.playerCaches.put(pl, json);
    }
}
