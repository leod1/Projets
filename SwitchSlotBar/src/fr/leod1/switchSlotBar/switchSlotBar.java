package fr.leod1.switchSlotBar;

import fr.leod1.switchSlotBar.Db.ProjectSerializationManager;
import fr.leod1.switchSlotBar.Db.fileUtils;
import fr.leod1.switchSlotBar.events.SwitchItem;
import fr.leod1.switchSlotBar.events.eventJoin;
import fr.leod1.switchSlotBar.events.eventLeave;
import fr.leod1.switchSlotBar.stockageSlots.SlotsBar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class switchSlotBar extends JavaPlugin {

    public HashMap<Player, SlotsBar> playerCaches;
    public static switchSlotBar InstanceOfMain;
    private ProjectSerializationManager projectSerializationManager;

    @Override
    public void onEnable() {
        projectSerializationManager = new ProjectSerializationManager();
        InstanceOfMain = this;
        playerCaches = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(new SwitchItem(), this);
        Bukkit.getPluginManager().registerEvents(new eventJoin(), this);
        Bukkit.getPluginManager().registerEvents(new eventLeave(), this);

        for (Player pl : Bukkit.getOnlinePlayers()){
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

        /*

        File Directory = new File(InstanceOfMain.getDataFolder(), "/PlayerData/");

        if (Directory.exists()) {
            for (File fileEntry : Objects.requireNonNull(Directory.listFiles())) {
                try {
                    SlotsBar ahouia = projectSerializationManager.deserializePlayerData(fileUtils.loadContent(fileEntry));

                    if (ahouia.getSlotBar1() == null) {
                        ahouia.setSlotBar1(new ArrayList<>());
                    }
                    if (ahouia.getSlotBar2() == null) {
                        ahouia.setSlotBar2(new ArrayList<>());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Directory.mkdirs();
        } */


    }

    @Override
    public void onDisable() {

        File DirectoryProj = new File(InstanceOfMain.getDataFolder(), "/PlayerData/");

        DirectoryProj.mkdirs();


        for (Player pl : Bukkit.getOnlinePlayers()){
            //
            File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder(),"/PlayerData/");
            final File file = new File(DirectoryPlayerData,pl.getName()+".json");
            try {
                fileUtils.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String json = projectSerializationManager.serializePlayerData(playerCaches.get(pl));
            fileUtils.save(file,json);

        }
    }


    public ProjectSerializationManager getProjectSerializationManager() {
        return projectSerializationManager;
    }

    public void setProjectSerializationManager(ProjectSerializationManager projectSerializationManager) {
        this.projectSerializationManager = projectSerializationManager;
    }
}
