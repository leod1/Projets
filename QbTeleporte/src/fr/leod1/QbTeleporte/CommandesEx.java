package fr.leod1.QbTeleporte;

import fr.leod1.QbTeleporte.QbInventory.CreationGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandesEx implements CommandExecutor {
    private CreationGui gui = new CreationGui();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            /*if (pl.getItemInHand().getType() == Material.SKULL_ITEM){
                SkullMeta skullMeta = (SkullMeta) pl.getItemInHand().getItemMeta();
                pl.sendMessage(skullMeta.getOwner());
                return true;
            }*/
            if (cmd.getName().equalsIgnoreCase("qbc")) {
                gui.CreateProject(pl);
            } else if (cmd.getName().equalsIgnoreCase("qbl")) {
                gui.getListgui().SelectionList(pl);
            }
        }
        return false;
    }
}
