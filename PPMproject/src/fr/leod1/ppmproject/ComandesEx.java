package fr.leod1.ppmproject;

import fr.leod1.ppmproject.DB.fileutils.RandomString;
import fr.leod1.ppmproject.Inventorygui.CreationGUI;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMcreatorProject;
import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import fr.leod1.ppmproject.ppmunderproject.PPMUnderProject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class ComandesEx implements CommandExecutor {
    public CreationGUI gui = new CreationGUI();
    public RandomString aleo = new RandomString();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
        if (sender instanceof Player){
            Player pl = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("project") || cmd.getName().equalsIgnoreCase("pc")){
                if (arg.length == 0){
                    Help(pl);
                }else if (arg.length == 1){
                    if (arg[0].equalsIgnoreCase("create") || arg[0].equalsIgnoreCase("c")) {
                       gui.CreateInventory(pl);
                    } else if (arg[0].equalsIgnoreCase("list") || arg[0].equalsIgnoreCase("l")){
                        gui.ListInventory(pl);
                    } else if (arg[0].equalsIgnoreCase("test")){
                        pl.sendMessage("OKOK");
                        for (int i = 0; i < 50; i++){
                            InstanceOfMain.addProjets(new PPMprojects(new Date(), "Commition","Terraforming",aleo.getAlphaNumericString(10),new Location(Bukkit.getWorld("world"),0,70,0),"leod1",new ArrayList<String>(),new ItemStack(Material.GRASS),new ArrayList<PPMUnderProject>()));
                            //new ItemStack(Material.getMaterial((int) Math.round(Math.random() * 100)))
                        }
                    }
                }
            }
        } else {
            System.out.println(InstanceOfMain.getProjects().size());
        }
        return false;
    }
    public void Help(Player pl){

        pl.sendMessage("/project create");
        pl.sendMessage("/project list");
    }
}
