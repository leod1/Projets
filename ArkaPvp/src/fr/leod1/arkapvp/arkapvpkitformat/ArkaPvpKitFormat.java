package fr.leod1.arkapvp.arkapvpkitformat;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ArkaPvpKitFormat {
    private ItemStack helmet;
    private ItemStack chest;
    private ItemStack legging;
    private ItemStack boots;
    private ItemStack[] Inventory;

    public void setKitByPlayer(Player pl){
        this.helmet = pl.getInventory().getBoots();
        this.chest = pl.getInventory().getChestplate();
        this.legging = pl.getInventory().getLeggings();
        this.boots = pl.getInventory().getBoots();
        this.Inventory = pl.getInventory().getContents();
    }

    public ItemStack[] getInventory() {
        return Inventory;
    }

    public void setInventory(ItemStack[] inventory) {
        Inventory = inventory;
    }
}
