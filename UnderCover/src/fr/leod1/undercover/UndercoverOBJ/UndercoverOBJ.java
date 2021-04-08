package fr.leod1.undercover.UndercoverOBJ;

import fr.leod1.undercover.UndercoverWord.UndercoverWordOBJ;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

import static fr.leod1.undercover.UnderCover.UnderMain;

public class UndercoverOBJ {

    private ArrayList<Location> ListOfSpawn;
    private ArrayList<Player> ListPlayerOfPart;
    private ArrayList<UndercoverWordOBJ> ListofwordObj;
    private int Statues;
    private ArrayList<Player> ListOfAllPlayerNotIntrus;
    private Player Intrus;
    private Player OldIntrus;
    private boolean gameStart;

    public UndercoverOBJ(boolean gameStarte) {
        gameStart = gameStarte;
        ListPlayerOfPart = new ArrayList<>();
        ListOfAllPlayerNotIntrus = new ArrayList<>();
        ListofwordObj = new ArrayList<>();
        ListOfSpawn = new ArrayList<>();

    }
    public void addLocationofUnder(Location location){
        ListOfSpawn.add(location);
    }
    public ArrayList<Location> getListOfLoc(){
        return ListOfSpawn; }
    public ArrayList<UndercoverWordOBJ> getListOfWord(){
        return ListofwordObj;
    }

    public int getStatues() {
        return Statues;
    }

    public void setStatues(int statues) {
        Statues = statues;
    }

    public ArrayList<Player> getListPlayerOfPart() {
        return ListPlayerOfPart;
    }

    public void setIntrus() {
        Random RandPl = new Random();
        byte Nbr = (byte) RandPl.nextInt(ListPlayerOfPart.size());

        Intrus = ListPlayerOfPart.get(Nbr);
        ListOfAllPlayerNotIntrus.addAll(ListPlayerOfPart);
        ListOfAllPlayerNotIntrus.remove(Intrus);
    }

    public void addPlayer(Player p){
        ListPlayerOfPart.add(p);
    }
    public void rmPlayer(Player p){
        ListPlayerOfPart.remove(p);
    }
    public void StartGame() {
        if(this.gameStart){
            return;
        }
        if(ListPlayerOfPart.size() < 2){
            return;
        }
        if (!(Intrus == null)){
            OldIntrus = Intrus;
        }
        if (ListOfSpawn.size() < ListPlayerOfPart.size()){
            return;
        }
        for (int i = 0; i < ListPlayerOfPart.size(); i++){
            Player pl = ListPlayerOfPart.get(i);
            Location Loc = ListOfSpawn.get(i);
            pl.teleport(Loc);
        }
        this.setIntrus();
        this.Statues = 0;
        this.gameStart = true;
        ListofwordObj.addAll(UnderMain.ListWords);

        Random RandPl = new Random();
        byte Nbr = (byte) RandPl.nextInt(ListofwordObj.size());
        UndercoverWordOBJ words = ListofwordObj.get(Nbr);

        for (Player pl : ListPlayerOfPart){
            for (int r=0; r <100; r++){
                pl.sendMessage("");
            }
        }
        if(!(OldIntrus == null)){
            for (Player pl: getListPlayerOfPart()){
                pl.sendMessage("§6L'Intrus était: §c"+ OldIntrus.getName());
            }
        }

        for (Player pl : ListOfAllPlayerNotIntrus){
            pl.sendMessage("§8ton mot est: §c"+ words.getWord1());
        }
        Intrus.sendMessage("§8ton mot est: §c"+words.getWord2());
        ListofwordObj.remove(words);


        for (int i = 0; i < ListPlayerOfPart.size(); i++){
            Player pl = ListPlayerOfPart.get(i);
            UnderMain.GetUndercoverPlayerByname(pl.getName()).setIdStatues(i);
            pl.sendMessage("§3Le premier a parler est: §c"+ ListPlayerOfPart.get(0).getName());
            pl.sendMessage("");
            pl.sendMessage("");
        }
        return;
    }
    public void NextRound(){
        if(!this.gameStart){
            return;
        }
        if(ListPlayerOfPart.size() < 2){
            return;
        }
        StopGame();
        StartGame();
        return;



    }
    public void StopGame(){
        this.gameStart = false;
        Intrus = null;
        ListofwordObj.clear();
        ListOfAllPlayerNotIntrus.clear();
    }
}
