package fr.leod1.schemAchat;

import fr.leod1.schemAchat.utils.Line;
import fr.leod1.schemAchat.utils.SignGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import static fr.leod1.schemAchat.SchemAchat.InstanceMain;

public class Test implements Listener {

    @EventHandler
    public void qzed(BlockBreakEvent e){
        Player pl = e.getPlayer();
        System.out.println("Testts");
        SignGui gui = new SignGui()
                .line(Line.SECOND, "Default line2") // Set line 2
                .line(Line.FOURTH, "Default line4") // Set line 4
                .color() // Color user input
                .listener((player, lines) -> // Listen to user input, where player is the submitter and lines a String[4] array
                        player.sendMessage("You entered " + lines[0] + " on line 1."));

        gui.show(pl); // Show sign to player.
    }
}
