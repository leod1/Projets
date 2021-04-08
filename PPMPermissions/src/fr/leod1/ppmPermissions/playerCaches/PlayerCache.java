package fr.leod1.ppmPermissions.playerCaches;

import fr.leod1.ppmPermissions.ServerRank.Rank;

public class PlayerCache {
    private Rank rank;
    private Selected selection;
    private String concurenteRankModif;
    private String concurentePlayerModif;
    private int pagePermRank;
    private int pagePermPlayer;

    public PlayerCache() {
        rank = new Rank(false);
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Selected getSection() {
        return selection;
    }

    public void setSection(Selected section) {
        this.selection = section;
    }

    public String getConcurenteRankModif() {
        return concurenteRankModif;
    }

    public void setConcurenteRankModif(String concurenteRankModif) {
        this.concurenteRankModif = concurenteRankModif;
    }

    public String getConcurentePlayerModif() {
        return concurentePlayerModif;
    }

    public void setConcurentePlayerModif(String concurentePlayerModif) {
        this.concurentePlayerModif = concurentePlayerModif;
    }

    public int getPagePermRank() {
        return pagePermRank;
    }

    public void setPagePermRank(int pagePermRank) {
        this.pagePermRank = pagePermRank;
    }

    public int getPagePermPlayer() {
        return pagePermPlayer;
    }

    public void setPagePermPlayer(int pagePermPlayer) {
        this.pagePermPlayer = pagePermPlayer;
    }
}
