package fr.leod1.ppmPermissions.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.ServerRank.Rank;

public class ProjectSerializationManager {
    private Gson gson;

    public ProjectSerializationManager() {
        this.gson = createRightGson();
    }

    private Gson createRightGson(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }
    //.setPrettyPrinting().serializeNulls().disableHtmlEscaping()

    public String serializeRank(Rank projet){
        return gson.toJson(projet);
    }
    public Rank deserializeRank(String json){
        return gson.fromJson(json,Rank.class);
    }

    public String serializePlayerData(PlayerData dataPlayer){
        return gson.toJson(dataPlayer);
    }
    public PlayerData deserializePlayerData(String json){
        return gson.fromJson(json,PlayerData.class);
    }
}
