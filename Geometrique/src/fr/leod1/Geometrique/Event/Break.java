package fr.leod1.Geometrique.Event;

import fr.leod1.Geometrique.PlayerGeometrique.PlayerGeo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.LocalDate;

import static fr.leod1.Geometrique.Main.InstanceOfMain;

public class Break implements Listener {

    @EventHandler
    public void zebi(BlockBreakEvent e){
        Player pl = e.getPlayer();
        PlayerGeo plgeo = InstanceOfMain.PlayerG.get(pl);
        if(plgeo.getActive() == null || !plgeo.getActive()){
            return;
        } else {
            Location locPoint = plgeo.getPoint();
            e.getBlock().
        }


    }

}
