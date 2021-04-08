package fr.leod1.QbTeleporte.QbEvents;

import fr.leod1.QbTeleporte.QbInventory.CreationGui;
import fr.leod1.QbTeleporte.QbPlayer.Affichage;
import fr.leod1.QbTeleporte.QbPlayer.QbPlayer;
import fr.leod1.QbTeleporte.QbPlayer.SelectionOnCreator;
import fr.leod1.QbTeleporte.QbProjects.Categorie;
import fr.leod1.QbTeleporte.QbProjects.Motivations;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.Avancement;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.QbUnderProject;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;

import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class InteractInvEvent implements Listener {
    private CreationGui gui = new CreationGui();
    @EventHandler
    public void zebi(InventoryClickEvent e) {
        Player pl = (Player) e.getWhoClicked();

        if (InstanceOfMain.playerCaches.get(pl).getSelection() == SelectionOnCreator.ItemviewProjects) {
            InstanceOfMain.playerCaches.get(pl).setSelection(null);
            InstanceOfMain.playerCaches.get(pl).setItemview(e.getCurrentItem());
            gui.CreateProject(pl);
            return;
        }

        if(e.getInventory().getName().contains("§8Qb §7Categorie Evenements") || e.getInventory().getName().contains("§8Qb §7Categorie teamprojects") || e.getInventory().getName().contains("§8Qb §7Categorie bundle") || e.getInventory().getName().contains("§8Qb §7Categorie stelerio") || e.getInventory().getName().contains("§8Qb §7Categorie memberproject")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour") && e.getCurrentItem().getType() == Material.BARRIER){
                gui.getListgui().SelectionList(pl);
                e.setCancelled(true);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§6")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Page suivante")){
                    InstanceOfMain.playerCaches.get(pl).setPage(InstanceOfMain.playerCaches.get(pl).getPage() + 1);
                    e.setCancelled(true);
                    if (e.getInventory().getName().contains("§8Qb §7Categorie Evenements")){
                        gui.getListgui().ListEvenement(pl);
                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie teamprojects")){
                        gui.getListgui().ListTeamProject(pl);
                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie bundle")){
                        gui.getListgui().ListBundle(pl);
                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie stelerio")){
                        gui.getListgui().ListStelerio(pl);
                    } else {
                        gui.getListgui().ListMemberProject(pl);
                    }
                } else {
                    InstanceOfMain.playerCaches.get(pl).setPage(InstanceOfMain.playerCaches.get(pl).getPage() - 1);
                    e.setCancelled(true);

                    if (e.getInventory().getName().contains("§8Qb §7Categorie Evenements")){
                        gui.getListgui().ListEvenement(pl);

                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie teamprojects")){
                        gui.getListgui().ListTeamProject(pl);
                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie bundle")){
                        gui.getListgui().ListBundle(pl);
                    }else if (e.getInventory().getName().contains("§8Qb §7Categorie stelerio")){
                        gui.getListgui().ListStelerio(pl);
                    } else {
                        gui.getListgui().ListMemberProject(pl);
                    }
                }
                e.setCancelled(true);
                return;
            }

            if (InstanceOfMain.containProjectbyName(e.getCurrentItem().getItemMeta().getDisplayName())){
                QbProjects projet = InstanceOfMain.getProjectsByName(e.getCurrentItem().getItemMeta().getDisplayName());
                if (e.getClick() == ClickType.RIGHT){
                    if (!pl.hasPermission("qbl.hardPerm")){
                        pl.sendMessage("§cDésolé, Tu n'as pas la permission necésaire");
                        e.setCancelled(true);
                        return;
                    } else {
                        gui.getOprnproject().ProjetSettingsOpenner(pl,projet);
                        e.setCancelled(true);
                        return;
                    }
                }else {
                    gui.getOprnproject().ProjetOpenner(pl,projet);
                    e.setCancelled(true);
                    return;
                }

            } else {
                e.setCancelled(true);
                return;
            }

        }

        if (e.getInventory().getName().equalsIgnoreCase("§8Qb §7Creator")) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case GRASS:
                    if (pl.getItemInHand().getType() == null ||pl.getItemInHand().getType() == Material.AIR){
                        pl.sendMessage("§cMerci d'avoir un item en main !");
                        e.setCancelled(true);
                        return;
                    } else {
                        InstanceOfMain.playerCaches.get(pl).setItemview(pl.getItemInHand());
                        //InstanceOfMain.playerCaches.get(pl).setSelection(SelectionOnCreator.ItemviewProjects);
                        e.setCancelled(true);
                        return;
                    }
                case FEATHER:
                    InstanceOfMain.playerCaches.get(pl).setSelection(SelectionOnCreator.name);
                    e.setCancelled(true);
                    pl.closeInventory();
                    return;
                case RECORD_12:
                    gui.getGuiSelection().SelectionMotivation(pl);
                    //Gui Motivation
                    e.setCancelled(true);
                    return;
                case PAPER:
                    gui.getGuiSelection().SelectionCatégories(pl);
                    e.setCancelled(true);
                    return;
                case CONCRETE_POWDER:
                    if (!(InstanceOfMain.playerCaches.get(pl).getItemview() == null)) {
                        if (!(InstanceOfMain.playerCaches.get(pl).getNameProject() == null)) {
                            if (!(InstanceOfMain.playerCaches.get(pl).getCategorieProjet() == null)) {
                                if (!(InstanceOfMain.playerCaches.get(pl).getMotivationProject() == null)) {
                                    QbProjects projet = new QbProjects(InstanceOfMain.playerCaches.get(pl).getNameProject(), InstanceOfMain.playerCaches.get(pl).getCategorieProjet(), InstanceOfMain.playerCaches.get(pl).getItemview(), new ArrayList<>(),InstanceOfMain.playerCaches.get(pl).getMotivationProject());
                                    InstanceOfMain.Projects.add(projet);
                                    InstanceOfMain.playerCaches.remove(pl);
                                    InstanceOfMain.playerCaches.put(pl, new QbPlayer());
                                    InstanceOfMain.save();
                                    pl.sendMessage("Créer avec succes");
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                    return;
                case BARRIER:
                    pl.closeInventory();
                    e.setCancelled(true);
                    e.getWhoClicked();
                    return;
            }
        } else if (e.getInventory().getName().equalsIgnoreCase("§8Qb §7Creator Motivation")) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case DIAMOND:
                    InstanceOfMain.playerCaches.get(pl).setMotivationProject(Motivations.MarketPlace);
                    gui.CreateProject(pl);
                    e.setCancelled(true);
                    return;
                case TIPPED_ARROW:
                    InstanceOfMain.playerCaches.get(pl).setMotivationProject(Motivations.Projet);
                    gui.CreateProject(pl);
                    e.setCancelled(true);
                    return;
                case GOLD_INGOT:
                    InstanceOfMain.playerCaches.get(pl).setMotivationProject(Motivations.Commision);
                    gui.CreateProject(pl);
                    e.setCancelled(true);
                    return;
            }
        } else if (e.getInventory().getName().equalsIgnoreCase("§8Qb §7Creator Categories")) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case SKULL_ITEM:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cEvenementielle")) {
                        InstanceOfMain.playerCaches.get(pl).setCategorieProjet(Categorie.Evenementielle);
                        gui.CreateProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cProjet de team")) {
                        InstanceOfMain.playerCaches.get(pl).setCategorieProjet(Categorie.TeamProject);
                        gui.CreateProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBundle")) {
                        InstanceOfMain.playerCaches.get(pl).setCategorieProjet(Categorie.Bundle);
                        gui.CreateProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Projets des membres")) {
                        InstanceOfMain.playerCaches.get(pl).setCategorieProjet(Categorie.MemberProjects);
                        gui.CreateProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStelerio")) {
                        InstanceOfMain.playerCaches.get(pl).setCategorieProjet(Categorie.Stelerio);
                        gui.CreateProject(pl);
                        e.setCancelled(true);
                        return;
                    }
            }


        } else if (e.getInventory().getName().equalsIgnoreCase("§8Qb §7Categories")) {
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                e.setCancelled(true);

            }
            switch (e.getCurrentItem().getType()) {
                case SKULL_ITEM:
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cEvenementielle")) {
                        gui.getListgui().ListEvenement(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cProjet de team")) {
                        gui.getListgui().ListTeamProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBundle")) {
                        gui.getListgui().ListBundle(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Projets des membres")) {
                        gui.getListgui().ListMemberProject(pl);
                        e.setCancelled(true);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStelerio")) {
                        gui.getListgui().ListStelerio(pl);
                        e.setCancelled(true);
                        return;
                    }

            }
        } else if (e.getInventory().getName().contains("§8Qb §7Project ")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6")) {
                e.setCancelled(true);
                return;
            }
            QbProjects project = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§8Qb §7Project ",""));
            if(e.getCurrentItem().getType() == Material.ARROW && e.getCurrentItem().getItemMeta().getDisplayName().contains("§6Page")){
                e.setCancelled(true);
                return;
            }

            if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                InstanceOfMain.playerCaches.get(pl).setFiniEncours(Affichage.ALL);
                gui.getListgui().SelectionList(pl);
                e.setCancelled(true);
                return;
            }



            if(e.getCurrentItem().getType() == Material.CONCRETE_POWDER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Affichage: Fini")){
                InstanceOfMain.playerCaches.get(pl).setFiniEncours(Affichage.ENCOURS); //fini
                gui.getOprnproject().ProjetOpenner(pl,project);
                e.setCancelled(true);
                pl.sendMessage(String.valueOf(InstanceOfMain.playerCaches.get(pl).isFiniEncours()));
                return;
            }

            if(e.getCurrentItem().getType() == Material.CONCRETE_POWDER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Affichage: All")){
                InstanceOfMain.playerCaches.get(pl).setFiniEncours(Affichage.FINI); //tout
                gui.getOprnproject().ProjetOpenner(pl,project);
                e.setCancelled(true);
                pl.sendMessage(String.valueOf(InstanceOfMain.playerCaches.get(pl).isFiniEncours()));
                return;
            }

            if(e.getCurrentItem().getType() == Material.CONCRETE_POWDER && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Affichage: En cour")){
                InstanceOfMain.playerCaches.get(pl).setFiniEncours(Affichage.ALL); //en cour
                gui.getOprnproject().ProjetOpenner(pl,project);
                e.setCancelled(true);
                pl.sendMessage(String.valueOf(InstanceOfMain.playerCaches.get(pl).isFiniEncours()));
                return;
            }



            QbUnderProject underProject = project.getUnderProjectByName(e.getCurrentItem().getItemMeta().getDisplayName());
            if (e.getClick() == ClickType.LEFT){
                pl.teleport(underProject.getLocationMc());
                e.setCancelled(true);
                return;
            } else if (e.getClick() == ClickType.DROP){
                if(underProject.getAvancement() == Avancement.Encours){
                    underProject.setAvancement(Avancement.Fini);
                    InstanceOfMain.save();
                } else {
                    underProject.setAvancement(Avancement.Encours);
                    InstanceOfMain.save();
                }
                gui.getOprnproject().ProjetOpenner(pl, project);
                e.setCancelled(true);
                return;
            }else {
                e.setCancelled(true);
                return;
            }
        } else if (e.getInventory().getName().contains("§8Qb §7ProjectSettings ")){
            if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6")) {
                e.setCancelled(true);
                return;
            }

            if (e.getCurrentItem().getType() == Material.CONCRETE_POWDER){
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Créer un projet")){
                    InstanceOfMain.playerCaches.get(pl).setSelection(SelectionOnCreator.addUnderProject);
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(e.getInventory().getName().replace("§8Qb §7ProjectSettings ",""));
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSupprimer un projet")){
                    InstanceOfMain.playerCaches.get(pl).setSelection(SelectionOnCreator.rmUnderProject);
                    InstanceOfMain.playerCaches.get(pl).setProjetCache(e.getInventory().getName().replace("§8Qb §7ProjectSettings ",""));
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Supprimer le rangement")){
                    QbProjects projet = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§8Qb §7ProjectSettings ",""));
                    InstanceOfMain.Projects.remove(projet);
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                }

            }



        }
    }
}
