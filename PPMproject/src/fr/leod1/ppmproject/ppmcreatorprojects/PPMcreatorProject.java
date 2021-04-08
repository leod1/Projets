package fr.leod1.ppmproject.ppmcreatorprojects;

import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import java.time.LocalDate;
import java.util.ArrayList;

public class PPMcreatorProject {

    private PPMprojects CacheProjects;
    private SelectionOnList selection;
    private ItemStack Itemviews;
    private PPMCromas chromas;
    private StatuesOfSelect WatIsSelect;
    private String MotivationOfPproject;
    private String TypeOfproject;
    private String NameOfProject;
    private Location SpawnOfProject;
    private int page;
    private int pageUnder;

    private PhiltreSelection PhiltreSelections;
    private String PhiltreNameTarget;
    private String PhiltreMotiv;




    private String NameOfOwner;
    private ArrayList<String> NamesOfMenber = new ArrayList<>();

    public PPMcreatorProject(PPMCromas chroma) {
        chromas = chroma;
        selection = SelectionOnList.Normal;
    }

    public String getMotivationOfPproject() {
        return MotivationOfPproject;
    }

    public void setMotivationOfPproject(String motivationOfPproject) {
        MotivationOfPproject = motivationOfPproject;
    }

    public String getTypeOfproject() {
        return TypeOfproject;
    }

    public void setTypeOfproject(String typeOfproject) {
        TypeOfproject = typeOfproject;
    }

    public String getNameOfProject() {
        return NameOfProject;
    }

    public void setNameOfProject(String nameOfProject) {
        NameOfProject = nameOfProject;
    }

    public Location getSpawnOfProject() {
        return SpawnOfProject;
    }

    public void setSpawnOfProject(Location spawnOfProject) {
        SpawnOfProject = spawnOfProject;
    }

    public String getNameOfOwner() {
        return NameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        NameOfOwner = nameOfOwner;
    }

    public ArrayList<String> getNamesOfMenber() {
        return NamesOfMenber;
    }

    public void setNamesOfMenber(ArrayList<String> namesOfMenber) {
        NamesOfMenber = namesOfMenber;
    }

    public StatuesOfSelect getWatIsSelect() {
        return WatIsSelect;
    }
    public void addMember(String Namepl){
        NamesOfMenber.add(Namepl);
    }
    public void rmMember(String Namepl){
        NamesOfMenber.remove(Namepl);
    }

    public void setWatIsSelect(StatuesOfSelect watIsSelect) {
        WatIsSelect = watIsSelect;
    }

    public PPMCromas getChroma() {
        return chromas;
    }

    public void setChroma(PPMCromas chroma) {
        this.chromas = chroma;
    }

    public ItemStack getItemviews() {
        return Itemviews;
    }

    public void setItemviews(ItemStack itemviews) {
        Itemviews = itemviews;
    }

    public SelectionOnList getSelection() {
        return selection;
    }

    public void setSelection(SelectionOnList selection) {
        this.selection = selection;
    }


    public PhiltreSelection getPhiltreSelections() {
        return PhiltreSelections;
    }

    public void setPhiltreSelections(PhiltreSelection philtreSelections) {
        PhiltreSelections = philtreSelections;
    }

    public String getPhiltreNameTarget() {
        return PhiltreNameTarget;
    }

    public void setPhiltreNameTarget(String philtreNameTarget) {
        PhiltreNameTarget = philtreNameTarget;
    }

    public PPMprojects getCacheProjects() {
        return CacheProjects;
    }

    public void setCacheProjects(PPMprojects cacheProjects) {
        CacheProjects = cacheProjects;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageUnder() {
        return pageUnder;
    }

    public void setPageUnder(int pageUnder) {
        this.pageUnder = pageUnder;
    }

    public String getPhiltreMotiv() {
        return PhiltreMotiv;
    }

    public void setPhiltreMotiv(String philtreMotiv) {
        PhiltreMotiv = philtreMotiv;
    }
}
