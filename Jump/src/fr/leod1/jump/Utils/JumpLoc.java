package fr.leod1.jump.Utils;

import org.bukkit.Location;

public class JumpLoc {

    private int x;
    private int y;
    private int z;
    private String worldName;

    public JumpLoc(int x, int y, int z, String worldName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

    public JumpLoc(double x, double y, double z, String worldName) {
        this.x = (int) Math.floor(x);
        this.y = (int) Math.floor(y);
        this.z = (int) Math.floor(z);
        this.worldName = worldName;
    }

    public JumpLoc(Location location) {
        this.x = (int) Math.floor(location.getX());
        this.y = (int) Math.floor(location.getY());
        this.z = (int) Math.floor(location.getZ());
        this.worldName = location.getWorld().getName();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
}
