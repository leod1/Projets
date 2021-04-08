package fr.leod1.undercover.UndercoverPlayer;

public class UndercoverPlayer {

    private String UndercoverName;
    private int IdStatues;

    public UndercoverPlayer(int idStatues,String UndercoverNames) {
        IdStatues = idStatues;
        UndercoverName = UndercoverNames;
    }

    public String getUndercoverName() {
        return UndercoverName;
    }

    public void setUndercoverName(String undercoverName) {
        UndercoverName = undercoverName;
    }

    public int getIdStatues() {
        return IdStatues;
    }

    public void setIdStatues(int idStatues) {
        IdStatues = idStatues;
    }
}




