package fr.leod1.switchSlotBar.events;

import fr.leod1.switchSlotBar.stockageSlots.SlotsBar;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

import static fr.leod1.switchSlotBar.switchSlotBar.InstanceOfMain;

public class SwitchItem implements Listener {

    @EventHandler
    public void zebi (PlayerSwapHandItemsEvent e){
        SlotsBar Slots = InstanceOfMain.playerCaches.get(e.getPlayer());
        if (Slots.isSlot()){
            Slots.setSlot(false);

            //Avant
            ArrayList<ItemStack> qzd = new ArrayList<>();
            for (int i = 0; i < 9;i++){
                if (e.getPlayer().getInventory().getItem(i) == null){
                    qzd.add(new ItemStack(Material.AIR));
                }else {
                    qzd.add(e.getPlayer().getInventory().getItem(i));
                }
            }
            Slots.setSlotBar1(qzd);


            //Apres
            if (Slots.getSlotBar2() == null){
                for (int i = 0; i < 9;i++){
                    e.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            } else {
                for (int i = 0; i < 9;i++){
                    e.getPlayer().getInventory().setItem(i, Slots.getSlotBar2().get(i));
                }

            }



        } else {

            Slots.setSlot(true);

            //Avant
            ArrayList<ItemStack> qzd = new ArrayList<>();
            for (int i = 0; i < 9;i++){
                if (e.getPlayer().getInventory().getItem(i) == null){
                    qzd.add(new ItemStack(Material.AIR));
                } else {
                    qzd.add(e.getPlayer().getInventory().getItem(i));
                }
            }
            Slots.setSlotBar2(qzd);


            //Apres
            if (Slots.getSlotBar1() == null){
                for (int i = 0; i < 9;i++){
                    e.getPlayer().getInventory().setItem(i, new ItemStack(Material.AIR));
                }
            } else {
                for (int i = 0; i < 9;i++){
                    e.getPlayer().getInventory().setItem(i, Slots.getSlotBar1().get(i));
                }

            }

        }
        e.setCancelled(true);
    }
}
