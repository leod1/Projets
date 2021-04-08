package fr.leod1.ReuQB.Inventory;

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

public class ListPlayerAdd {

    public void createMembres(Player pl){
        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Reunion Membres Add");

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);


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
        inv.setItem(49,Leave);
        int i = 9;

        for (Player Membre : Bukkit.getOnlinePlayers()){
            inv.setItem(i,head(Membre.getName(),"§6"+Membre.getName()));
            i++;
        }
        pl.openInventory(inv);


    }
    private ItemStack head(String name, String nameItem){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) item.getItemMeta();
        skullm.setOwner(name);
        skullm.setDisplayName(nameItem);
        item.setItemMeta(skullm);

        return item;
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

    public ItemStack fast(int nb){
        ItemStack zebi = new ItemStack(Material.STAINED_GLASS_PANE);
        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }
}
