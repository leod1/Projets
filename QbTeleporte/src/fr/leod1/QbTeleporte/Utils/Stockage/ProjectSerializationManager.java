package fr.leod1.QbTeleporte.Utils.Stockage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;

public class ProjectSerializationManager {
    private Gson gson;

    public ProjectSerializationManager() {
        this.gson = createRightGson();
    }

    private Gson createRightGson(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }
    //.setPrettyPrinting().serializeNulls().disableHtmlEscaping()

    public String serializePPMproject(QbProjects projet){
        return gson.toJson(projet);
    }

    public QbProjects deserializePPMprojects(String json){
        return gson.fromJson(json,QbProjects.class);
    }

    public String serializePlayerData(QbProjects dataPlayer){
        return gson.toJson(dataPlayer);
    }
    public QbProjects deserializePlayerData(String json){
        return gson.fromJson(json,QbProjects.class);
    }
}
