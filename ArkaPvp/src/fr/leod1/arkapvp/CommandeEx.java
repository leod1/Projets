package fr.leod1.arkapvp;

import com.mojang.authlib.GameProfile;
import fr.leod1.arkapvp.arkapvpgames.ArkaPvpGames;
import fr.leod1.arkapvp.arkapvpkitformat.ArkaPvpKitFormat;
import fr.leod1.arkapvp.arkapvpstatuesgames.ArkaPvpStatuesGames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static fr.leod1.arkapvp.ArkaPvp.InstanceOfMain;

public class CommandeEx implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] arg) {
        if(sender instanceof Player){
            ArkaPvpGames Games = InstanceOfMain.arkaPvpGames;
            Player pl = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("Arkagames")){
                if(arg.length == 0){
                    Helpmessage(pl);
                } else if (arg.length == 1){
                    if(arg[0].equalsIgnoreCase("Infos")){
                        pl.sendMessage("------ Infos de la partie ------");
                        if (Games.getLobby() != null){
                            pl.sendMessage("Lobby: X ="+Games.getLobby().getBlockX() +" Y ="+Games.getLobby().getBlockY()+" Z="+ Games.getLobby().getBlockZ());
                        }else {
                            pl.sendMessage("Lobby: Pas set");
                        }
                        if (Games.getBanerRed() != null){
                            pl.sendMessage("Bannière Red: X ="+Games.getBanerRed().getBlockX() +" Y ="+Games.getBanerRed().getBlockY()+" Z="+ Games.getBanerRed().getBlockZ());
                        }else {
                            pl.sendMessage("Bannière Red: Pas set");
                        }
                        if (Games.getBannerBlue() != null){
                            pl.sendMessage("Bannière Blue: X ="+Games.getBannerBlue().getBlockX() +" Y ="+Games.getBannerBlue().getBlockY()+" Z="+ Games.getBannerBlue().getBlockZ());
                        }else {
                            pl.sendMessage("Bannière Blue: Pas set");
                        }
                        if (Games.getKit() != null){
                            pl.sendMessage("Kit: set");
                        }else {
                            pl.sendMessage("Kit: Pas set");
                        }
                        pl.sendMessage("Il y a "+ Games.getAllPlayerInPart().size() +" joueur(s) dans la partie");
                        return true;
                    }else if(arg[0].equalsIgnoreCase("setkit")){
                        ArkaPvpKitFormat kit = new ArkaPvpKitFormat();
                        kit.setKitByPlayer(pl);
                        Games.setKit(kit);
                        pl.sendMessage("Le kit à bien été set");
                    }else if(arg[0].equalsIgnoreCase("setLobby")){
                        Games.setLobby(pl.getLocation());
                        pl.sendMessage("Le lobby à bien été set");
                    }else if(arg[0].equalsIgnoreCase("setbannerBlue")){
                        Games.setBannerBlue(pl.getLocation());
                        pl.sendMessage("La bannière bleu à bien été set");
                    }else if(arg[0].equalsIgnoreCase("setbannerRed")){
                        Games.setBanerRed(pl.getLocation());
                        pl.sendMessage("La bannière rouge  à bien été set");
                    }else if (arg[0].equalsIgnoreCase("start")){
                        InstanceOfMain.StartGame(pl);
                    }
                } else if(arg.length == 2){
                    if(arg[0].equalsIgnoreCase("setteamred")){
                        if(!(Games.getGamesStatues() == ArkaPvpStatuesGames.Lobby)){
                            pl.sendMessage("Désolé, la partie a déjà commencé");
                            return true;
                        }
                        if (Bukkit.getPlayerExact(arg[1]) == null){
                            pl.sendMessage("Merci de mettre un joueur valide");
                            return true;
                        }
                        Player target = Bukkit.getPlayerExact(arg[1]);
                        if (!Games.getAllPlayerInPart().contains(target)){
                            pl.sendMessage("Ce joueur n'est pas dans la partie");
                            return true;
                        }
                        if (Games.getTeamRed().size() >= 6){
                           pl.sendMessage("La team rouge est plein");
                           return true;
                        }
                        if (Games.getTeamRed().contains(target)){
                            pl.sendMessage("Ce joueur est déjà dans la team rouge");
                            return true;
                        }
                        ////

                        if(Games.getTeamBlue().contains(target)){
                            Games.getTeamBlue().remove(target);
                        }
                        Games.getTeamRed().add(target);
                        pl.sendMessage("Le joueur "+target.getName()+" a été ajouter à l'équipe rouge");
                    } else if(arg[0].equalsIgnoreCase("setteamblue")){
                        if(!(Games.getGamesStatues() == ArkaPvpStatuesGames.Lobby)){
                            pl.sendMessage("Désolé, la partie a déjà commencé");
                            return true;
                        }
                        if (Bukkit.getPlayerExact(arg[1]) == null){
                            pl.sendMessage("Merci de mettre un joueur valide");
                            return true;
                        }
                        Player target = Bukkit.getPlayerExact(arg[1]);
                        if (!Games.getAllPlayerInPart().contains(target)){
                            pl.sendMessage("Ce joueur n'est pas dans la partie");
                            return true;
                        }
                        if (Games.getTeamBlue().size() >= 6){
                            pl.sendMessage("La team blue est plein");
                            return true;
                        }
                        if (Games.getTeamBlue().contains(target)){
                            pl.sendMessage("Ce joueur est déjà dans la team blue");
                            return true;
                        }
                        ////

                        if(Games.getTeamRed().contains(target)){
                            Games.getTeamRed().remove(target);
                        }
                        Games.getTeamBlue().add(target);
                        pl.sendMessage("Le joueur "+target.getName()+" a été ajouter à l'équipe blue");
                    }

                }



            }
        }
        return false;
    }

    private void Helpmessage(Player pl){
        pl.sendMessage("/arkagames ");
        pl.sendMessage("/arkagames Infos");
        pl.sendMessage("/arkagames setkit");
        pl.sendMessage("/arkagames setLobby");
        pl.sendMessage("/arkagames setbannerBlue");
        pl.sendMessage("/arkagames setbannerRed");
        pl.sendMessage("/arkagames setteamred <player>");
        pl.sendMessage("/arkagames setteamblue <player>");
        pl.sendMessage("/arkagames Start");
    }
}
