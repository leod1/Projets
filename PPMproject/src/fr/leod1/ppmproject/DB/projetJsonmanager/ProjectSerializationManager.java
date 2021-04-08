package fr.leod1.ppmproject.DB.projetJsonmanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import sun.java2d.windows.GDIBlitLoops;

public class ProjectSerializationManager {
    private Gson gson;

    public ProjectSerializationManager() {
        this.gson = createRightGson();
    }

    private Gson createRightGson(){
        return new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }
    //.setPrettyPrinting().serializeNulls().disableHtmlEscaping()

    public String serializePPMproject(PPMprojects projet){
        return gson.toJson(projet);
    }

    public PPMprojects deserializePPMprojects(String json){
        return gson.fromJson(json,PPMprojects.class);
    }

    public String serializePlayerData(PPMDataPlayer dataPlayer){
        return gson.toJson(dataPlayer);
    }
    public PPMDataPlayer deserializePlayerData(String json){
        return gson.fromJson(json,PPMDataPlayer.class);
    }
}
