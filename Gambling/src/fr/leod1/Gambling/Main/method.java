package fr.leod1.Gambling.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class method {

    public static boolean online (String Arg1, String NameSender){
        for (Player P : Bukkit.getOnlinePlayers()) {
            String okok = P.getName();
            if(okok.equals(Arg1)){
                if(NameSender.equals(okok)){
                    return false;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }



}
