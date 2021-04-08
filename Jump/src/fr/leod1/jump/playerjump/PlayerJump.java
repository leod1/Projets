package fr.leod1.jump.playerjump;

import fr.leod1.jump.Jumps.JumpObj;
import fr.leod1.jump.Utils.JumpLoc;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class PlayerJump {

    private String jump;
    private int checkPoint;
    private long timeStart;
    private ItemStack[] itemStacks;

    public PlayerJump(String jump, ItemStack[] itemStacks) {
        this.jump = jump;
        this.checkPoint = -1;
        this.timeStart = System.currentTimeMillis();
        this.itemStacks = itemStacks;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public int getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(int checkPoint) {
        this.checkPoint = checkPoint;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public ItemStack[] getInventory() {
        return itemStacks;
    }

    public void setInventory(ItemStack[] inventory) {
        this.itemStacks = itemStacks;
    }

    public Location getLocationOflastCP (JumpObj jump){
        if (this.getCheckPoint() == -1){
            Location location= new Location(Bukkit.getWorld(jump.getStart().getWorldName()), jump.getStart().getX() + 0.5, jump.getStart().getY(), jump.getStart().getZ()+ 0.5);
            return location;
        }
        JumpLoc loc = jump.getCheckPoint(this.checkPoint);
        Location location= new Location(Bukkit.getWorld(loc.getWorldName()), loc.getX() + 0.5, loc.getY(), loc.getZ() + 0.5);
        return location;
    }
}
