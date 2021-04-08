package fr.leod1.ppmPermissions;

import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.Utils.ProjectSerializationManager;
import fr.leod1.ppmPermissions.Utils.fileUtils;
import fr.leod1.ppmPermissions.events.Join;
import fr.leod1.ppmPermissions.events.Leave;
import fr.leod1.ppmPermissions.events.Walk;
import fr.leod1.ppmPermissions.inventory.Interaction.ChatEvent;
import fr.leod1.ppmPermissions.inventory.Interaction.InteractEvent;
import fr.leod1.ppmPermissions.playerCaches.PlayerCache;
import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PPMPermissions extends JavaPlugin {
    public static PPMPermissions plugin;
    public Scoreboard scoreboard;
    public ArrayList<Rank> ServerRank;
    public HashMap<Player, PlayerCache> playerDataCache = new HashMap<>();
    public HashMap<Player, PlayerData> playerData = new HashMap<>();
    public HashMap<Player, PermissionAttachment> playerPermission = new HashMap<>();
    private ProjectSerializationManager projectSerializationManager;

    @Override
    public void onEnable() {
        projectSerializationManager = new ProjectSerializationManager();
        plugin = this;
        ServerRank = new ArrayList<>();
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        getCommand("testde").setExecutor(new CommandeEx());
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new Walk(), this);
        this.getServer().getPluginManager().registerEvents(new Join(), this);
        this.getServer().getPluginManager().registerEvents(new Leave(), this);
        this.getServer().getPluginManager().registerEvents(new InteractEvent(), this);

        //PlayerData
        File DirectoryPlayerData = new File(this.getDataFolder().getPath()+"/PlayerData/");
        DirectoryPlayerData.setWritable(true);
        DirectoryPlayerData.mkdirs();
        //PlayerData

        //Rank
        File Directory = new File(this.getDataFolder(), "/Rank/");
        if (Directory.exists()){
            for (File fileEntry : Objects.requireNonNull(Directory.listFiles())) {
                try {
                    Rank ahouia = projectSerializationManager.deserializeRank(fileUtils.loadContent(fileEntry));
                    ServerRank.add(ahouia);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Directory.setWritable(true);
            Directory.mkdirs();
        }
        //Rank

        //Load player Data
        for (Player pl : Bukkit.getOnlinePlayers()){
            sendTablist(pl);
            playerDataCache.put(pl,new PlayerCache());
            File DirectoryPlayerDatat = new File(plugin.getDataFolder()+"/PlayerData/");
            final File file = new File(DirectoryPlayerDatat,pl.getName()+".json");
            if(file.exists()){
                PlayerData json = null;
                try {
                    json = plugin.getProjectSerializationManager().deserializePlayerData(fileUtils.loadContent(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        //Load player Data


    }

    @Override
    public void onDisable() {
        //Unload playerdata
        for (Player pl : Bukkit.getOnlinePlayers()){
            playerDataCache.remove(pl);
            File DirectoryPlayerData = new File(plugin.getDataFolder()+"/PlayerData/");
            final File file = new File(DirectoryPlayerData,pl.getName()+".json");
            try {
                fileUtils.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PlayerData zebi = plugin.playerData.get(pl);
            String json = plugin.getProjectSerializationManager().serializePlayerData(zebi);
            fileUtils.save(file,json);
            plugin.scoreboard.getTeam(pl.getName()).unregister();
        }
        //Unload playerdata
        //Rank
        File DirectoryProj = new File(this.getDataFolder(),"/Rank/");
        DirectoryProj.mkdirs();

        for(Rank pp : ServerRank){
            final File file = new File(DirectoryProj,pp.getName()+".json");
            String json = projectSerializationManager.serializeRank(pp);
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileUtils.save(file,json);

        }
        //Rank

    }


    public Rank getDefaultRank() {
        for (Rank rank: ServerRank) {
            if (rank.isRankDefault()) {
                return rank;
            }
        }
        return null;
    }

    public String getDefaultRankString(){
        for (Rank rank: ServerRank) {
            if (rank.isRankDefault()) {
                return rank.getName();
            }
        }
        return null;
    }
    public Rank getRankByName(String name){
        for (Rank rank : ServerRank) {
            if (rank.getName().equals(name)) {
                return rank;
            }
        }
        return null;
    }

    public void setRankToPlayer(Rank rank,Player pl) {
        PermissionAttachment attachment = pl.addAttachment(this);
        for (String permRank : rank.getPermissionsRank()) {
            attachment.setPermission(permRank,true);
        }
        for (String permPlayer : this.playerData.get(pl).getPrivatePermissions()) {
            attachment.setPermission(permPlayer,true);
        }
        if (playerPermission.containsValue(pl)){
            playerPermission.remove(pl);
        }
        playerPermission.put(pl,attachment);
        pl.setScoreboard(scoreboard);
        if (!(scoreboard.getTeam(pl.getName()) == null)){
            plugin.scoreboard.getTeam(pl.getName()).unregister();
        }
        Team tras = scoreboard.registerNewTeam(pl.getName());
        tras.setPrefix(rank.getPrefix().replace("%arrow%","⇨").replace("&","§").replace("%20%"," "));
        tras.setSuffix(rank.getSufix().replace("%arrow%","⇨").replace("&","§").replace("%20%"," "));
        tras.setNameTagVisibility(NameTagVisibility.ALWAYS);
        tras.addEntry(pl.getName());


    }
    public boolean rankExist(String name) {
        for (Rank rank : ServerRank) {
            if (rank.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void sendTablist(Player p){
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;//۞ ∎ ⌚
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\": \"§6▇ §6§l[§c§lPamplemousse§6§l]§6 ▇\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \"\n      §3X§8: §r" +p.getLocation().getBlockX() + "    §3Y§8: §r" +p.getLocation().getBlockY() + "    §3Z§8: §r" +p.getLocation().getBlockZ() + "" +
                "       \n§8§l[§b§l" + p.getWorld().getName() +"§8§l]" + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field headerField = packet.getClass().getDeclaredField("a");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("b");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        connection.sendPacket(packet);
    }

    /*public void setupPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(this);
        this.playerPermissions.put(player , attachment);
        permissionsSetter(player);
    }

    private void permissionsSetter(Player uuid) {
        PermissionAttachment attachment = this.playerPermissions.get(uuid);
        for (String groups : this.getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : this.getConfig().getStringList("Groups." + groups + ".permissions")) {
                System.out.print(permissions);
                attachment.setPermission(permissions, true);
            }
        }
    }*/


    public ProjectSerializationManager getProjectSerializationManager() {
        return projectSerializationManager;
    }
}
