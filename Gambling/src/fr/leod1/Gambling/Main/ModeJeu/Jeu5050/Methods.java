package fr.leod1.Gambling.Main.ModeJeu.Jeu5050;

import java.util.Random;

public class Methods {
    public static boolean Coinflip (){
        Random RandPl = new Random();
        byte Nbr = (byte) RandPl.nextInt(2);
        return Nbr == 0;
    }

}
