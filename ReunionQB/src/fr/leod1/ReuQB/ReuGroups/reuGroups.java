package fr.leod1.ReuQB.ReuGroups;

import fr.leod1.ReuQB.ReuParticipant.reuParticipant;

import java.util.ArrayList;

public class reuGroups {
    private String name;
    private ArrayList<reuParticipant> Membre;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<reuParticipant> getMembre() {
        return Membre;
    }

    public void setMembre(ArrayList<reuParticipant> membre) {
        Membre = membre;
    }
}
