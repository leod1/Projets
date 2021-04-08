package fr.leod1.undercover.event;

import fr.leod1.undercover.UnderCover;
import fr.leod1.undercover.UndercoverOBJ.UndercoverOBJ;
import fr.leod1.undercover.UndercoverPlayer.UndercoverPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import static fr.leod1.undercover.UnderCover.UnderMain;

public class EventMsg implements Listener {

    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
        Player pl = e.getPlayer();
        if (UnderMain.UndercoverPlayerExist(pl.getName())){
            UndercoverPlayer Udp = UnderMain.GetUndercoverPlayerByname(pl.getName());
            if (Udp.getIdStatues() == UnderMain.getUndercoverByName(Udp.getUndercoverName()).getStatues()){
                e.setCancelled(true);
                UndercoverOBJ ud = UnderMain.getUndercoverByName(Udp.getUndercoverName());
                if(ud.getStatues() == ud.getListPlayerOfPart().size()-1){
                    ud.setStatues(0);
                } else {
                    ud.setStatues(ud.getStatues() + 1);

                }
                Player plTour = ud.getListPlayerOfPart().get(ud.getStatues());
                for (Player plALL : ud.getListPlayerOfPart()){
                    plALL.sendMessage("§8[§b"+pl.getName()+"§8] §a"+ e.getMessage());
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            plALL.sendMessage("§3Tour de: §c"+plTour.getName());
                        }
                    }.runTaskLater(UnderMain, 10);
                }
            } else {
                pl.sendMessage("Tg c pas ton tour");
                e.setCancelled(true);
                return;
            }
        } else {
            return;
        }






        /*if(UnderMain.tourManager.getTour().getPlayerNumber() ==){
            player.sendMessage("Tg c pas ton tour");
        } else {
            //UnderMain.GetUndercoverPlayerByname(e.getPlayer().getName()).setTurnToTalk(false);
        }

    }*/
    }
}
