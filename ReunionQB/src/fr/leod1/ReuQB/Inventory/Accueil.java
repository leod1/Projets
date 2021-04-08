package fr.leod1.ReuQB.Inventory;

import fr.leod1.ReuQB.Utils.CustomHead.HeadManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Accueil {
    HeadManager hm = new HeadManager();
    Groups gr = new Groups();
    Membres mb = new Membres();
    public void createAccueil(Player pl){
        ItemStack ListG = hm.getHead("Groupe");
        ItemMeta ListGIM = ListG.getItemMeta();
        ListGIM.setDisplayName("§bGroupes");
        ListGIM.setLore(Arrays.asList("§7Permet de lister, ajouter, supprimer", "§7Et gérer les groupes"));
        ListG.setItemMeta(ListGIM);

        ItemStack ListM = hm.getHead("Menbre");
        ItemMeta ListMIM = ListM.getItemMeta();
        ListMIM.setDisplayName("§bMembres");
        ListMIM.setLore(Arrays.asList("§7Permet de lister, ajouter, supprimer", "§7Et gérer les participants"));
        ListM.setItemMeta(ListMIM);

        ItemStack History = new ItemStack(Material.MAP);
        ItemMeta HistoryIM = History.getItemMeta();
        HistoryIM.setDisplayName("§bHistorique");
        HistoryIM.setLore(Arrays.asList("§7Permet de voir l'historique.", "§7Des reunions qu'il a eu"));
        History.setItemMeta(HistoryIM);

        ItemStack Tuto2 = new ItemStack(Material.BOOK);
        ItemMeta Tuto2IM = Tuto2.getItemMeta();
        Tuto2IM.setDisplayName("§cTUTO 2");
        Tuto2IM.setLore(Arrays.asList("§7P"));
        Tuto2.setItemMeta(Tuto2IM);

        ItemStack Tuto1 = new ItemStack(Material.BOOK);
        ItemMeta Tuto1IM = Tuto1.getItemMeta();
        Tuto1IM.setDisplayName("§cTUTO 1");
        Tuto1IM.setLore(Arrays.asList("§7P"));
        Tuto1.setItemMeta(Tuto1IM);

        ItemStack InReu = new ItemStack(Material.CONCRETE);
        InReu.setDurability((short) 4);
        ItemMeta InReuIM = InReu.getItemMeta();
        InReuIM.setDisplayName("§eLes Réunions en cours");
        InReuIM.setLore(Arrays.asList("§7Permet de lister les reunions","§7En cours"));
        InReu.setItemMeta(InReuIM);

        ItemStack StopReu = new ItemStack(Material.CONCRETE);
        StopReu.setDurability((short) 14);
        ItemMeta StopReuIM = StopReu.getItemMeta();
        StopReuIM.setDisplayName("§cStopper les reunions en cours");
        StopReuIM.setLore(Arrays.asList("§7Permet de lister les reunions","§7En cours et de les stopper"));
        StopReu.setItemMeta(StopReuIM);

        ItemStack StartReu = new ItemStack(Material.CONCRETE);
        StartReu.setDurability((short) 5);
        ItemMeta StartReuIM = StartReu.getItemMeta();
        StartReuIM.setDisplayName("§aDémmarer les reunions");
        StartReuIM.setLore(Arrays.asList("§7Permet de démmarer des reunions"));
        StartReu.setItemMeta(StartReuIM);

        ItemStack rmGroups = new ItemStack(Material.STAINED_CLAY);
        rmGroups.setDurability((short) 14);
        ItemMeta rmGroupsIM = rmGroups.getItemMeta();
        rmGroupsIM.setDisplayName("§4Remove un groupe");
        rmGroupsIM.setLore(Arrays.asList("§7Permet de supprimer un groupe"));
        rmGroups.setItemMeta(rmGroupsIM);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cQuitter");
        Leave.setItemMeta(metaLeave);


        Inventory inv = Bukkit.createInventory(null, 27,"§8Qb §7Reunion");

        for (int i = 0; i < 9; i++){
            if ( i % 2 == 0){
                inv.setItem(i,fast(15));
            }else {
                inv.setItem(i,fast(0));
            }
        }

        for (int i = 18; i < 27; i++){
            if ( i % 2 == 0){
                inv.setItem(i,fast(15));
            }else {
                inv.setItem(i,fast(0));
            }
        }
        inv.setItem(15,fast(12));
        inv.setItem(9, ListM);
        inv.setItem(10, ListG);
        inv.setItem(14, History);
        inv.setItem(11, StartReu);
        inv.setItem(12, InReu);
        inv.setItem(13, StopReu);
        inv.setItem(16, Tuto1);
        inv.setItem(17, Tuto2);
        inv.setItem(22, Leave);



        pl.openInventory(inv);
    }

    public ItemStack fast(int nb){
        ItemStack zebi = new ItemStack(Material.STAINED_GLASS_PANE);
        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }
    public ItemStack setItem(Material material, int n, String name, List description) {

        ItemStack item = new ItemStack(material, n);
        ItemMeta itemMeta = item.getItemMeta();

        if (name != null) {
            if (name.equals("")) {
                itemMeta.setDisplayName("§r");
            } else {
                itemMeta.setDisplayName(name);
            }
        }

        if (description != null && !description.equals("")) {
            itemMeta.setLore(description);
        }

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return item;
    }

    public Groups getGr() {
        return gr;
    }

    public Membres getMb() {
        return mb;
    }
}
