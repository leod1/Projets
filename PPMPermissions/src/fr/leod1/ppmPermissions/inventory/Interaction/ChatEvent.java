package fr.leod1.ppmPermissions.inventory.Interaction;

import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.inventory.InventoryMain;
import fr.leod1.ppmPermissions.playerCaches.PlayerCache;
import fr.leod1.ppmPermissions.playerCaches.Selected;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;
public class ChatEvent implements Listener {

    private InventoryMain inv = new InventoryMain();

    @EventHandler
    public void qzd(AsyncPlayerChatEvent e){
        Player pl = e.getPlayer();

        PlayerCache plCache = plugin.playerDataCache.get(pl);

        if (plCache.getSection() != null){
            if (plCache.getSection() == Selected.CREATIONPREFIX){
                plCache.getRank().setPrefix(e.getMessage());
                plCache.setSection(null);
                inv.getInvRank().creationRank(pl);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.CREATIONNAME){
                plCache.getRank().setName(e.getMessage());
                plCache.setSection(null);
                inv.getInvRank().creationRank(pl);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.CREATIONSUFIX){
                plCache.getRank().setSufix(e.getMessage());
                plCache.setSection(null);
                inv.getInvRank().creationRank(pl);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.MODIFPREFIX) {
                plugin.getRankByName(plCache.getConcurenteRankModif()).setPrefix(e.getMessage());
                inv.getInvListGroup().groupModif(pl,plugin.getRankByName(plCache.getConcurenteRankModif()));
                plCache.setConcurenteRankModif(null);
                plCache.setSection(null);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.MODIFSUFIX) {
                plugin.getRankByName(plCache.getConcurenteRankModif()).setSufix(e.getMessage());
                inv.getInvListGroup().groupModif(pl,plugin.getRankByName(plCache.getConcurenteRankModif()));
                plCache.setConcurenteRankModif(null);
                plCache.setSection(null);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.ADDPERM) {
                Rank rank = plugin.getRankByName(plCache.getConcurenteRankModif());
                if (rank.getPermissionsRank() == null){
                    rank.setPermissionsRank(new ArrayList<>());
                }
                rank.getPermissionsRank().add(e.getMessage());
                inv.getInvListGroup().groupModif(pl, plugin.getRankByName(plCache.getConcurenteRankModif()));

                plCache.setConcurenteRankModif(null);
                plCache.setSection(null);
                e.setCancelled(true);
                return;
            } else if (plCache.getSection() == Selected.ADDPERSOPERM){
                if (plugin.playerData.get(Bukkit.getPlayerExact(plCache.getConcurentePlayerModif())).getPrivatePermissions() == null){
                    plugin.playerData.get(Bukkit.getPlayerExact(plCache.getConcurentePlayerModif())).setPrivatePermissions(new ArrayList<>());
                }
                Player Target = Bukkit.getPlayerExact(plCache.getConcurentePlayerModif());
                if (plugin.playerData.get(Target).getPrivatePermissions().contains(e.getMessage())){
                    pl.sendMessage("§cDésolé, mais ce joueur à déjà cette permission");
                    plCache.setConcurentePlayerModif(null);
                    plCache.setSection(null);
                    e.setCancelled(true);
                    return;
                }
                plugin.playerData.get(Bukkit.getPlayerExact(plCache.getConcurentePlayerModif())).getPrivatePermissions().add(e.getMessage());
                plugin.setRankToPlayer(plugin.getRankByName(plugin.playerData.get(Target).getServerRank()),Target);
                inv.getInvListPlayer().listGroupForSetPermAtTarget(pl,Target);


                plCache.setConcurentePlayerModif(null);
                plCache.setSection(null);
                e.setCancelled(true);
                return;
            }
        } else {
            if (plugin.playerData.get(pl).getServerRank() == null || plugin.playerData.get(pl) == null){
                System.out.println(ChatColor.DARK_RED + "Merci de mettre un rang par défault et de redémarrer le serveur");
                return;
            }
            e.setFormat("§8[§b"+pl.getLocation().getWorld().getName()+"§8]§r "+ plugin.getRankByName(plugin.playerData.get(pl).getServerRank()).getPrefix().replace("%arrow%","⇨").replace("&","§").replace("%20%"," ")+ pl.getName() +" §7:§r " + e.getMessage());
        }
    }
}
