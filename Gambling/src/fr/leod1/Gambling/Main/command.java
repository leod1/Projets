package fr.leod1.Gambling.Main;

import fr.leod1.Gambling.Main.ModeJeu.Jeu5050.Core;
import fr.leod1.Gambling.Main.Request.Request;
import fr.leod1.Gambling.Main.Request.RequestManager;
import fr.leod1.Gambling.Main.Gui.GuiMethods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class command implements CommandExecutor {
    public static RequestManager requestManager = new RequestManager();


    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String label, String[] arg) {
        if (Sender instanceof Player) {
            Player P = (Player) Sender;
            if (P != null) {

                if (cmd.getName().equalsIgnoreCase("Coinflip")) {
                    if (arg.length == 0) {
                        GuiMethods.GuiTeteServeur(P);
                    } else if (arg.length == 1) {
                        if (arg[0].equals("accept")) {
                            if (requestManager.getRequestsOfRequestingPlayer(P).isEmpty()) {
                                P.sendMessage("§7[§bCoinflip§7] §cDésolé, aucun joueur ne t'a envoyé de défi.");
                            } else {
                                String Pmode = requestManager.getRequestsOfRequestingPlayer(P).get(0).getGameMode();
                                Player Pask = requestManager.getRequestsOfRequestingPlayer(P).get(0).getTargetedPlayer();
                                int mise = requestManager.getRequestsOfRequestingPlayer(P).get(0).getAmount();

                                if (Pmode.equalsIgnoreCase("coinflip")) {
                                    Core.PrincipalCoinflip(P, Pask, mise);
                                } else if (Pmode.equalsIgnoreCase("f")) {


                                }

                            }
                        }
                    } else if (arg.length == 2) {


                    } else if (arg.length == 3) {
                        String Pname = P.getName();
                        if (method.online(arg[0], Pname)) {
                            if (method.isInt(arg[2])) {
                                int qzd = Integer.valueOf(arg[2]);
                                Player Reqest = Bukkit.getPlayer(arg[0]);
                                if (requestManager.addRequest(new Request(Reqest, P, qzd, (arg[1]), 60 * 1000))) {
                                    P.sendMessage("§7[§bCoinflip§7] §cLe défi a bien été envoyé à §7" + Reqest.getName());
                                    P.sendMessage("§7[§bCoinflip§7] §cTu as défié ce joueur dans le mode §7" + arg[1] + " §cavec §7" + qzd + " §cde mise");
                                    P.sendMessage("§7[§bCoinflip§7] §cIl a 60 secondes pour accepter");


                                    Reqest.sendMessage("§7[§bCoinflip§7] §cLe joueur §7" + P.getName() + " §ct'a envoyé un défi.");
                                    Reqest.sendMessage("§7[§bCoinflip§7] §cCe joueur te défi dans le mode §7" + arg[1] + " §cavec la mise de §7" + qzd);
                                    Reqest.sendMessage("§7[§bCoinflip§7] §cTu as 60 secondes pour l'accepter");
                                    Reqest.sendMessage("§7[§bCoinflip§7] §cPour accepter son défi la commande est §a/gambling accept");
                                }
                            } else {
                                P.sendMessage("§7[§bCoinflip§7] §cMerci de mettre un montant valide !");
                            }

                        } else {
                            P.sendMessage("§7[§bCoinflip§7] §cMerci de mettre un joueur connecté ! ");
                        }

                    }

                }
            }
        }
        return false;
    }
}
