package fr.leod1.undercover;

import fr.leod1.undercover.UndercoverOBJ.UndercoverOBJ;
import fr.leod1.undercover.UndercoverPlayer.UndercoverPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.leod1.undercover.UnderCover.UnderMain;

public class ComandesEx implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
        if(sender instanceof Player){
            Player pl = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("Undercover")){
                if(arg.length == 0){
                    SendDesc(pl);
                }else if(arg.length == 1){
                    if(arg[0].equalsIgnoreCase("list")){
                        pl.sendMessage("---------Liste des parties-------");
                        int i = 1;
                        for (String plat : UnderMain.getAllundernames()){
                            pl.sendMessage(i +". "+ plat);
                            i++;
                        }
                    }
                } else if (arg.length == 2){
                    if(arg[0].equalsIgnoreCase("create")){
                        if(UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Cette parti exist déja");
                            return true;
                        }
                        UndercoverOBJ underOBJ = new UndercoverOBJ(false);
                        UnderMain.AddIntoAllUndercover(underOBJ,arg[1]);
                        pl.sendMessage("§8[§7Undercover§8]§3 La partie §b"+ arg[1] + "§3 a été créer");
                        return true;
                    }else if(arg[0].equalsIgnoreCase("remove")) {
                        if (!UnderMain.UndercoverExit(arg[1])) {
                            pl.sendMessage("§8[§7Undercover§8]§3 Désolé, il n'y a pas de parti de ce nom là");
                            return true;
                        }
                        UnderMain.RmIntoAllUndercover(arg[1]);
                        pl.sendMessage("§8[§7Undercover§8]§3 La partie §b"+ arg[1] + "§3 a été remove");
                    }else if (arg[0].equalsIgnoreCase("start")){
                        if (!UnderMain.UndercoverExit(arg[1])) {
                            pl.sendMessage("§8[§7Undercover§8]§3 Désolé, il n'y a pas de parti de ce nom là");
                            return true;
                        }
                        UndercoverOBJ Under = UnderMain.getUndercoverByName(arg[1]);
                        Under.setIntrus();
                        Under.StartGame();
                    }else if (arg[0].equalsIgnoreCase("NextRound")){
                        if(!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Désolé, il n'y a pas de parti de ce nom là");
                            return true;
                        }
                        UndercoverOBJ Under = UnderMain.getUndercoverByName(arg[1]);
                        Under.NextRound();
                        Under.setIntrus();
                    }else if(arg[0].equalsIgnoreCase("addspawn")){
                        if(!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Désolé, il n'y a pas de parti de ce nom là");
                            return true;
                        }
                        UndercoverOBJ Under = UnderMain.getUndercoverByName(arg[1]);
                        Under.addLocationofUnder(pl.getLocation());
                        pl.sendMessage("§8[§7Undercover§8]§3 Il y a §b" + Under.getListOfLoc().size() + " §rspawn");
                    }else if (arg[0].equalsIgnoreCase("rmspawn")){
                        if(!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Désolé, il n'y a pas de parti de ce nom là");
                            return true;
                        }
                        UndercoverOBJ Under = UnderMain.getUndercoverByName(arg[1]);
                        if (Under.getListOfLoc().size() >= 1){
                            Under.getListOfLoc().remove(Under.getListOfLoc().size() -1);
                            pl.sendMessage("§8[§7Undercover§8]§3 Il reste §b" + Under.getListOfLoc().size() + " §rspawn");
                        } else {
                            pl.sendMessage("§8[§7Undercover§8]§3 Dsl, mais il n'y a pas de spawn défini");
                        }
                    } else if (arg[0].equalsIgnoreCase("AddallPlayer")){

                        if (!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[1]);
                        for (Player pla : Bukkit.getOnlinePlayers()){
                            undercover.addPlayer(pla);
                            UnderMain.SetUndercoverPlayer(pla.getName(), new UndercoverPlayer(undercover.getListPlayerOfPart().size()+1, arg[1]));
                            pl.sendMessage("§8[§7Undercover§8]§3 Le joueur §b"+ pla.getName() +"§3 a été ajouter correctement à §b"+ arg[1]);
                        }

                    }else if (arg[0].equalsIgnoreCase("Info")){

                        if (!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[1]);
                        pl.sendMessage("Name: "+ arg[1]);
                        pl.sendMessage("Nombres de joueur: "+ undercover.getListPlayerOfPart().size());
                        pl.sendMessage("Nombres de spawn: "+ undercover.getListOfLoc().size());
                        pl.sendMessage("Nombres de mots charger: "+ undercover.getListOfWord().size());

                    }else if (arg[0].equalsIgnoreCase("InfoSpawn")){
                        if (!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[1]);
                        pl.sendMessage("---------Liste des spawns-------");
                        int i = 1;
                        for (Location loc : undercover.getListOfLoc()){
                            pl.sendMessage(i +". "+ "X: "+loc.getBlockX()+ " Y: "+ loc.getBlockY()+ " Z: "+ loc.getBlockZ());
                            i++;
                        }

                    } else if (arg[0].equalsIgnoreCase("InfoPlayer")){
                        if (!UnderMain.UndercoverExit(arg[1])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[1]);
                        pl.sendMessage("---------Liste des players-------");
                        int i = 1;
                        for (Player plat : undercover.getListPlayerOfPart()){
                            pl.sendMessage(i +". "+ plat.getName());
                            i++;
                        }
                    }else {
                        SendDesc(pl);
                        return true;
                    }
                } else if (arg.length == 3){
                    if (arg[0].equalsIgnoreCase("addplayer")){
                        if(Bukkit.getPlayerExact(arg[1]) == null){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un joueur valide");
                            return true;
                        }
                        if (!UnderMain.UndercoverExit(arg[2])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[2]);
                        Player pltoadd = Bukkit.getPlayerExact(arg[1]);
                        undercover.addPlayer(pltoadd);
                        UnderMain.SetUndercoverPlayer(arg[1], new UndercoverPlayer(undercover.getListPlayerOfPart().size()+1, arg[2]));
                        System.out.println(arg[1]+", "+ arg[2]);
                        pl.sendMessage("§8[§7Undercover§8]§3 Le joueur §b"+ pltoadd.getName() +"§3 a été ajouter correctement à §b"+ arg[2]);

                    } else if(arg[0].equalsIgnoreCase("rmplayer")){
                        if(Bukkit.getPlayerExact(arg[1]) == null){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un joueur valide");
                            return true;
                        }
                        if (!UnderMain.UndercoverExit(arg[2])){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un Undercover valide");
                            return true;
                        }
                        UndercoverOBJ undercover = UnderMain.getUndercoverByName(arg[2]);
                        Player pltorm = Bukkit.getPlayerExact(arg[1]);
                        if (!(undercover.getListPlayerOfPart().contains(pltorm))){
                            pl.sendMessage("§8[§7Undercover§8]§3 Merci de mettre un joueur qui est dans la parti");
                            return true;
                        }
                        undercover.rmPlayer(pltorm);
                        pl.sendMessage("§8[§7Undercover§8]§3 Le joueur §b"+ pltorm.getName()+"§3 a été retirer correctement à §b"+ arg[2]);
                    }else{
                        SendDesc(pl);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void SendDesc(Player pl){
        pl.sendMessage("/undercover list");
        pl.sendMessage("/undercover start <Name>");
        pl.sendMessage("/undercover create <Name>");
        pl.sendMessage("/undercover remove <Name>");
        pl.sendMessage("/undercover addspawn <Name>");
        pl.sendMessage("/undercover addplayer <NamePlayer> <Name>");
        pl.sendMessage("/undercover AddallPlayer <Name>");
        pl.sendMessage("/undercover rmplayer <NamePlayer> <Name>");
        pl.sendMessage("/undercover Infos <Name>");
        pl.sendMessage("/undercover InfosSpawn <Name>");
        pl.sendMessage("/undercover InfosPlayer <Name>");
    }
}
