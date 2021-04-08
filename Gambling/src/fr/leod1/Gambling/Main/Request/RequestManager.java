package fr.leod1.Gambling.Main.Request;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RequestManager {

    private List<Request> requests = new ArrayList<>();

    public List<Request> getRequests() {
        deleteOldRequests();
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public boolean addRequest(Request request) {
        deleteOldRequests();
        removeRequests(getRequestsInCommon(request.getRequestingPlayer(), request.getTargetedPlayer()));
        if (getRequestsOfTargetedPlayer(request.getTargetedPlayer()).isEmpty()) {
            this.requests.add(request);
            return true;
        }
        return false;
    }

    public void removeRequest(Request request) {
        this.requests.remove(request);
    }

    public void removeRequests(List<Request> requests) {
        for (Request request : requests) {
            removeRequest(request);
        }
    }

    public void clearAllRequest() {
        getRequests().clear();
    }

    public void deleteOldRequests() {
        if (requests == null){
            return;
        }
        for (Request request : requests) {
            if (request.getTimeOfRequest() + request.getTimeOfReset() < System.currentTimeMillis()) {
                removeRequest(request);
            }
        }
    }

    public List<Request> getRequestsOfRequestingPlayer(Player requestingPlayer) {
        deleteOldRequests();
        List<Request> requestsOfRequestingPlayer = new ArrayList<>();
        for (Request request : getRequests()) {
            if (request.getRequestingPlayer() == requestingPlayer) {
                requestsOfRequestingPlayer.add(request);
            }
        }
        return requestsOfRequestingPlayer;
    }

    public List<Request> getRequestsOfTargetedPlayer(Player targetedPlayer) {
        deleteOldRequests();
        List<Request> requestsOfTargetedPlayer = new ArrayList<>();
        for (Request request : getRequests()) {
            if (request.getTargetedPlayer() == targetedPlayer) {
                requestsOfTargetedPlayer.add(request);
            }
        }
        return requestsOfTargetedPlayer;
    }

    public List<Request> getRequestsInCommon(Player requestingPlayer, Player targetedPlayer) {
        deleteOldRequests();
        List<Request> requestsInCommon = new ArrayList<>();
        for (Request request : getRequests()) {
            if (request.getRequestingPlayer() == requestingPlayer
                    && request.getTargetedPlayer() == targetedPlayer) {
                requestsInCommon.add(request);
            }
        }
        return requestsInCommon;
    }

}