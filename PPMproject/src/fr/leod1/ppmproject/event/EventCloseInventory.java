package fr.leod1.ppmproject.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class EventCloseInventory implements Listener {
    public void CloseInventory(InventoryCloseEvent e) {
        if (e.getPlayer() instanceof Player) {
            InstanceOfMain.getPPMcreator((Player) e.getPlayer()).setWatIsSelect(null);
            InstanceOfMain.getPPMcreator((Player) e.getPlayer()).setPage(0);
        }
    }
}
