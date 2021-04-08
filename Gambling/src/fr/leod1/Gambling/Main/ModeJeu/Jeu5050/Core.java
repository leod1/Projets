package fr.leod1.Gambling.Main.ModeJeu.Jeu5050;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static fr.leod1.Gambling.Main.command.requestManager;

public class Core {

    public static void PrincipalCoinflip(Player P, Player Pask, int mise ) {
        String Pname = P.getName();
        String Paskname = Pask.getName();
        P.sendMessage("§7[§bCoinflip§7] §cTu as bien accepté la requete de "+ Pask.getName());
        Pask.sendMessage("§7[§bCoinflip§7] §cLa requete à §7" + P.getName() +" §cest bien accepté.");


        requestManager.removeRequests(requestManager.getRequestsInCommon(Pask,P));
        requestManager.removeRequests(requestManager.getRequestsInCommon(P,Pask));

        if (Methods.Coinflip()){
            P.sendMessage("§7[§bCoinflip§7] §cBien jouer §a"+ P.getName() +" §c.Tu viens de remporter§7 "+mise+" $");
            Pask.sendMessage("§7[§bCoinflip§7] §cDésolé, tu viens de perdre le 5050");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + Paskname + " " + mise);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + Pname + " " + mise);
        } else {
            Pask.sendMessage("§7[§bCoinflip§7] §cBien jouer §a"+ P.getName() +" §c.Tu viens de remporter§7 "+mise+" $");
            P.sendMessage("§7[§bCoinflip§7] §cDésolé, tu viens de perdre le 5050");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + Paskname + " " + mise);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco take " + Pname + " " + mise);
        }
    }
}
