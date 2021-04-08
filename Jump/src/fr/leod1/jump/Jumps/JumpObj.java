package fr.leod1.jump.Jumps;

import de.inventivegames.hologram.Hologram;
import de.inventivegames.hologram.HologramAPI;
import fr.leod1.jump.Utils.JumpLoc;
import fr.leod1.jump.score.BestTimeScore;
import fr.leod1.jump.score.TimeComparator;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Collections;

public class JumpObj {

    private String name;
    private JumpLoc Start;
    private JumpLoc Finish;
    private JumpLoc Holo;
    private ArrayList<JumpLoc> checkPoints = new ArrayList<>();
    private ArrayList<BestTimeScore> BestScores = new ArrayList<>();
    private ArrayList<Hologram> holo = new ArrayList<>();


    public JumpObj(String name) {
        this.name = name;
        this.Start = null;
        this.Finish = null;
    }

    public ArrayList<BestTimeScore> getBestScores() {
        return BestScores;
    }

    public void setBestScores(ArrayList<BestTimeScore> bestScores) {
        BestScores = bestScores;
    }
    public void addBestScores(BestTimeScore Bc){
        BestScores.add(Bc);
        Collections.sort(BestScores, new TimeComparator());
        if(this.Holo == null||this.Holo.getWorldName() == null || Holo.getWorldName().equalsIgnoreCase("null")){
            return;
        }
        this.SetHolo();
    }
    public void SetHolo(){
        if(BestScores.size() < 10){
            DeleteAllHolo();
            Double z = 4D;
            for(int i = 0; i <= BestScores.size() - 1; i++){
                BestTimeScore bcobj = BestScores.get(i);
                Double TimeinSec = bcobj.getTime()/1000D;
                Double TimeinMin = TimeinSec/60D;
                Double TimeinHour = TimeinMin/60D;
                Hologram hl = null;
                if(bcobj.getTime()/1000D > 1 && TimeinSec/60D > 1 && TimeinMin/60D>1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a"+(int)Math.floor(TimeinHour)+":"+(int)Math.floor(TimeinMin)+":"+TimeinSec);
                } else if (bcobj.getTime()/1000D > 1 && TimeinSec/60D > 1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a00:"+(int)Math.floor(TimeinMin)+":"+TimeinSec);
                }else if (bcobj.getTime()/1000D > 1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a00:00:"+TimeinSec);

                }else {

                }
                hl.spawn();
                holo.add(hl);
                z = z-0.30D;

            }
        }else {
            DeleteAllHolo();
            Double z = 4D;
            for(int i = 0; i <= 9; i++){
                BestTimeScore bcobj = BestScores.get(i);
                Double TimeinSec = bcobj.getTime()/1000D;
                Double TimeinMin = TimeinSec/60D;
                Double TimeinHour = TimeinMin/60D;
                Hologram hl = null;

                if(bcobj.getTime()/1000D > 1 && TimeinSec/60D > 1 && TimeinMin/60D>1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a"+Math.floor(TimeinHour)+":"+Math.floor(TimeinMin)+":"+TimeinSec);
                } else if (bcobj.getTime()/1000D > 1 && TimeinSec/60D > 1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a00:"+Math.floor(TimeinMin)+":"+TimeinSec);
                }else if (bcobj.getTime()/1000D > 1){
                    hl = HologramAPI.createHologram(new Location(Bukkit.getWorld(this.getHolo().getWorldName()), getHolo().getX(), getHolo().getY() + z, getHolo().getZ()), "§b"+(i+1)+".§7 "+bcobj.getPlayer()+" / §a00:00:"+TimeinSec);

                }else {

                }
                hl.spawn();
                holo.add(hl);
                z = z-0.30D;

            }
        }
    }
    private void DeleteAllHolo(){
        if(!(this.holo.size() == 0)){
            for (Hologram holog : this.holo){
                holog.despawn();
            }
            this.holo.clear();
        }
    }

    public JumpLoc getHolo() {
        return Holo;
    }

    public void setHolo(JumpLoc holo) {
        Holo = holo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public JumpLoc getStart() {
        return Start;
    }

    public void setStart(JumpLoc start) {
        Start = start;
    }

    public JumpLoc getFinish() {
        return Finish;
    }

    public void setFinish(JumpLoc finish) {
        Finish = finish;
    }

    public ArrayList<JumpLoc> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(ArrayList<JumpLoc> checkPoints) {
        this.checkPoints = checkPoints;
    }

    public JumpLoc getCheckPoint(int number) {
        if (number >= 0 && number < checkPoints.size()) {
            return checkPoints.get(number);
        } else {
            return null;
        }
    }

    public void addCheckPoint(JumpLoc jumpLoc) {
        this.checkPoints.add(jumpLoc);
    }

    public void addCheckPoint(int index, JumpLoc jumpLoc) {
        this.checkPoints.add(index, jumpLoc);
    }

    public void removeCheckPoints(JumpLoc jumpLoc) {
        this.checkPoints.remove(jumpLoc);
    }

    public void removeCheckPoints(int index) {
        this.checkPoints.remove(index);
    }

    public boolean hasCheckPoint(JumpLoc jumpLoc) {
        if (this.checkPoints.contains(jumpLoc)) {
            return true;
        } else {
            return false;
        }
    }

    public int getIndexOfCheckPoint(JumpLoc jumpLoc) {
        return this.getCheckPoints().indexOf(jumpLoc);
    }


}
