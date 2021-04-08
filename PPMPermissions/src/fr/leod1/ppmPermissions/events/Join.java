package fr.leod1.ppmPermissions.events;

import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.Utils.fileUtils;
import fr.leod1.ppmPermissions.playerCaches.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class Join implements Listener {
    @EventHandler
    public void join(PlayerJoinEvent event) throws IOException {
        Player pl = event.getPlayer();

        plugin.playerDataCache.put(pl, new PlayerCache());
        plugin.sendTablist(pl);

        //Load player Data
        File DirectoryPlayerData = new File(plugin.getDataFolder()+"/PlayerData/");
        final File file = new File(DirectoryPlayerData,pl.getName()+".json");
        if(file.exists()){
            PlayerData json = plugin.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
            if (json == null){
                if (plugin.getDefaultRank() == null){
                    System.out.println("§4[URGENT]§c Merci de définir un group par défaut et de redémarrer le serveur !!!");
                }else {
                    plugin.playerData.put(pl,new PlayerData(plugin.getDefaultRankString(), new ArrayList<>()));
                }
            } else {
                plugin.playerData.put(pl,json);
            }
        } else {
            if (plugin.getDefaultRank() == null){
                System.out.println("§4[URGENT]§c Merci de définir un group par défaut et de redémarrer le serveur !!!");
                return;
            }else {
                plugin.playerData.put(pl,new PlayerData(plugin.getDefaultRankString(), new ArrayList<>()));
            }
        }
        //Load player Data

        //Load rank to player
        if (plugin.playerData.get(pl) == null){
            System.out.println("§4[URGENT]§c Merci de définir un group par défaut et de redémarrer le serveur !!!");
        } else {
            PlayerData pldata = plugin.playerData.get(pl);
            if(plugin.getRankByName(pldata.getServerRank()) == null){
                pldata.setServerRank(plugin.getDefaultRankString());
            }
            plugin.setRankToPlayer(plugin.getRankByName(pldata.getServerRank()),pl);
        }
        //Load rank to player


    }
}
