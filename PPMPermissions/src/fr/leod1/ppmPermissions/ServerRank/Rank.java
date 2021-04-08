package fr.leod1.ppmPermissions.ServerRank;

import fr.leod1.ppmPermissions.inventory.ColorHeads;
import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.ArrayList;

public class Rank {

    private ColorHeads Color;
    private String name;
    private String Prefix;
    private String Sufix;
    private ArrayList<String> PermissionsRank;
    private boolean rankDefault;


    public Rank(ColorHeads color, String name, String prefix, String sufix, ArrayList<String> permissionsRank, boolean rankDefault) {
        Color=color;
        this.name = name;
        Prefix = prefix;
        Sufix = sufix;
        PermissionsRank = permissionsRank;
        this.rankDefault = rankDefault;
    }

    public Rank(boolean rankDefault) {
        this.rankDefault = rankDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String prefix) {
        Prefix = prefix;
    }

    public String getSufix() {
        return Sufix;
    }

    public void setSufix(String sufix) {
        Sufix = sufix;
    }

    public ArrayList<String> getPermissionsRank() {
        return PermissionsRank;
    }

    public void setPermissionsRank(ArrayList<String> permissionsRank) {
        PermissionsRank = permissionsRank;
    }

    public boolean isRankDefault() {
        return rankDefault;
    }

    public void setRankDefault(boolean rankDefault) {
        this.rankDefault = rankDefault;
    }

    public ColorHeads getColor() {
        return Color;
    }

    public void setColor(ColorHeads color) {
        Color = color;
    }
}
