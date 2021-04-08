package fr.leod1.switchSlotBar.stockageSlots;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class ItemStock {

    private int type;
    private int amount;
    private short durability;

    public ItemStock(int type, int amount, short durability) {
        this.type = type;
        this.amount = amount;
        this.durability = durability;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public short getDurability() {
        return durability;
    }

    public void setDurability(short durability) {
        this.durability = durability;
    }

}
