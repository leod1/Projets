package fr.leod1.ppmPermissions.inventory.groups;

import fr.leod1.ppmPermissions.inventory.ColorHeads;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvSelectionColor {

    public void SelectionColor(Player pl){
        Inventory inv = Bukkit.createInventory(null, 18,"§cPPM Perms §8Creation rank Selection Color");
        inv.setItem(0,a(ColorHeads.createSkull(ColorHeads.BLACK.getUrl()),"Noir"));
        inv.setItem(1,a(ColorHeads.createSkull(ColorHeads.DARK_BLUE.getUrl()),"Bleu foncé"));
        inv.setItem(2,a(ColorHeads.createSkull(ColorHeads.DARK_GREEN.getUrl()),"Vert foncé"));
        inv.setItem(3,a(ColorHeads.createSkull(ColorHeads.DARK_AQUA.getUrl()),"Cyan foncé"));
        inv.setItem(4,a(ColorHeads.createSkull(ColorHeads.DARK_RED.getUrl()),"Rouge foncé"));
        inv.setItem(5,a(ColorHeads.createSkull(ColorHeads.DARK_PURPLE.getUrl()),"Violet foncé"));
        inv.setItem(6,a(ColorHeads.createSkull(ColorHeads.GOLD.getUrl()),"Or"));
        inv.setItem(7,a(ColorHeads.createSkull(ColorHeads.GRAY.getUrl()),"Gris"));
        inv.setItem(8,a(ColorHeads.createSkull(ColorHeads.DARK_GRAY.getUrl()),"Gris foncé"));
        inv.setItem(9,a(ColorHeads.createSkull(ColorHeads.BLUE.getUrl()),"Bleu"));
        inv.setItem(10,a(ColorHeads.createSkull(ColorHeads.GREEN.getUrl()),"Vert"));
        inv.setItem(11,a(ColorHeads.createSkull(ColorHeads.AQUA.getUrl()),"Cyan"));
        inv.setItem(12,a(ColorHeads.createSkull(ColorHeads.LIGHT_PURPLE.getUrl()),"Violet"));
        inv.setItem(13,a(ColorHeads.createSkull(ColorHeads.RED.getUrl()),"Rouge"));
        inv.setItem(14,a(ColorHeads.createSkull(ColorHeads.YELLOW.getUrl()),"Jaune"));
        inv.setItem(15,a(ColorHeads.createSkull(ColorHeads.WHITE.getUrl()),"Blanc"));
        pl.openInventory(inv);



    }

    private ItemStack a(ItemStack item, String name){
        ItemMeta zebi = item.getItemMeta();
        zebi.setDisplayName(name);
        item.setItemMeta(zebi);
        return item;
    }
}
