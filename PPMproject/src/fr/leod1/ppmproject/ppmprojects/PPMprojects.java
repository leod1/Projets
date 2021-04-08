package fr.leod1.ppmproject.ppmprojects;

import fr.leod1.ppmproject.ppmunderproject.PPMUnderProject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PPMprojects {
    private Date DateOfCreationOfProject;
    private String MotivationOfPproject;
    private String TypeOfproject;
    private String NameOfProject;

    private int Itemviews;
    private Short MetaData;

    private String SpawnWorld;
    private Double SpawnX;
    private Double SpawnY;
    private Double SpawnZ;
    private float pitch;
    private float yaw;


    private String NameOfOwner;
    private ArrayList<String> NamesOfMenber;
    private ArrayList<PPMUnderProject> UnderProject;



    public PPMprojects(Date dateOfCreationOfProject, String motivationOfPproject, String typeOfproject, String nameOfProject, Location spawnOfProject, String nameOfOwner, ArrayList<String> namesOfMenber,ItemStack itemviews, ArrayList<PPMUnderProject> underproject) {
        DateOfCreationOfProject = dateOfCreationOfProject;
        MotivationOfPproject = motivationOfPproject;
        TypeOfproject = typeOfproject;
        NameOfProject = nameOfProject;
        setSpawnOfProject(spawnOfProject);
        NameOfOwner = nameOfOwner;
        NamesOfMenber = namesOfMenber;
        setItemviews(itemviews);
        underproject.add(new PPMUnderProject(spawnOfProject,"Main"));
        UnderProject = underproject;
    }



    public Date getDateOfCreationOfProject() {
        return DateOfCreationOfProject;
    }

    public void setDateOfCreationOfProject(Date dateOfCreationOfProject) {
        DateOfCreationOfProject = dateOfCreationOfProject;
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
        return new Location(Bukkit.getWorld(SpawnWorld), SpawnX,SpawnY,SpawnZ,yaw,pitch);
    }

    public void setSpawnOfProject(Location spawnOfProject) {
        SpawnWorld = spawnOfProject.getWorld().getName();
        SpawnX = spawnOfProject.getX();
        SpawnY = spawnOfProject.getY();
        SpawnZ = spawnOfProject.getZ();
        yaw = spawnOfProject.getYaw();
        pitch = spawnOfProject.getPitch();
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

    public ItemStack getItemviews() {
        if (MetaData == null){
            MetaData = 0;
        }
        return new ItemStack(Material.getMaterial(Itemviews),1,MetaData);
    }

    public void setItemviews(ItemStack itemviews) {
        Itemviews = itemviews.getType().getId();
        MetaData = itemviews.getDurability();
    }


    public ArrayList<PPMUnderProject> getUnderProject() {
        return UnderProject;
    }

    public void setUnderProject(ArrayList<PPMUnderProject> underProject) {
        UnderProject = underProject;
    }

    public void addUnderProject(PPMUnderProject ppmUnderProject){
        UnderProject.add(ppmUnderProject);
    }
    public void rmUnderProject(PPMUnderProject ppmUnderProject){
        UnderProject.remove(ppmUnderProject);
    }


    public PPMUnderProject getUnderProjetctbyName(String name){
        for (PPMUnderProject under : this.getUnderProject()){
            if (under.getName().equalsIgnoreCase(name)){
                return under;
            }
        }
        return null;
    }

    public boolean UnderProjectexist(String name){
        for (PPMUnderProject under : this.getUnderProject()){
            if (under.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public boolean PlayerIsinProject(String name){
        if (this.getNamesOfMenber().contains(name) || name.equalsIgnoreCase("leod1") || name.equalsIgnoreCase(this.getNameOfOwner())){
            return true;
        }else {
            return false;
        }
    }

}
