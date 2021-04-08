package fr.leod1.arkapvp.arkapvpgames;

import fr.leod1.arkapvp.arkapvpkitformat.ArkaPvpKitFormat;
import fr.leod1.arkapvp.arkapvpstatuesgames.ArkaPvpStatuesGames;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class ArkaPvpGames {

    private ArkaPvpStatuesGames GamesStatues;


    private ArkaPvpKitFormat kit;
    private Location Lobby;
    private Location Pnj;
    private Location EmerauldGen1;
    private Location EmerauldGen2;
    private Location BannerBlue;
    private Location BanerRed;
    private ArrayList<Player> AllPlayerInPart;
    private ArrayList<Player> TeamBlue;
    private ArrayList<Player> TeamRed;
    private int MaxPlayerInPart;
    private int MaxPlayerInTeam;

    public ArkaPvpGames() {
        this.GamesStatues = ArkaPvpStatuesGames.Lobby;
        this.kit = null;
        this.Lobby = null;
        this.BannerBlue = null;
        this.BanerRed = null;
        this.AllPlayerInPart = new ArrayList<>();
        this.TeamBlue = new ArrayList<>();
        this.TeamRed = new ArrayList<>();
        this.MaxPlayerInPart = 12;
        this.MaxPlayerInTeam = 6;
    }

    public ArkaPvpKitFormat getKit() {
        return kit;
    }

    public void setKit(ArkaPvpKitFormat kit) {
        this.kit = kit;
    }

    public Location getLobby() {
        return Lobby;
    }

    public void setLobby(Location lobby) {
        Lobby = lobby;
    }

    public Location getBannerBlue() {
        return BannerBlue;
    }

    public void setBannerBlue(Location bannerBlue) {
        BannerBlue = bannerBlue;
    }

    public Location getBanerRed() {
        return BanerRed;
    }

    public void setBanerRed(Location banerRed) {
        BanerRed = banerRed;
    }

    public void addPlayerInPart(Player pl){
        this.AllPlayerInPart.add(pl);
    }
    public void rmPlayerInPart(Player pl){
        this.AllPlayerInPart.remove(pl);
    }

    public ArrayList<Player> getAllPlayerInPart() {
        return AllPlayerInPart;
    }

    public ArrayList<Player> getTeamBlue() {
        return TeamBlue;
    }

    public ArrayList<Player> getTeamRed() {
        return TeamRed;
    }

    public int getMaxPlayerInTeam() {
        return MaxPlayerInTeam;
    }

    public void setMaxPlayerInTeam(int maxPlayer) {
        MaxPlayerInTeam = maxPlayer;
    }

    public int getMaxPlayerInPart() {
        return MaxPlayerInPart;
    }

    public void setMaxPlayerInPart(int maxPlayerInPart) {
        MaxPlayerInPart = maxPlayerInPart;
    }

    public ArkaPvpStatuesGames getGamesStatues() {
        return GamesStatues;
    }

    public void setGamesStatues(ArkaPvpStatuesGames gamesStatues) {
        GamesStatues = gamesStatues;
    }
}
