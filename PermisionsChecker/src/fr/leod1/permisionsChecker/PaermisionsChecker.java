package fr.leod1.permisionsChecker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PaermisionsChecker extends JavaPlugin implements CommandExecutor {
    @Override
    public void onEnable() {
        getCommand("permcheck").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player pl = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("permcheck")) {
                if (Bukkit.getPlayerExact(args[0]).hasPermission(args[1])){
                    pl.sendMessage("§cLe joueur §6" +args[0]+" §ca la permisions §b" +args[1]);
                } else {
                    pl.sendMessage("§cLe joueur §6" +args[0]+" §cn'a pas la permisions §b" +args[1]);
                }
            }
        }
        return false;
    }
}
