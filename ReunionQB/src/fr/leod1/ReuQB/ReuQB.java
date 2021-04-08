package fr.leod1.ReuQB;

import fr.leod1.ReuQB.Event.ClickInv;
import fr.leod1.ReuQB.ReuGroups.reuGroups;
import fr.leod1.ReuQB.ReuParticipant.reuParticipant;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class ReuQB extends JavaPlugin {

    public static ReuQB InstanceP;
    public ArrayList<reuParticipant> playerDATA;
    public ArrayList<reuGroups> Group;

    @Override
    public void onEnable() {
        playerDATA = new ArrayList<>();
        Group = new ArrayList<>();
        InstanceP = this;

        getCommand("reunion").setExecutor(new CommandeEX());
        Bukkit.getPluginManager().registerEvents(new ClickInv(), this);
    }





    public reuParticipant getPlayerDATA(String NamePlayer){
        for (reuParticipant pdt: playerDATA){
            if(pdt.getName().equalsIgnoreCase(NamePlayer)){
                return pdt;
            }
        }
        return null;
    }

    public boolean IsinData(String NamePlayer){
        for (reuParticipant pdt: playerDATA){
            if(pdt.getName().equalsIgnoreCase(NamePlayer)){
                return true;
            }
        }
        return false;
    }


    @Override
    public void onDisable() {

    }
}
