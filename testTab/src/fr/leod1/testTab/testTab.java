package fr.leod1.testTab;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;

public class testTab extends JavaPlugin {

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        /*Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Team red = scoreboard.registerNewTeam("§4[RED]");
        red.setNameTagVisibility(NameTagVisibility.ALWAYS);*/
        for (Player pl : Bukkit.getOnlinePlayers()){
            sendTablist(pl);
            setScoreboard(pl);
        }

    }

    public void sendTablist(Player p){
        CraftPlayer craftplayer = (CraftPlayer) p;
        PlayerConnection connection = craftplayer.getHandle().playerConnection;//۞ ∎ ⌚
        IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\": \"§6▇ §6§l[§c§lPamplemousse§6§l]§6 ▇\"}");
        IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" +
                "       §6▇\n" +
                "       §7▇§r▇§6▇\n" +
                "     §7▇§r▇§r▇§6▇\n" +
                "   §6▇§r▇§r▇§r▊§6▇ \n" +
                " §6▇▇▇\"}");
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



    public void setScoreboard(Player pl){
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        setNametag(sb);
        pl.setScoreboard(sb);
    }

    public void setNametag(Scoreboard sb){
        Team Pamplemouss = sb.registerNewTeam("§4Test");
        Team Visiteur = sb.registerNewTeam("§eTest");
        Pamplemouss.setPrefix("§cBoss  ⇨  ");
        Pamplemouss.setNameTagVisibility(NameTagVisibility.ALWAYS);
        for (Player pl : Bukkit.getOnlinePlayers()){
            if (pl.isOp()){
                Pamplemouss.addPlayer(pl);
            } else {
                Visiteur.addEntry(pl.getName());
            }
        }

    }

}
