package fr.leod1.schemAchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SchemAchat extends JavaPlugin {

    public static SchemAchat InstanceMain;


    @Override
    public void onEnable() {

        InstanceMain = this;
        Bukkit.getPluginManager().registerEvents(new Test(), this);
    }

    @Override
    public void onDisable() {
        //ok
    }

}
