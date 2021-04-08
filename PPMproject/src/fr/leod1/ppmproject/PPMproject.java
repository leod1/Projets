package fr.leod1.ppmproject;

import com.google.gson.Gson;
import fr.leod1.ppmproject.DB.fileutils.fileUtils;
import fr.leod1.ppmproject.DB.projetJsonmanager.ProjectSerializationManager;
import fr.leod1.ppmproject.event.*;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMCromas;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMcreatorProject;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import fr.leod1.ppmproject.ppmunderproject.PPMUnderProject;
import net.minecraft.server.v1_12_R1.WorldServer;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class PPMproject extends JavaPlugin {


    public static PPMproject InstanceOfMain;
    private HashMap<Player, PPMcreatorProject> cacheProject;
    private HashMap<Player, PPMDataPlayer> PlayerData;
    private ArrayList<PPMprojects> Projects;
    private ProjectSerializationManager projectSerializationManager;

    @Override
    public void onEnable() {
        this.InstanceOfMain = this;
        cacheProject = new HashMap<>();
        Projects = new ArrayList<>();
        projectSerializationManager = new ProjectSerializationManager();
        PlayerData = new HashMap<>();

        getCommand("project").setExecutor(new ComandesEx());
        Bukkit.getPluginManager().registerEvents(new EventJoin(), this);
        Bukkit.getPluginManager().registerEvents(new EventChat(), this);
        Bukkit.getPluginManager().registerEvents(new EventLeave(), this);
        Bukkit.getPluginManager().registerEvents(new EventCloseInventory(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryEvent(), this);

        //create player data
        File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder().getPath()+"/PlayerData/");
        DirectoryPlayerData.mkdirs();
        //create player data


        //init player data
        for(Player pl : Bukkit.getOnlinePlayers()){
            InstanceOfMain.addCacheOnjoin(pl, new PPMcreatorProject(PPMCromas.smooth));

            //
            File DirectoryPlayerDat = new File(InstanceOfMain.getDataFolder()+"/PlayerData/");
            final File file = new File(DirectoryPlayerDat,pl.getName()+".json");
            PPMDataPlayer json = null;
            try {
                json = InstanceOfMain.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fileUtils.createFile(file)){
                    InstanceOfMain.addDataPlayer(pl,new PPMDataPlayer());
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            InstanceOfMain.addDataPlayer(pl,json);
        }
        //init player data


        //innite project
        File Directory = new File(InstanceOfMain.getDataFolder(), "/projects/");

        if (Directory.exists()){
            for (File fileEntry : Objects.requireNonNull(Directory.listFiles())) {
                try {
                    PPMprojects ahouia = projectSerializationManager.deserializePPMprojects(fileUtils.loadContent(fileEntry));
                    if (ahouia.getUnderProject() == null){
                        ahouia.setUnderProject(new ArrayList<>());
                        ahouia.addUnderProject(new PPMUnderProject(ahouia.getSpawnOfProject(),"Main"));
                    } else if (ahouia.getUnderProject().size() == 0){
                        ahouia.addUnderProject(new PPMUnderProject(ahouia.getSpawnOfProject(),"Main"));
                    }
                    Projects.add(ahouia);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //innite project

    }

    @Override
    public void onDisable() {
        File DirectoryProj = new File(InstanceOfMain.getDataFolder(),"/projects/");

        DirectoryProj.mkdirs();

        for(PPMprojects pp : Projects){
            final File file = new File(DirectoryProj,pp.getNameOfProject()+".json");
            String json = projectSerializationManager.serializePPMproject(pp);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileUtils.save(file,json);

        }

        for (Player pl : Bukkit.getOnlinePlayers()){
            //
            File DirectoryPlayerData = new File(InstanceOfMain.getDataFolder(),"/PlayerData/");
            final File file = new File(DirectoryPlayerData,pl.getName()+".json");
            try {
                fileUtils.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PPMDataPlayer zebi = InstanceOfMain.getDataPlayer(pl);
            zebi.sortrmproject();
            String json = InstanceOfMain.getProjectSerializationManager().serializePlayerData(zebi);
            fileUtils.save(file,json);
            //
        }
    }

    public ArrayList<PPMprojects> getPPMProjectbyPlayerFavori(ArrayList<String> list){
        ArrayList<PPMprojects> zebi = new ArrayList<>();
        for (String st: list){
            zebi.add(getProjectsByName(st));
        }
        return zebi;
    }

    public PPMDataPlayer getDataPlayer(Player pl){
        return PlayerData.get(pl);
    }

    public void addDataPlayer(Player pl, PPMDataPlayer plData){
        PlayerData.put(pl,plData);
    }

    public  void removeDataPlayer(Player pl){
        PlayerData.remove(pl);
    }



    public void ClearCacheOfPlayer(Player pl){
        rmCacheOnleave(pl);
        addCacheOnjoin(pl,new PPMcreatorProject(PPMCromas.smooth));
    }

    public void addCacheOnjoin(Player pl, PPMcreatorProject pr){
        this.cacheProject.put(pl, pr);
    }
    public void rmCacheOnleave(Player pl){
        this.cacheProject.remove(pl);
    }
    public PPMcreatorProject getPPMcreator(Player pl){
        return cacheProject.get(pl);
    }
    public HashMap getHashmap(){
        return cacheProject;
    }

    public void AddProjets(PPMcreatorProject projets, Player pl){
        Date date = new Date();
        PPMprojects endProj = new PPMprojects(date, projets.getMotivationOfPproject(), projets.getTypeOfproject(), projets.getNameOfProject(), projets.getSpawnOfProject(), pl.getName(), projets.getNamesOfMenber(), projets.getItemviews(), new ArrayList<PPMUnderProject>());
        Projects.add(endProj);
    }
    public void rmProjets(PPMprojects projets){
        Projects.remove(projets);
    }
    public void addProjets(PPMprojects projets){
        Projects.add(projets);
    }

    public ArrayList<PPMprojects> getProjects() {
        return Projects;
    }

    public boolean containProjectbyName(String name){
        boolean test = false;
        for (PPMprojects zebi : Projects){
            if (zebi.getNameOfProject().equalsIgnoreCase(name)){
                test = true;
            }
        }
        return test;
    }

    public PPMprojects getProjectsByName(String name){
        for (PPMprojects zebi : Projects){
            if (zebi.getNameOfProject().equalsIgnoreCase(name)){
                return zebi;
            }
        }
        return null;
    }

    public ProjectSerializationManager getProjectSerializationManager() {
        return projectSerializationManager;
    }

    public HashMap<Player, PPMDataPlayer> getPlayerData() {
        return PlayerData;
    }
}
