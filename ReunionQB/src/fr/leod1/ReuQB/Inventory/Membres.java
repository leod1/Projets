package fr.leod1.ReuQB.Inventory;

import fr.leod1.ReuQB.ReuGroups.reuGroups;
import fr.leod1.ReuQB.ReuParticipant.reuParticipant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;

import static fr.leod1.ReuQB.ReuQB.InstanceP;

public class Membres {
    ListPlayerAdd Lpa = new ListPlayerAdd();
    public void createMembres(Player pl){
        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Reunion Membres");

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);

        ItemStack addList = new ItemStack(Material.STAINED_CLAY);
        addList.setDurability((short) 5);
        ItemMeta rmGroupsIM = addList.getItemMeta();
        rmGroupsIM.setDisplayName("§aAjouter un Participants §7(list joueur co)");
        rmGroupsIM.setLore(Arrays.asList("§7Permet d'afficher la list des joueurs","§7Connecter pour selectionner celui à ajouter"));
        addList.setItemMeta(rmGroupsIM);

        ItemStack addName = new ItemStack(Material.STAINED_CLAY);
        addName.setDurability((short) 5);
        ItemMeta addNameIM = addList.getItemMeta();
        addNameIM.setDisplayName("§aAjouter un Participants §7(Pseudo)");
        addNameIM.setLore(Arrays.asList("§7Permet d'ajouter un participant avec","§7Son pseudo"));
        addName.setItemMeta(addNameIM);


        for (int i = 0; i < 9; i++){
            if ( i % 2 == 0){
                inv.setItem(i,fast(15));
            }else {
                inv.setItem(i,fast(0));
            }
        }

        for (int i = 45; i < 54; i++){
            if ( i % 2 == 0){
                inv.setItem(i,fast(15));
            }else {
                inv.setItem(i,fast(0));
            }
        }
        inv.setItem(45,addList);
        inv.setItem(46,addName);
        inv.setItem(49,Leave);
        int i = 9;

        for (reuParticipant Membre : InstanceP.playerDATA){
            inv.setItem(i,setItem(head(Membre.getName(),Membre.getName()), 1, "§6" + Membre.getName(), Arrays.asList("§bX: "+Membre.getLocation().getX(),"§bY: "+Membre.getLocation().getY(),"§bZ: "+Membre.getLocation().getZ(),"§7[DROP] SUPPRIMER","§7[DROIT] REDEFINIR LA LOCATION")));
            i++;
        }
        pl.openInventory(inv);


    }
    public ItemStack setItem(ItemStack material, int n, String name, List description) {

        ItemStack item = material ;
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

    public ItemStack fast(int nb){
        ItemStack zebi = new ItemStack(Material.STAINED_GLASS_PANE);
        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }

    public ListPlayerAdd getLpa() {
        return Lpa;
    }
    private ItemStack head(String name, String nameItem){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) item.getItemMeta();
        skullm.setOwner(name);
        skullm.setDisplayName(nameItem);
        item.setItemMeta(skullm);

        return item;
    }
}
