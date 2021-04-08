package fr.leod1.undercover.UndercoverPlayer;

import java.util.HashMap;

public class UndercoverPlayerManager {

    private HashMap<String, UndercoverPlayer> Undercover = new HashMap<>();

    public UndercoverPlayer GetUndercoverPlayer(String nameofplayer){
        return Undercover.get(nameofplayer);
    }
    public void AddundercoverPlayer(String NameofPlayer, UndercoverPlayer ucPlayer){
        Undercover.put(NameofPlayer,ucPlayer);
    }
    public boolean IsExist(String plname){
        return Undercover.containsKey(plname);
    }
}
