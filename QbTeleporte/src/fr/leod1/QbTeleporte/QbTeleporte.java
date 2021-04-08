package fr.leod1.QbTeleporte;

import fr.leod1.QbTeleporte.QbEvents.InteractInvEvent;
import fr.leod1.QbTeleporte.QbEvents.JoinEvent;
import fr.leod1.QbTeleporte.QbEvents.chatEvent;
import fr.leod1.QbTeleporte.QbPlayer.QbPlayer;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import fr.leod1.QbTeleporte.Utils.Stockage.ProjectSerializationManager;
import fr.leod1.QbTeleporte.Utils.Stockage.fileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class QbTeleporte extends JavaPlugin {

    public HashMap<Player, QbPlayer> playerCaches;
    public static QbTeleporte InstanceOfMain;
    public ArrayList<QbProjects> Projects;
    private ProjectSerializationManager projectSerializationManager;

    @Override
    public void onEnable() {
        getCommand("qbc").setExecutor(new CommandesEx());
        getCommand("qbl").setExecutor(new CommandesEx());
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new chatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InteractInvEvent(), this);

        this.InstanceOfMain = this;
        playerCaches = new HashMap<>();
        Projects = new ArrayList<>();
        projectSerializationManager = new ProjectSerializationManager();

        File Directory = new File(InstanceOfMain.getDataFolder(), "/projects/");

        if (Directory.exists()) {
            for (File fileEntry : Objects.requireNonNull(Directory.listFiles())) {
                try {
                    QbProjects ahouia = projectSerializationManager.deserializePPMprojects(fileUtils.loadContent(fileEntry));
                    if (ahouia.getUnderQbProjet() == null) {
                        ahouia.setUnderQbProjet(new ArrayList<>());
                    }
                    Projects.add(ahouia);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Directory.mkdirs();
        }


        for (Player pl : Bukkit.getOnlinePlayers()) {
            playerCaches.put(pl, new QbPlayer());
        }

    }

    @Override
    public void onDisable() {
        save();
    }


    public void save(){
        File DirectoryProj = new File(InstanceOfMain.getDataFolder(), "/projects/");

        DirectoryProj.mkdirs();

        for (QbProjects pp : Projects) {
            final File file = new File(DirectoryProj, pp.getName() + ".json");
            String json = projectSerializationManager.serializePPMproject(pp);
            fileUtils.save(file, json);

        }
    }

    public QbProjects getProjectsByName(String name){
        for (QbProjects zebi : Projects){
            if (zebi.getName().equalsIgnoreCase(name)){
                return zebi;
            }
        }
        return null;
    }

    public boolean containProjectbyName(String name){
        for (QbProjects zebi : Projects){
            if (zebi.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
