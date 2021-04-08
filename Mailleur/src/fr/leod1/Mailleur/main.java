package fr.leod1.Mailleur;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    main InstanceOfmain;
    Bot zebi;

    @Override
    public void onEnable() {
        /*InstanceOfmain = this;
        for(Player pl : Bukkit.getOnlinePlayers()){
            zebi = new Bot(pl,"Browlin_");
            zebi.spawn(pl.getLocation());
        }
        //zebi.getBot().teleport(new Location(Bukkit.getWorld("world"),10 ,100,10));*/
        getCommand("spawnbot").setExecutor(new MainCommand());
        getCommand("killbots").setExecutor(new MainCommand());
        getCommand("killbot").setExecutor(new MainCommand());
        getCommand("simdebug").setExecutor(new MainCommand());
        getCommand("editbot").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {

    }
}
