package fr.leod1.switchSlotBar.stockageSlots;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SlotsBar {


    private boolean slot;
    private ArrayList<ItemStock> slotBar1;
    private ArrayList<ItemStock> slotBar2;


    public SlotsBar(boolean slot, ArrayList<ItemStack> slotBar1) {
        this.slot = slot;
        setSlotBar1(slotBar1);
    }
    public SlotsBar(boolean slot, ArrayList<ItemStack> slotBar1,ArrayList<ItemStack> slotBar2) {
        this.slot = slot;
        for (ItemStack item: slotBar1){
            ItemStock itemStok = new ItemStock(item.getType().getId(),item.getAmount(),item.getDurability());
            this.slotBar1.add(itemStok);
        }

        for (ItemStack item: slotBar2){
            ItemStock itemStok = new ItemStock(item.getType().getId(),item.getAmount(),item.getDurability());
            this.slotBar2.add(itemStok);
        }
    }

    public boolean isSlot() {
        return slot;
    }

    public void setSlot(boolean slot) {
        this.slot = slot;
    }

    public void setSlotBar1(ArrayList<ItemStack> slotBar1) {
        ArrayList<ItemStock> zebi = new ArrayList<>();
        for (ItemStack item : slotBar1) {
            ItemStock itemStak = new ItemStock(item.getType().getId(), item.getAmount(), item.getDurability());
            zebi.add(itemStak);

            this.slotBar1 = zebi;
        }
    }

    public void setSlotBar2(ArrayList<ItemStack> slotBar2) {
        ArrayList<ItemStock> zebi = new ArrayList<>();
        for (ItemStack item : slotBar2) {
            ItemStock itemStak = new ItemStock(item.getType().getId(), item.getAmount(), item.getDurability());
            zebi.add(itemStak);

            this.slotBar2 = zebi;
        }
    }

    public ArrayList<ItemStack> getSlotBar2() {
        ArrayList<ItemStack> zebi = new ArrayList<>();
        if (slotBar2 == null){
            return null;
        }
        for (ItemStock item: slotBar2){
            ItemStack itemStak = new ItemStack(item.getType(),item.getAmount(),item.getDurability());
            zebi.add(itemStak);
        }
        return zebi;
    }

    public ArrayList<ItemStack> getSlotBar1() {
        ArrayList<ItemStack> zebi = new ArrayList<>();
        if (slotBar1 == null){
            return null;
        }
        for (ItemStock item: slotBar1){
            ItemStack itemStak = new ItemStack(item.getType(),item.getAmount(),item.getDurability());
            zebi.add(itemStak);
        }
        return zebi;
    }
}
