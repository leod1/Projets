package fr.leod1.Gambling.Main.Request;
import org.bukkit.entity.Player;

public class Request {

    private Player requestingPlayer;
    private Player targetedPlayer;
    private Integer amount;
    private String gameMode;
    private long timeOfReset;
    private long timeOfRequest;

    public Request(Player requestingPlayer, Player targetedPlayer, Integer amount, String gameMode, long timeOfReset) {
        this.requestingPlayer = requestingPlayer;
        this.targetedPlayer = targetedPlayer;
        this.amount = amount;
        this.gameMode = gameMode;
        this.timeOfReset = timeOfReset;
        this.timeOfRequest = System.currentTimeMillis();
    }

    public Player getRequestingPlayer() {
        return requestingPlayer;
    }

    public void setRequestingPlayer(Player requestingPlayer) {
        this.requestingPlayer = requestingPlayer;
    }

    public Player getTargetedPlayer() {
        return targetedPlayer;
    }

    public void setTargetedPlayer(Player targetedPlayer) {
        this.targetedPlayer = targetedPlayer;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public long getTimeOfReset() {
        return timeOfReset;
    }

    public void setTimeOfReset(long timeOfReset) {
        this.timeOfReset = timeOfReset;
    }

    public long getTimeOfRequest() {
        return timeOfRequest;
    }

    public void setTimeOfRequest(long timeOfRequest) {
        this.timeOfRequest = timeOfRequest;
    }

}