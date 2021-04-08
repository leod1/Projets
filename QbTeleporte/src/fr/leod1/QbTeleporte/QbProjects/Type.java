package fr.leod1.QbTeleporte.QbProjects;

public enum Type {

    BigProjet ("Gros projet"),
    Structure ("Structure"),
    Organique ("MarketPlace"),
    Terraforming ("MarketPlace"),
    Arbre ("Arbre");

    private String abreviation;


    private Type(String abreviation) {
        this.abreviation = abreviation ;
    }

    public String getAbreviation() {
        return  this.abreviation ;
    }
}
