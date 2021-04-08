package fr.leod1.Geometrique;

import fr.leod1.Geometrique.Event.Break;
import fr.leod1.Geometrique.PlayerGeometrique.PlayerGeo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public static Main InstanceOfMain;
    public HashMap<Player, PlayerGeo> PlayerG;

    @Override
    public void onEnable() {
        InstanceOfMain = this;
        PlayerG = new HashMap();
        Bukkit.getPluginManager().registerEvents(new Break(), this);

        for (Player pl : Bukkit.getOnlinePlayers()){
            PlayerG.put(pl,new PlayerGeo());
        }
    }

    @Override
    public void onDisable() {


    }
}
