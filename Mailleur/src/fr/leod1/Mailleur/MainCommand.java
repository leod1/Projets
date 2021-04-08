package fr.leod1.Mailleur;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MainCommand implements CommandExecutor {
	@SuppressWarnings("deprecation")
	@Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("spawnbot")) {
        	if (!(sender instanceof Player)) {
        		sender.sendMessage("You must be a player to perform this command");
        		return true;
        	}
        	
        	if (!performPermission(sender, "playersim.spawnbot")) return true;
        	Player p = (Player) sender;
            int num = 1;
            if (args.length > 0) {
            	try {
            		num = Integer.parseInt(args[0]);
            	} catch (Exception e) {
            		if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
            			sender.sendMessage("Player is already online");
            			return true;
            		}
            		
            		new Bot(p, args[0]).spawn(p.getLocation());
            		return true;
            	}
            }
            
            for (int i = 0; i < num; i++) {
            	new Bot(p).spawn(p.getLocation());
            }
        } else if (command.getName().equalsIgnoreCase("killbots")) {
        	if (!performPermission(sender, "playersim.killbots")) return true;
        	if (!Bot.bots.isEmpty()) {
	            for (Bot bot : Bot.bots) {
	            	bot.disconnect(false);
	            }
	            
	            Bot.bots.clear();
        	}
        	
        	sender.sendMessage( "Disconnected all bots");
        } else if (command.getName().equalsIgnoreCase("killbot")) {
        	if (!performPermission(sender, "playersim.killbot")) return true;
        	if (args.length < 1) {
        		sender.sendMessage( "Must specify §eBot Name");
        		return true;
        	}
        	
        	String botName = args[0];
        	for (Bot b : Bot.bots) {
        		if (b.getBot().getName().equals(botName)) {
        			b.disconnect(true);
        			sender.sendMessage("Disconnected§e " + botName);
        			return true;
        		}
        	}
        	
        	sender.sendMessage("Could not find bot §e" + botName);
        } else if (command.getName().equalsIgnoreCase("editbot")) {
        	if (!performPermission(sender, "playersim.editbot")) return true;
        	if (!(sender instanceof Player)) {
        		sender.sendMessage( "You must be a player to perform this command");
        		return true;
        	}
        	
        	if (args.length < 1) {
        		sender.sendMessage( "Must specify §eBot Name");
        		return true;
        	}
        	
        	String botName = args[0];
        	Bot bot = null;
        	for (Bot b : Bot.bots) {
        		if (b.getBot().getName().equalsIgnoreCase(botName)) bot = b;
        	}
        	
        	if (bot == null) {
        		sender.sendMessage( "Could not find bot §e" + botName);
        		return true;
        	}
        	

        }
        
        return true;
    }
	
	public static boolean performPermission(CommandSender sender, String permission) {
		if (!sender.hasPermission(permission)) {
			sender.sendMessage("You don't have permission to perform this command");
			return false;
		}
		
		return true;
	}
}
