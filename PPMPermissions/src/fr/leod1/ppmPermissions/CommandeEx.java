package fr.leod1.ppmPermissions;

import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.inventory.ColorHeads;
import fr.leod1.ppmPermissions.inventory.InventoryMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class CommandeEx implements CommandExecutor {
    InventoryMain invMain = new InventoryMain();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("perm")) {
                invMain.MainInventory(pl);
            }
            return false;
        }
        return false;
    }
}
