package fr.leod1.QbTeleporte.QbEvents;

import fr.leod1.QbTeleporte.QbInventory.CreationGui;
import fr.leod1.QbTeleporte.QbPlayer.SelectionOnCreator;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.Avancement;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.QbUnderProject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class chatEvent implements Listener {
    private CreationGui gui = new CreationGui();
    @EventHandler
    public void zebi(AsyncPlayerChatEvent e){
        Player pl = e.getPlayer();
        if(InstanceOfMain.playerCaches.get(pl).getSelection() != null){
            if (InstanceOfMain.playerCaches.get(pl).getSelection() == SelectionOnCreator.name){
                InstanceOfMain.playerCaches.get(pl).setSelection(null);
                InstanceOfMain.playerCaches.get(pl).setNameProject(e.getMessage());
                gui.CreateProject(pl);
                e.setCancelled(true);
            } else if (InstanceOfMain.playerCaches.get(pl).getSelection() == SelectionOnCreator.addUnderProject){

                QbProjects projetSelect = InstanceOfMain.getProjectsByName(InstanceOfMain.playerCaches.get(pl).getProjetCache());

                if (pl.getItemInHand().getType() == null ||pl.getItemInHand().getType() == Material.AIR){
                    pl.sendMessage("§cMerci d'avoir un item en main !");
                    e.setCancelled(true);
                    InstanceOfMain.playerCaches.get(pl).setSelection(null);
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(null);
                    return;
                }else if (projetSelect.getUnderQbProjet().contains(projetSelect.getUnderProjectByName(e.getMessage()))){
                    pl.sendMessage("§cDésolé, ce nom exist déjà");
                    e.setCancelled(true);
                    InstanceOfMain.playerCaches.get(pl).setSelection(null);
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(null);
                } else {
                    InstanceOfMain.playerCaches.get(pl).setSelection(null);

                    InstanceOfMain.getProjectsByName(InstanceOfMain.playerCaches.get(pl).getProjetCache()).getUnderQbProjet().add(new QbUnderProject(e.getMessage(), Avancement.Encours, pl.getLocation(), e.getPlayer().getItemInHand()));
                    gui.getOprnproject().ProjetOpenner(pl,InstanceOfMain.getProjectsByName(InstanceOfMain.playerCaches.get(pl).getProjetCache()));
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(null);
                    e.setCancelled(true);
                    InstanceOfMain.save();
                }
            }else if (InstanceOfMain.playerCaches.get(pl).getSelection() == SelectionOnCreator.rmUnderProject){
                QbProjects projetSelect = InstanceOfMain.getProjectsByName(InstanceOfMain.playerCaches.get(pl).getProjetCache());


                if (projetSelect.getUnderQbProjet().contains(projetSelect.getUnderProjectByName(e.getMessage()))){

                    InstanceOfMain.playerCaches.get(pl).setSelection(null);
                    projetSelect.getUnderQbProjet().remove(projetSelect.getUnderProjectByName(e.getMessage()));
                    gui.getOprnproject().ProjetOpenner(pl,InstanceOfMain.getProjectsByName(InstanceOfMain.playerCaches.get(pl).getProjetCache()));
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(null);
                    e.setCancelled(true);


                } else {
                    pl.sendMessage("§cDésolé, ce projet n'exist pas");
                    e.setCancelled(true);
                    InstanceOfMain.playerCaches.get(pl).setSelection(null);
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(null);
                }
            }
        }
    }
}
