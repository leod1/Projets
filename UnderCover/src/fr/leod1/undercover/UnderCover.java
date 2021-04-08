package fr.leod1.undercover;

import fr.leod1.undercover.UndercoverOBJ.UndercoverOBJ;
import fr.leod1.undercover.UndercoverPlayer.UndercoverPlayer;
import fr.leod1.undercover.UndercoverPlayer.UndercoverPlayerManager;
import fr.leod1.undercover.UndercoverWord.UndercoverWordOBJ;
import fr.leod1.undercover.event.EventMsg;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class UnderCover extends JavaPlugin {

    private ArrayList<String> Allundernames = new ArrayList<>();
    private HashMap<String,UndercoverOBJ> AllUndercover = new HashMap<>();
    private UndercoverPlayerManager UndercoverPlayerManager;
    public ArrayList<UndercoverWordOBJ> ListWords = new ArrayList<>();
    private List<String> Word1 = new ArrayList<>();
    private List<String> Word2 = new ArrayList<>();

    public static UnderCover UnderMain;

    @Override
    public void onEnable() {
        UnderMain = this;
        UndercoverPlayerManager = new UndercoverPlayerManager();
        getCommand("Undercover").setExecutor(new ComandesEx());
        Bukkit.getPluginManager().registerEvents(new EventMsg(), this);

        Random RandPl = new Random();
        byte Nbr = (byte) RandPl.nextInt(1);
        if(Nbr == 1 ){
            Word1 = this.getConfig().getStringList("Word1");
            Word2 = this.getConfig().getStringList("Word2");
        }else {
            Word2 = this.getConfig().getStringList("Word1");
            Word1 = this.getConfig().getStringList("Word2");
        }


        for (int i = 0; i < Word1.size() ;i++){
            ListWords.add(new UndercoverWordOBJ(Word1.get(i),Word2.get(i)));
        }

    }

    public ArrayList<String> getAllundernames() {
        return Allundernames;
    }

    public void RmIntoAllundernames(String s) {
        Allundernames.remove(s);
    }


    public void AddIntoAllUndercover(UndercoverOBJ undercover, String Name){
        AllUndercover.put(Name, undercover);
        Allundernames.add(Name);
    }
    public void RmIntoAllUndercover(String name){
        AllUndercover.remove(name);
        Allundernames.remove(name);
    }

    public boolean UndercoverExit(String Name){
        return AllUndercover.containsKey(Name);
    }

    public UndercoverOBJ getUndercoverByName(String Name){
        return AllUndercover.get(Name);
    }

    public UndercoverPlayer GetUndercoverPlayerByname (String NamePlayer){
        return UndercoverPlayerManager.GetUndercoverPlayer(NamePlayer);
    }
    public void SetUndercoverPlayer(String Name, UndercoverPlayer UCPlayer){
        UndercoverPlayerManager.AddundercoverPlayer(Name,UCPlayer);
    }
    public boolean UndercoverPlayerExist(String Name){
        return UndercoverPlayerManager.IsExist(Name);
    }
}
