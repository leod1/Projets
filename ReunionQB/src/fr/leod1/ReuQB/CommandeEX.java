package fr.leod1.ReuQB;

import fr.leod1.ReuQB.Inventory.Accueil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandeEX implements CommandExecutor {
    Accueil gui = new Accueil();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("Reunion")) {
                gui.createAccueil(pl);
            }
            return false;
        }
        return false;
    }
}
