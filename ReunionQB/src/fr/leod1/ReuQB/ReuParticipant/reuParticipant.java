package fr.leod1.ReuQB.ReuParticipant;

import fr.leod1.ReuQB.Utils.reuLocation;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class reuParticipant {

    String Name;
    reuLocation Location;


    public reuParticipant(String name, reuLocation location) {
        Name = name;
        Location = location;
    }

    private ItemStack head(String name, String nameItem){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) item.getItemMeta();
        skullm.setOwner(name);
        skullm.setDisplayName(nameItem);
        item.setItemMeta(skullm);

        return item;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public reuLocation getLocation() {
        return Location;
    }

    public void setLocation(reuLocation location) {
        Location = location;
    }

    public ItemStack getHead() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) item.getItemMeta();
        skullm.setOwner(this.Name);
        skullm.setDisplayName("§6"+Name);
        item.setItemMeta(skullm);
        return item;
    }

}
