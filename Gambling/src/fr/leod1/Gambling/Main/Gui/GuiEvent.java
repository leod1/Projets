package fr.leod1.Gambling.Main.Gui;

import fr.leod1.Gambling.Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.HashMap;
import java.util.Map;


public class GuiEvent implements Listener {

    public static Map<Player,Player> RequestTarget = new HashMap<>();
    public static Map<Player,String> RequestMode = new HashMap<>();
    public static Map<Player,Integer> RequestMise = new HashMap<>();

    @EventHandler
    public void onClickEvent (InventoryClickEvent e){
        Player P = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equalsIgnoreCase("§7Selectionne un joueur")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){ return; }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("Page suivante")){

            } else {
                String NameHeadClick = e.getCurrentItem().getItemMeta().getDisplayName().replace("§6","");
                Player PTDR = Bukkit.getPlayer(NameHeadClick);
                RequestTarget.put(P,PTDR);
                e.setCancelled(true);
                RequestMode.put(P,"coinflip");

                GuiMethods.ChoisirSome(P,0);
                RequestMise.put(P,0);

            }


        } else if (e.getInventory().getTitle().equalsIgnoreCase("§7Selectionne un mode de jeu")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){ return; }

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6 CoinFlip")){
                RequestMode.put(P,"coinflip");
                GuiMethods.ChoisirSome(P,0);
                RequestMise.put(P,0);
            }





        } else if (e.getInventory().getTitle().equalsIgnoreCase("§7Selectionne une somme a parier")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){ return; }
            Integer Valeur = RequestMise.get(P);
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+100 000 000")){
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+100000000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+10 000 000")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+10000000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+1 000 000")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+100000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+100 000")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+10000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+10 000")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+10000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+1 000")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+1000);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+100")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+100);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+10")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+10);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§a+1")) {
                RequestMise.remove(P);
                RequestMise.put(P,Valeur+1);
                GuiMethods.ChoisirSome(P,RequestMise.get(P));



            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§aValider")){

                if (Main.econ.getBalance(P) >= RequestMise.get(P)){
                    if (Main.econ.getBalance(RequestTarget.get(P).getName()) >= RequestMise.get(P)){
                        P.performCommand("Coinflip "+ RequestTarget.get(P).getName() + " "+ RequestMode.get(P) + " " + RequestMise.get(P));
                    } else {
                        P.sendMessage("§7[§bCoinflip§7] §cDésolé, §7le joueur défié na pas l'argent pour accepté le défi.");
                    }
                } else {
                    P.sendMessage("§7[§bCoinflip§7] §cDésolé, §7tu n'a pas l'argent pour lancé ce défi.");
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-100 000 000")){
                if (RequestMise.get(P) > 100000000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-100000000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-10 000 000")) {
                if (RequestMise.get(P) > 10000000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-10000000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-1 000 000")) {
                if (RequestMise.get(P) > 1000000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-1000000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-100 000")) {
                if (RequestMise.get(P) > 100000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-100000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-10 000")) {
                if (RequestMise.get(P) > 10000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-10000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-1 000")) {
                if (RequestMise.get(P) > 1000){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-1000);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-100")) {
                if (RequestMise.get(P) > 100){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-100);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-10")) {
                if (RequestMise.get(P) > 10){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-10);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§c-1")) {
                if (RequestMise.get(P) > 1){
                    RequestMise.remove(P);
                    RequestMise.put(P,Valeur-1);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                } else {
                    RequestMise.remove(P);
                    RequestMise.put(P,0);
                    GuiMethods.ChoisirSome(P,RequestMise.get(P));
                }
            }

        }
    }









}
