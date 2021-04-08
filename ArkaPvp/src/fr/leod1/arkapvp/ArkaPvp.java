package fr.leod1.arkapvp;

import de.Herbystar.TTA.TTA_Methods;
import fr.leod1.arkapvp.arkapvpgames.ArkaPvpGames;
import fr.leod1.arkapvp.arkapvpstatuesgames.ArkaPvpStatuesGames;
import fr.leod1.arkapvp.event.eventJoin;
import fr.leod1.arkapvp.event.eventLeave;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ArkaPvp extends JavaPlugin {

    public static ArkaPvp InstanceOfMain;
    public ArkaPvpGames arkaPvpGames;
    public TTA_Methods Title = new TTA_Methods();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new eventJoin(), this);
        Bukkit.getPluginManager().registerEvents(new eventLeave(), this);

        getCommand("Arkagames").setExecutor(new CommandeEx());
        InstanceOfMain = this;
        arkaPvpGames = new ArkaPvpGames();
//        title = new Title();
    }

    public void StartGame(Player pl){
        if(!(arkaPvpGames.getGamesStatues() == ArkaPvpStatuesGames.Lobby)){
            pl.sendMessage("La partie a déjà commencé.");
            return;
        }
        /*if (arkaPvpGames.getAllPlayerInPart().size() < 8){
            pl.sendMessage("Il faut être au moin 8 pour commencé une partie");
            return;
        }
        if (arkaPvpGames.getTeamBlue().size() < 4){
            pl.sendMessage("Il faut être au moin 4 joueur par ésuipe pour commencé");
            return;
        }
        if (arkaPvpGames.getTeamRed().size() < 4){
            pl.sendMessage("Il faut être au moin 4 joueur par ésuipe pour commencé");
            return;
        }*/
        ////
        for (Player plBlue : arkaPvpGames.getTeamBlue()){
            plBlue.setDisplayName("§9"+plBlue.getName() + "§r");
            plBlue.teleport(arkaPvpGames.getBannerBlue());
        }
        for (Player plRed : arkaPvpGames.getTeamRed()){
            plRed.setDisplayName("§c"+plRed.getName()+ "§r");
            plRed.teleport(arkaPvpGames.getBanerRed());
        }
        for(Player Allpl : arkaPvpGames.getAllPlayerInPart()){
            ItemStack[] item = arkaPvpGames.getKit().getInventory();

            if(!(item == null)){
                for (int i = 0; i < item.length; i++) {
                    if (item[i] != null) {
                        Allpl.getInventory().setItem(i, item[i]);
                    } else {
                        Allpl.getInventory().setItem(i, new ItemStack(Material.AIR));
                    }
                }
            }
            Allpl.updateInventory();
            Title.sendTitle(Allpl,"Message",40,200,2,null,0,0,0);
        }
        arkaPvpGames.setGamesStatues(ArkaPvpStatuesGames.BeforMidFound);
    }


    @Override
    public void onDisable() {

    }
}
