package fr.leod1.arkapvp.event;


import fr.leod1.arkapvp.arkapvpgames.ArkaPvpGames;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;


import static fr.leod1.arkapvp.ArkaPvp.InstanceOfMain;

public class eventJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        ArkaPvpGames Games = InstanceOfMain.arkaPvpGames;
        Player pl = e.getPlayer();
        e.setJoinMessage("");


        if(!(Games.getAllPlayerInPart().size() >= Games.getMaxPlayerInPart())){
            if (!(Games.getAllPlayerInPart().contains(pl))){
                Games.addPlayerInPart(pl);
                Bukkit.broadcastMessage("[ArkaPvp] Le joueur "+pl.getName()+" viens de rejoindre la partie ("+Games.getAllPlayerInPart().size()+ "/"+ Games.getMaxPlayerInPart() + ")");
                if(Games.getLobby() != null ){
                    pl.teleport(Games.getLobby());
                }
            }
        }


        /*Scoreboard test = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = test.registerNewObjective("Test", "dummy");
        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);
        obj.setDisplayName("§cZEBI");

        Score joueur = obj.getScore("§bTest:§r "+ Games.getAllPlayerInPart().get(0));
        joueur.setScore(5);

        pl.setScoreboard(test);*/
        InstanceOfMain.Title.sendTitle(pl,"Message",40,200,2,null,0,0,0);
    }
}
