package fr.leod1.QbTeleporte.QbProjects.QbUnderProject;

import fr.leod1.QbTeleporte.Utils.QbLoc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class QbUnderProject {

    private String name;
    private Avancement avancement;
    private QbLoc Location;
    private int Itemviews;
    private Short MetaData;

    public QbUnderProject(String name, Avancement avancement, Location location, ItemStack item) {
        this.name = name;
        this.avancement = avancement;
        Location = new QbLoc(location.getWorld().getName(),location.getX(),location.getY(),location.getZ(),location.getYaw(),location.getPitch());
        setItemvie(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Avancement getAvancement() {
        return avancement;
    }

    public void setAvancement(Avancement avancement) {
        this.avancement = avancement;
    }

    public QbLoc getLocation() {
        return Location;
    }
    public org.bukkit.Location getLocationMc() {
        return new Location(Bukkit.getWorld(Location.getWorld()), Location.getX(),Location.getY(),Location.getZ(),Location.getYaw(),Location.getPitch());
    }

    public void setLocation(QbLoc location) {
        Location = location;
    }

    public ItemStack getItemviews() {
        if (MetaData == null){
            MetaData = 0;
        }
        return new ItemStack(Material.getMaterial(Itemviews),1,MetaData);
    }


    public void setItemvie(ItemStack item){
        Itemviews =  item.getType().getId();
        MetaData = item.getDurability();
    }
}
