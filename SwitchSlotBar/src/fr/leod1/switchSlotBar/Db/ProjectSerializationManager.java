package fr.leod1.switchSlotBar.Db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.switchSlotBar.stockageSlots.SlotsBar;

public class ProjectSerializationManager {
    private Gson gson;

    public ProjectSerializationManager() {
        this.gson = createRightGson();
    }

    private Gson createRightGson(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }
    //.setPrettyPrinting().serializeNulls().disableHtmlEscaping()

    public String serializePlayerData(SlotsBar dataPlayer){
        return gson.toJson(dataPlayer);
    }
    public SlotsBar deserializePlayerData(String json){
        return gson.fromJson(json,SlotsBar.class);
    }
}
