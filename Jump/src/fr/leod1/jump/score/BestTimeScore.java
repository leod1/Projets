package fr.leod1.jump.score;

public class BestTimeScore {

    private String jump;
    private String playerName;
    private long time;

    public BestTimeScore(String playerName, long time, String jump) {
        this.playerName = playerName;
        this.time = time;
        this.jump = jump;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public String getPlayer() {
        return playerName;
    }

    public void setPlayer(String playerName) {
        this.playerName = playerName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
