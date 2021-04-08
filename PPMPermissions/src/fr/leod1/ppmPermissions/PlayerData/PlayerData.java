package fr.leod1.ppmPermissions.PlayerData;

import java.util.ArrayList;

public class PlayerData {

    private String ServerRank;
    private ArrayList<String> PrivatePermissions;

    public PlayerData(String serverRank, ArrayList<String> privatePermissions) {
        ServerRank = serverRank;
        PrivatePermissions = privatePermissions;
    }

    public String getServerRank() {
        return ServerRank;
    }

    public void setServerRank(String serverRank) {
        ServerRank = serverRank;
    }

    public ArrayList<String> getPrivatePermissions() {
        return PrivatePermissions;
    }

    public void setPrivatePermissions(ArrayList<String> privatePermissions) {
        PrivatePermissions = privatePermissions;
    }
}
