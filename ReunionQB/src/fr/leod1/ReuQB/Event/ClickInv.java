package fr.leod1.ReuQB.Event;

import fr.leod1.ReuQB.Inventory.Accueil;
import fr.leod1.ReuQB.ReuParticipant.reuParticipant;
import fr.leod1.ReuQB.Utils.reuLocation;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static fr.leod1.ReuQB.ReuQB.InstanceP;

public class ClickInv implements Listener {

    @EventHandler
    public void zebi(InventoryClickEvent e) {
        Accueil acc = new Accueil();
        Player pl = (Player) e.getWhoClicked();
        if(e.getInventory().getName().equalsIgnoreCase("§8Qb §7Reunion")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter") && e.getCurrentItem().getType() == Material.BARRIER){
                pl.closeInventory();
                e.setCancelled(true);
                return;
            }

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bMembres") && e.getCurrentItem().getType() == Material.SKULL_ITEM){
                acc.getMb().createMembres(pl);
                e.setCancelled(true);
                return;
            }
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bGroupes") && e.getCurrentItem().getType() == Material.SKULL_ITEM){
                acc.getGr().createGroups(pl);
                e.setCancelled(true);
                return;
            }
        } else if(e.getInventory().getName().equalsIgnoreCase("§8Qb §7Reunion Membres")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour") && e.getCurrentItem().getType() == Material.BARRIER){
                acc.createAccueil(pl);
                e.setCancelled(true);
                return;
            }

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aAjouter un Participants §7(list joueur co)") && e.getCurrentItem().getType() == Material.STAINED_CLAY){
                acc.getMb().getLpa().createMembres(pl);
                e.setCancelled(true);
                return;
            }
        } else if(e.getInventory().getName().equalsIgnoreCase("§8Qb §7Reunion Groups")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour") && e.getCurrentItem().getType() == Material.BARRIER){
                acc.createAccueil(pl);
                e.setCancelled(true);
                return;
            }
        } else if(e.getInventory().getName().equalsIgnoreCase("§8Qb §7Reunion Membres Add")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }

            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour") && e.getCurrentItem().getType() == Material.BARRIER){
                acc.getMb().createMembres(pl);
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                if (!InstanceP.IsinData(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""))){
                    InstanceP.playerDATA.add(new reuParticipant(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""),new reuLocation(pl.getLocation())));
                    acc.getMb().createMembres(pl);
                } else {
                    pl.sendMessage("§cDésolé, Ce joueur est déjà dans la list");
                    acc.getMb().getLpa().createMembres(pl);
                }
                e.setCancelled(true);
                return;
            }
        }
    }
}
