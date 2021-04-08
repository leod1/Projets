package fr.leod1.ppmPermissions.inventory.Interaction;

import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.inventory.ColorHeads;
import fr.leod1.ppmPermissions.inventory.InventoryMain;
import fr.leod1.ppmPermissions.playerCaches.PlayerCache;
import fr.leod1.ppmPermissions.playerCaches.Selected;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import static fr.leod1.ppmPermissions.PPMPermissions.plugin;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Random;

public class InteractEvent implements Listener {
    private InventoryMain inv = new InventoryMain();

    @EventHandler
    public void zeb(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player pl = (Player) e.getWhoClicked();
            //PPMcreatorProject Projplayer = InstanceOfMain.getPPMcreator(pl);
            if (e.getInventory().getName().equalsIgnoreCase("§cPPM Perms")) {
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRanks")){
                    if(e.getClick() == ClickType.RIGHT){
                        inv.getInvRank().creationRank(pl);
                        e.setCancelled(true);
                        return;
                    } else {
                        inv.getInvListGroup().listGroup(pl);
                        e.setCancelled(true);
                        return;
                    }
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPlayers")){
                    inv.getInvListPlayer().listPlayer(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cLeave")){
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Perms §8List players")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getType().equals(Material.SKULL_ITEM)){
                    Player Target = Bukkit.getPlayerExact(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    inv.getInvListPlayer().listPlayerTarget(pl,Target);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getType().equals(Material.BARRIER)){
                    inv.MainInventory(pl);
                    e.setCancelled(true);
                    return;
                }else {
                    e.setCancelled(true);
                    return;
                }

            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Perms §8Creation rank")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Validation")){
                    if (plugin.playerDataCache.get(pl).getRank().getColor() == null){
                        e.setCancelled(true);
                        return;
                    }
                    if (plugin.playerDataCache.get(pl).getRank().getName() == null){
                        e.setCancelled(true);
                        return;
                    }
                    if (plugin.rankExist(plugin.playerDataCache.get(pl).getRank().getName())){
                        e.setCancelled(true);
                        return;
                    }

                    if (plugin.playerDataCache.get(pl).getRank().getPrefix() == null){
                        plugin.playerDataCache.get(pl).getRank().setPrefix("");
                    }
                    if (plugin.playerDataCache.get(pl).getRank().getSufix() == null){
                        plugin.playerDataCache.get(pl).getRank().setSufix("");
                    }
                    plugin.playerDataCache.get(pl).getRank().setPermissionsRank(new ArrayList<>());
                    plugin.ServerRank.add(plugin.playerDataCache.get(pl).getRank());
                    plugin.playerDataCache.get(pl).setRank(new Rank(false));
                    inv.MainInventory(pl);
                    pl.sendMessage("Le rank à bien été ajouté.");
                    e.setCancelled(true);
                    return;

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cNom ")){
                    plugin.playerDataCache.get(pl).setSection(Selected.CREATIONNAME);
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cSuffix ")){
                    plugin.playerDataCache.get(pl).setSection(Selected.CREATIONSUFIX);
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cPrefix ")){
                    plugin.playerDataCache.get(pl).setSection(Selected.CREATIONPREFIX);
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cColor")) {
                    inv.getInvSelectionColor().SelectionColor(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cDefault")) {
                    if (plugin.playerDataCache.get(pl).getRank().isRankDefault()){
                        plugin.playerDataCache.get(pl).getRank().setRankDefault(false);
                        inv.getInvRank().creationRank(pl);
                    } else {
                        plugin.playerDataCache.get(pl).getRank().setRankDefault(true);
                        inv.getInvRank().creationRank(pl);
                    }
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cRetour")) {
                    inv.MainInventory(pl);
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Perms §8Creation rank Selection Color")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Noir")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.BLACK);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Bleu foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_BLUE);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Vert foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_GREEN);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Cyan foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_AQUA);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Rouge foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_RED);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Violet foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_PURPLE);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Or")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.GOLD);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Gris")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.GRAY);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Gris foncé")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.DARK_GRAY);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Bleu")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.BLUE);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Vert")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.GREEN);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Cyan")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.AQUA);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Violet")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.LIGHT_PURPLE);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Rouge")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.RED);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Jaune")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.YELLOW);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Blanc")){
                    plugin.playerDataCache.get(pl).getRank().setColor(ColorHeads.WHITE);
                    inv.getInvRank().creationRank(pl);
                    e.setCancelled(true);
                    return;
                }

            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Perms §8List rank")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.ARROW){
                    //Pages
                } else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    if (plugin.rankExist(e.getCurrentItem().getItemMeta().getDisplayName())){
                        inv.getInvListGroup().groupModif(pl,plugin.getRankByName(e.getCurrentItem().getItemMeta().getDisplayName()));
                        e.setCancelled(true);
                        return;
                    } else {
                        e.setCancelled(true);
                        return;
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cRetour")) {
                    inv.MainInventory(pl);
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().contains("§cPPM Perms §7players ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                    inv.getInvListPlayer().listPlayer(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getType() == Material.TOTEM){
                    if (e.getClick() == ClickType.RIGHT){
                        plugin.playerDataCache.get(pl).setSection(Selected.ADDPERSOPERM);
                        plugin.playerDataCache.get(pl).setConcurentePlayerModif(e.getInventory().getName().replace("§cPPM Perms §7players ",""));
                        pl.closeInventory();
                        e.setCancelled(true);
                        return;
                    } else {
                        inv.getInvListPlayer().listGroupForSetPermAtTarget(pl,Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7players ","")));
                        e.setCancelled(true);
                        return;
                    }
                } else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    if (plugin.rankExist(e.getCurrentItem().getItemMeta().getDisplayName())){
                        inv.getInvListPlayer().listGroupForSetAtTarget(pl,Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7players ","")));
                        e.setCancelled(true);
                        return;
                    } else {
                        e.setCancelled(true);
                        return;
                    }
                }

            }else if (e.getInventory().getName().contains("§cPPM Perms §7List perm for ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")) {
                    inv.getInvListPlayer().listPlayerTarget(pl, Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List perm for ", "")));
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.TOTEM) {
                    Player Target = Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List perm for ", ""));
                    plugin.playerData.get(Target).getPrivatePermissions().remove(e.getCurrentItem().getItemMeta().getDisplayName());
                    inv.getInvListPlayer().listGroupForSetPermAtTarget(pl,Target);
                    e.setCancelled(true);
                    return;
                }

                } else if (e.getInventory().getName().contains("§cPPM Perms §7List rank for ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                    inv.getInvListPlayer().listPlayerTarget(pl,Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List rank for ","")));
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    if (plugin.rankExist(e.getCurrentItem().getItemMeta().getDisplayName())){
                        plugin.playerData.get(Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List rank for ",""))).setServerRank(e.getCurrentItem().getItemMeta().getDisplayName());
                        plugin.setRankToPlayer(plugin.getRankByName(e.getCurrentItem().getItemMeta().getDisplayName()),Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List rank for ","")));
                        inv.getInvListPlayer().listPlayerTarget(pl,Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Perms §7List rank for ","")));
                        e.setCancelled(true);
                        return;
                    } else {
                        e.setCancelled(true);
                        return;
                    }
                }


            } else if (e.getInventory().getName().contains("§cPPM Perms §7Modif ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                Rank rank = plugin.getRankByName(e.getInventory().getName().replace("§cPPM Perms §7Modif ",""));
                PlayerCache plData = plugin.playerDataCache.get(pl);

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPrefix")){

                    plData.setSection(Selected.MODIFPREFIX);
                    plData.setConcurenteRankModif(rank.getName());
                    e.setCancelled(true);
                    pl.closeInventory();
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSuffix")){

                    plData.setSection(Selected.MODIFSUFIX);
                    plData.setConcurenteRankModif(rank.getName());
                    e.setCancelled(true);
                    pl.closeInventory();
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cDefault : §7[§l§a✓§r§7]")){

                    rank.setRankDefault(false);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cDefault : §7[§4✖§7]")){
                    rank.setRankDefault(true);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cColor")){
                    inv.getInvListGroup().groupModifColor(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPermissions")){
                    if (e.getClick().equals(ClickType.RIGHT)){
                        plData.setSection(Selected.ADDPERM);
                        plData.setConcurenteRankModif(rank.getName());
                        pl.closeInventory();
                        e.setCancelled(true);
                        return;
                    }else {
                        inv.getInvListGroup().groupModifPerm(pl,rank, plugin.playerDataCache.get(pl).getPagePermRank());
                        e.setCancelled(true);
                        return;
                    }
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                    inv.getInvListGroup().listGroup(pl);
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().contains("§cPPM Perms §8Modif Color ")) {
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    e.setCancelled(true);
                    return;
                }
                Rank rank = plugin.getRankByName(e.getInventory().getName().replace("§cPPM Perms §8Modif Color ", ""));
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Noir")) {
                    rank.setColor(ColorHeads.BLACK);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;

                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Bleu foncé")) {
                    rank.setColor(ColorHeads.DARK_BLUE);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Vert foncé")) {
                    rank.setColor(ColorHeads.DARK_GREEN);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Cyan foncé")) {
                    rank.setColor(ColorHeads.DARK_AQUA);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Rouge foncé")) {
                    rank.setColor(ColorHeads.DARK_RED);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Violet foncé")) {
                    rank.setColor(ColorHeads.DARK_PURPLE);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Or")) {
                    rank.setColor(ColorHeads.GOLD);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Gris")) {
                    rank.setColor(ColorHeads.GRAY);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Gris foncé")) {
                    rank.setColor(ColorHeads.DARK_GRAY);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Bleu")) {
                    rank.setColor(ColorHeads.BLUE);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Vert")) {
                    rank.setColor(ColorHeads.GREEN);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Cyan")) {
                    rank.setColor(ColorHeads.AQUA);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Violet")) {
                    rank.setColor(ColorHeads.LIGHT_PURPLE);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Rouge")) {
                    rank.setColor(ColorHeads.RED);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Jaune")) {
                    rank.setColor(ColorHeads.YELLOW);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("Blanc")) {
                    rank.setColor(ColorHeads.WHITE);
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().contains("§cPPM Perms §8Modif Perm ")) {
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR) {
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    e.setCancelled(true);
                    return;
                }
                Rank rank = plugin.getRankByName(e.getInventory().getName().replace("§cPPM Perms §8Modif Perm ", ""));
                if (e.getCurrentItem().getType() == Material.BARRIER) {
                    inv.getInvListGroup().groupModif(pl,rank);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.TOTEM){
                    rank.getPermissionsRank().remove(e.getCurrentItem().getItemMeta().getDisplayName());
                    inv.getInvListGroup().groupModifPerm(pl,rank, plugin.playerDataCache.get(pl).getPagePermRank());
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.ARROW) {
                    if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cPage Précédente")){
                        if (plugin.playerDataCache.get(pl).getPagePermRank() <= 0){
                            inv.getInvListGroup().groupModifPerm(pl, rank, plugin.playerDataCache.get(pl).getPagePermRank());
                            e.setCancelled(true);
                            return;
                        } else {
                            plugin.playerDataCache.get(pl).setPagePermRank(plugin.playerDataCache.get(pl).getPagePermRank() - 1);
                            inv.getInvListGroup().groupModifPerm(pl, rank, plugin.playerDataCache.get(pl).getPagePermRank());
                            e.setCancelled(true);
                            return;
                        }
                    } else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cPage Suivante")){
                            plugin.playerDataCache.get(pl).setPagePermRank(plugin.playerDataCache.get(pl).getPagePermRank() + 1);
                            inv.getInvListGroup().groupModifPerm(pl, rank, plugin.playerDataCache.get(pl).getPagePermRank());
                            e.setCancelled(true);
                            return;
                        }
                    }

                }else{
                    e.setCancelled(true);
                    return;
                }
            }
        }

    public String getRandomStr(int n) {
        //choisissez un caractére au hasard à partir de cette chaîne
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder s = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(str.length() * Math.random());
            s.append(str.charAt(index));
        }
        return s.toString();
    }
}
