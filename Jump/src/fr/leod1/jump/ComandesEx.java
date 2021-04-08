package fr.leod1.jump;

import com.sun.media.sound.InvalidFormatException;
import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.Utils.JumpLoc;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class ComandesEx implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (sender instanceof Player) {

            Player pl = (Player) sender;

            if (cmd.getName().equalsIgnoreCase("Jump")) {

                if (!pl.hasPermission("jump.use") && !pl.hasPermission("*")) return true;

                if (args.length == 0) {//§a[§bJump§a]

                    pl.sendMessage("§a[§bJump§a] §r/jump  §7");
                    pl.sendMessage("§a[§bJump§a] §r/jump save §8Permet de sauvegarder les jumps.");
                    pl.sendMessage("§a[§bJump§a] §r/jump list §8Permet de lister les jumps sur le serveur.");
                    pl.sendMessage("§a[§bJump§a] §r/jump create/Delete §7<Nom du jump>  §8Permet de créer ou de supprimer un jump.");
                    pl.sendMessage("§a[§bJump§a] §r/jump set §7<Finish/Start/Holo> §7<Nom du jump> §8Permet de définir un début et une fin et l'hologramme su jump.");
                    pl.sendMessage("§a[§bJump§a] §r/jump addcp §7<Nom du jump>   §8Permet d’ajouter un checkpoint au jump.");
                    pl.sendMessage("§a[§bJump§a] §r/jump rmcp §7<Nom du jump>  §8Permet d’enlever un checkpoint au jump.");
                    pl.sendMessage("§a[§bJump§a] §r/jump cpList §7<Nom du jump> §8Permet de lister les checkpoints d'un jump.");
                    pl.sendMessage("§a[§bJump§a] §r/jump infos §7<Nom du jump> §8Permet d'avoire toutes les infos d'un jump.");


                } else if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("list")) {
                        for (JumpObj jumpObj : Jump.jumpsManagers.getJumpObjs()) {
                            pl.sendMessage("§a[§bJump§a] §6" + jumpObj.getName());
                        }
                    } else if (args[0].equalsIgnoreCase("save")) {
                        try {
                            Jump.SQLite.updateDB();
                            pl.sendMessage("§a[§bJump§a] §bSauvegarde des Jumps terminées !");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } catch (InvalidFormatException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (args.length == 2) {

                    if (args[0].equalsIgnoreCase("Create")) {

                        if (Jump.jumpsManagers.isJumpObj(args[1])) {
                            pl.sendMessage("§a[§bJump§a] §bCourse deja créé !");
                        } else {
                            Jump.jumpsManagers.addJumpObj(new JumpObj(args[1]));
                            pl.sendMessage("§a[§bJump§a] §bCourse §r" + args[1] + " §bcorrectement créé");
                        }

                    } else if (args[0].equalsIgnoreCase("Delete")) {

                        if (Jump.jumpsManagers.isJumpObj(args[1])) {
                            Jump.jumpsManagers.removeJupsObj(args[1]);
                            pl.sendMessage("§a[§bJump§a] §bCourse §r" + args[1] + " §ba bien été supprimée !");
                        } else {
                            pl.sendMessage("§a[§bJump§a] §bLa course §r" + args[1] + " §bn'existe pas !");
                        }

                    } else if (args[0].equalsIgnoreCase("addcp")) {

                        String name = args[1];
                        JumpLoc jumpLoc = new JumpLoc(pl.getLocation());
                        JumpObj jump = Jump.jumpsManagers.getJumpObj(name);
                        jump.addCheckPoint(jumpLoc);

                        pl.sendMessage("§a[§bJump§a] §bCherckpoint §r" + jump.getCheckPoints().size() + " §bajouté avec succes !");

                    } else if (args[0].equalsIgnoreCase("removecp") || args[0].equalsIgnoreCase("rmcp")) {

                        int index = 0;
                        String name = args[1];
                        JumpLoc jumpLoc = new JumpLoc(pl.getLocation());
                        JumpObj jump = Jump.jumpsManagers.getJumpObj(name);
                        if (jump.hasCheckPoint(jumpLoc)) {


                            index = jump.getIndexOfCheckPoint(jumpLoc);
                            jump.removeCheckPoints(jumpLoc);
                        } else {
                            if(!(jump.getCheckPoints().size() <= 0)){
                                jump.removeCheckPoints(jump.getCheckPoints().size() - 1);
                                index = jump.getCheckPoints().size();
                            }else {
                                pl.sendMessage("§a[§bJump§a] ");
                            }
                        }
                        pl.sendMessage("§a[§bJump§a] §bCherckpoint §r" + index + " §bsupprimé avec succes !");

                    } else if (args[0].equalsIgnoreCase("cpList") || args[0].equalsIgnoreCase("checkPointsList")) {

                        int i = 0;
                        for (JumpLoc jumpLoc : Jump.jumpsManagers.getJumpObj(args[1]).getCheckPoints()) {
                            i++;
                            pl.sendMessage("§a[§bJump§a] §bCheckPoint numéro §9:§r " + i + " ( " + jumpLoc.getX() + " " + jumpLoc.getY() +  " " + jumpLoc.getZ() + " )");
                        }

                    } else if (args[0].equalsIgnoreCase("Info") || args[0].equalsIgnoreCase("Infos")){
                        if (args[1] == null){
                            pl.sendMessage("§a[§bJump§a] §bMerci de mettre un nom");
                            return true;
                        }
                        String name = args[1];
                        JumpObj jump = Jump.jumpsManagers.getJumpObj(name);

                        if (jump != null) {
                            pl.sendMessage("§a[§bJump§a]§b--------------");
                            pl.sendMessage("§bName : §r" + jump.getName());
                            if (jump.getStart() != null){
                                pl.sendMessage("§bStart : §r" + jump.getStart().getWorldName() + " §b/§r " + jump.getStart().getX() + " §b/§r "+ jump.getStart().getY() + " §b/§r "+ jump.getStart().getZ());
                            }else {
                                pl.sendMessage("§bStart : §rStart non défini");
                            }
                            if (jump.getFinish() != null){
                                pl.sendMessage("§bFinish : §r" + jump.getFinish().getWorldName() + " §b/§r " + jump.getFinish().getX() + " §b/§r "+ jump.getFinish().getY() + " §b/§r "+ jump.getFinish().getZ());
                            }else {
                                pl.sendMessage("§bFinish : §rFinish non défini");
                            }
                            if (jump.getHolo() != null){
                                pl.sendMessage("§bHolo : §r" + jump.getHolo().getWorldName() + " §b/§r " + jump.getHolo().getX() + " §b/§r "+ jump.getHolo().getY() + " §b/§r "+ jump.getHolo().getZ());
                            }else {
                                pl.sendMessage("§bHolo : §rHolo non défini");
                            }
                            pl.sendMessage("§bNb de cp : §r" + String.valueOf(jump.getCheckPoints().size()));
                            pl.sendMessage("§a[§bJump§a]§b--------------");
                        } else {
                            pl.sendMessage("§a[§bJump§a] §bLe jump §r"+ args[1]+"§b n'existe pas.");
                        }

                    }

                } else if (args.length == 3) {

                    if (args[0].equalsIgnoreCase("set")) {
                        if (args[1].equalsIgnoreCase("Start")) {
                            if (Jump.jumpsManagers.isJumpObj(args[2])){
                                Jump.jumpsManagers.getJumpObj(args[2]).setStart(new JumpLoc(pl.getLocation()));
                                pl.sendMessage("§a[§bJump§a] §bLe Start de §r"+args[2]+" §b à bien été défini");
                            } else {
                                pl.sendMessage("§a[§bJump§a] §bCourse §r"+args[2]+" §bnon créé !");
                            }
                        } else if (args[1].equalsIgnoreCase("Finish")) {
                            if(Jump.jumpsManagers.isJumpObj(args[2])) {
                                Jump.jumpsManagers.getJumpObj(args[2]).setFinish(new JumpLoc(pl.getLocation()));
                                pl.sendMessage("§a[§bJump§a] §bLe Finish de §r"+args[2]+" §b à bien été défini");
                            } else {
                                pl.sendMessage("§a[§bJump§a] §bCourse §r"+args[2]+" §bnon créé !");
                            }
                        } else if (args[1].equalsIgnoreCase("holo") || args[1].equalsIgnoreCase("holograme")) {
                            if (Jump.jumpsManagers.isJumpObj(args[2])) {
                                Jump.jumpsManagers.getJumpObj(args[2]).setHolo(new JumpLoc(pl.getLocation().getX(),pl.getLocation().getY(),pl.getLocation().getZ(),pl.getWorld().getName()));
                                pl.sendMessage("§a[§bJump§a] §bPosition de l'holograme de la course §r"+args[2]+" §bdéfini");
                            } else {
                                pl.sendMessage("§a[§bJump§a] §bCourse §r "+args[2]+" §bnon créé !");
                            }
                        }

                    } else if (args[0].equalsIgnoreCase("removecp") || args[0].equalsIgnoreCase("rmcp")) {

                        String name = args[1];
                        JumpObj jump = Jump.jumpsManagers.getJumpObj(name);

                        if (args[2].matches("[0-9]*")) {
                            int index = Integer.parseInt(args[2]);
                            if (index <= (jump.getCheckPoints().size())) {
                                jump.getCheckPoints().remove(index -1);
                                pl.sendMessage("§a[§bJump§a] §bCheckpoint §r" + (index) + " §bsupprimé avec succes !");
                            } else {
                                pl.sendMessage("§a[§bJump§a] §bVous devez rentrer un numéro de checkpoint valide !");
                            }
                        } else {
                            pl.sendMessage("§a[§bJump§a] §bVous devez rentrer un numéro de checkpoint a supprimer !");
                        }

                    }

                } else {
                    pl.sendMessage("§a[§bJump§a] §bLa commande /jump ne peut pas avoir plus de 4 parametres !");

                }

                try {
                    Jump.SQLite.updateDB();
                    pl.sendMessage("§a[§bJump§a] §adatabase reload");
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (InvalidFormatException e) {
                    e.printStackTrace();
                }
                return true;

            }
        }
        return false;
    }
}
