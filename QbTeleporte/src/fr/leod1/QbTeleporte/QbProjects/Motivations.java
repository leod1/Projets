package fr.leod1.QbTeleporte.QbProjects;

public enum Motivations {

    Commision ("Commision"),
    Projet ("Projet"),
    MarketPlace ("MarketPlace");

    private String abreviation;


    private Motivations(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getAbreviation() {
        return  this.abreviation ;
    }

}
