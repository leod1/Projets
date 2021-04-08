package fr.leod1.QbTeleporte.QbProjects;

import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.QbUnderProject;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class QbProjects {

    private String name;
    private String nameHead;
    private Categorie Categorie;
    private int Itemviews;
    private Short MetaData;
    private Motivations motivation;


    private ArrayList<QbUnderProject> underQbProjet;


    public QbProjects(String name, fr.leod1.QbTeleporte.QbProjects.Categorie categorie, ItemStack item, ArrayList<QbUnderProject> underQbProjet,Motivations motiv) {
        this.name = name;
        Categorie = categorie;
        setItemvie(item);
        this.underQbProjet = underQbProjet;
        motivation = motiv;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public fr.leod1.QbTeleporte.QbProjects.Categorie getCategorie() {
        return Categorie;
    }

    public void setCategorie(fr.leod1.QbTeleporte.QbProjects.Categorie categorie) {
        Categorie = categorie;
    }

    public void setItemvie(ItemStack item){
        Itemviews =  item.getType().getId();
        MetaData = item.getDurability();
    }

    public ItemStack getItemviews() {
        if (MetaData == null){
            MetaData = 0;
        }
        return new ItemStack(Material.getMaterial(Itemviews),1,MetaData);
    }

    public void setItemviews(int itemviews) {
        Itemviews = itemviews;
    }

    public Short getMetaData() {
        return MetaData;
    }

    public void setMetaData(Short metaData) {
        MetaData = metaData;
    }


    public ArrayList<QbUnderProject> getUnderQbProjet() {
        return underQbProjet;
    }

    public void setUnderQbProjet(ArrayList<QbUnderProject> underQbProjet) {
        this.underQbProjet = underQbProjet;
    }

    /*private void stockageItemStacks(ItemStack item){
        if (item.getType() == Material.SKULL_ITEM && ){
            nameHead = SkullType
        }

    }*/


    public String getStringMotivation(){
        if (motivation == Motivations.Commision){
            return "Commision";
        } else if (motivation == Motivations.MarketPlace){
            return "Market Place";
        } else if (motivation == Motivations.Projet){
            return "Projet";
        }
        return "";
    }

    public QbUnderProject getUnderProjectByName(String name){
        for (QbUnderProject underproj : underQbProjet){
            if (underproj.getName().equalsIgnoreCase(name)){
                return underproj;
            }
        }
        return null;
    }
}
