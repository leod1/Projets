package fr.leod1.ReuQB.Inventory;

import fr.leod1.ReuQB.ReuGroups.reuGroups;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.Arrays;
import java.util.List;

import static fr.leod1.ReuQB.ReuQB.InstanceP;

public class Groups{
    public void createGroups(Player pl){
        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Reunion Groups");

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

        for (reuGroups Group : InstanceP.Group){
            inv.setItem(i,setItem(Material.SEA_LANTERN, 1, Group.getName(), Arrays.asList("Oui","qzd")));
            i++;
        }
        pl.openInventory(inv);


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
