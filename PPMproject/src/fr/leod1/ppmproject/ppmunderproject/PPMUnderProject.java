package fr.leod1.ppmproject.ppmunderproject;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PPMUnderProject {
    private String name;

    private double LocX;
    private double LocY;
    private double LocZ;
    private float Pitch;
    private float Yaw;
    private String world;

    public PPMUnderProject(Location loc, String name) {
        setSpawnUnderProject(loc);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Location getSpawnOfProject() {
        return new Location(Bukkit.getWorld(world), LocX,LocY,LocZ,Yaw,Pitch);
    }
    private void setSpawnUnderProject(Location spawnOfProject) {
        LocX = spawnOfProject.getX();
        world = spawnOfProject.getWorld().getName();
        LocY = spawnOfProject.getY();
        LocZ = spawnOfProject.getZ();
        Yaw = spawnOfProject.getYaw();
        Pitch = spawnOfProject.getPitch();
    }
}
