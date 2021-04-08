package fr.leod1.Geometrique.PlayerGeometrique;

import org.bukkit.Location;

public class PlayerGeo {

    private Location point;
    private Boolean active;



    public Location getPoint() {
        return point;
    }

    public void setPoint(Location point) {
        this.point = point;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
