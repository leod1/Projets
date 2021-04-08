package fr.leod1.QbTeleporte.QbPlayer;

import fr.leod1.QbTeleporte.QbProjects.Categorie;
import fr.leod1.QbTeleporte.QbProjects.Motivations;
import org.bukkit.inventory.ItemStack;

public class QbPlayer {

    private SelectionOnCreator selection;
    private String NameProject;
    private Motivations MotivationProject;
    private Categorie categorieProjet;
    private ItemStack itemview;
    private String ProjetCache;
    private Affichage FiniEncours;
    private int page;


    public QbPlayer() {
        setFiniEncours(Affichage.ENCOURS);
    }

    public SelectionOnCreator getSelection() {
        return selection;
    }

    public void setSelection(SelectionOnCreator selection) {
        this.selection = selection;
    }

    public String getNameProject() {
        return NameProject;
    }

    public void setNameProject(String nameProject) {
        NameProject = nameProject;
    }

    public Motivations getMotivationProject() {
        return MotivationProject;
    }

    public void setMotivationProject(Motivations motivationProject) {
        MotivationProject = motivationProject;
    }

    public Categorie getCategorieProjet() {
        return categorieProjet;
    }

    public void setCategorieProjet(Categorie categorieProjet) {
        this.categorieProjet = categorieProjet;
    }

    public ItemStack getItemview() {
        return itemview;
    }

    public void setItemview(ItemStack itemview) {
        this.itemview = itemview;
    }

    public String getProjetCache() {
        return ProjetCache;
    }

    public Affichage isFiniEncours() {
        return FiniEncours;
    }

    public void setFiniEncours(Affichage finiEncours) {
        FiniEncours = finiEncours;
    }

    public void setProjetCache(String projetCache) {
        ProjetCache = projetCache;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
