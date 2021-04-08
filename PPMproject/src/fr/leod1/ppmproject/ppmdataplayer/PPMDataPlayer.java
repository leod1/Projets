package fr.leod1.ppmproject.ppmdataplayer;

import java.util.ArrayList;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class PPMDataPlayer {

    private ArrayList<String> Favori;
    private ArrayList<String> projetAllowToPlayer;
    private int projetCreate;
    //StatOfPllayer


    public PPMDataPlayer() {
        Favori = new ArrayList<>();
        projetAllowToPlayer = new ArrayList<>();
        projetCreate = 0;
    }

    public PPMDataPlayer(ArrayList<String> favori, int projetcreate, ArrayList<String> projetAllowToPlayerr) {
        Favori = favori;
        projetCreate = projetcreate;
        projetAllowToPlayer = projetAllowToPlayerr;
    }

    public void sortrmproject(){
        for (String st: Favori){
            if (!(InstanceOfMain.containProjectbyName(st))){
                Favori.remove(st);
            }
        }
        return;
    }

    public ArrayList<String> getFavori() {
        return Favori;
    }

    public void setFavori(ArrayList<String> favori) {
        Favori = favori;
    }

    public int getProjetCreate() {
        return projetCreate;
    }

    public void setProjetCreate(int projetCreate) {
        this.projetCreate = projetCreate;
    }

    public ArrayList<String> getProjetAllowToPlayer() {
        return projetAllowToPlayer;
    }

    public void setProjetAllowToPlayer(ArrayList<String> projetAllowToPlayer) {
        this.projetAllowToPlayer = projetAllowToPlayer;
    }
}
