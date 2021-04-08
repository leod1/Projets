package fr.leod1.ppmproject.event;

import fr.leod1.ppmproject.Inventorygui.CreationGUI;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMcreatorProject;
import fr.leod1.ppmproject.ppmcreatorprojects.PhiltreSelection;
import fr.leod1.ppmproject.ppmunderproject.PPMUnderProject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class EventChat implements Listener {
    public CreationGUI gui = new CreationGUI();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        Player pl = e.getPlayer();
        PPMcreatorProject proj = InstanceOfMain.getPPMcreator(pl);
        if(!(proj.getWatIsSelect() == null)){
            switch (proj.getWatIsSelect()){
                case SetNameOfProject:
                    proj.setNameOfProject(e.getMessage());
                    proj.setWatIsSelect(null);
                    gui.CreateInventory(pl);
                    e.setCancelled(true);
                    break;
                case SetOneOfNamesOfMenber:
                    proj.addMember(e.getMessage());
                    proj.setWatIsSelect(null);
                    gui.CreateInventory(pl);
                    e.setCancelled(true);
                    break;
                case addUnderProject:
                    if (proj.getCacheProjects().UnderProjectexist(e.getMessage())){
                        proj.setWatIsSelect(null);
                        e.setCancelled(true);
                        pl.sendMessage("Désolé, le sous projet exist déjà");
                        break;
                    }
                    proj.getCacheProjects().addUnderProject(new PPMUnderProject(pl.getLocation(),e.getMessage()));
                    proj.setWatIsSelect(null);
                    e.setCancelled(true);
                    pl.sendMessage("Le sous projet a été ajouter");
                    break;
                case rmUnderProject:
                    if (!proj.getCacheProjects().UnderProjectexist(e.getMessage())){
                        pl.sendMessage("Désolé, le sous projet exist pas");
                        proj.setWatIsSelect(null);
                        e.setCancelled(true);
                        break;
                    }
                    if (e.getMessage().equalsIgnoreCase("Main")){
                        pl.sendMessage("Désolé, le sous projet Main ne peut pas etre supprimer !");
                        proj.setWatIsSelect(null);
                        e.setCancelled(true);
                        break;
                    }
                    proj.getCacheProjects().rmUnderProject(proj.getCacheProjects().getUnderProjetctbyName(e.getMessage()));
                    proj.setWatIsSelect(null);
                    e.setCancelled(true);
                    pl.sendMessage("Le sous projet a été suprimer");
                    break;
            }
            return;

        }
    }
}
