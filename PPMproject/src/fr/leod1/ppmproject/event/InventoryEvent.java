package fr.leod1.ppmproject.event;

import fr.leod1.ppmproject.Inventorygui.CreationGUI;
import fr.leod1.ppmproject.ppmcreatorprojects.*;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.InputStream;
import java.time.Instant;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class InventoryEvent implements Listener {
    public CreationGUI gui = new CreationGUI();

    @EventHandler
    public void onclick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player pl = (Player) e.getWhoClicked();
            PPMcreatorProject Projplayer = InstanceOfMain.getPPMcreator(pl);
            if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects creator")) {
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                switch (e.getCurrentItem().getType()){
                    case FEATHER:
                        pl.closeInventory();
                        Projplayer.setWatIsSelect(StatuesOfSelect.SetNameOfProject);
                        e.setCancelled(true);
                        break;
                    case RECORD_3:
                        pl.closeInventory();
                        gui.motivGui(pl);
                        Projplayer.setWatIsSelect(StatuesOfSelect.SetMotivationOfPproject);
                        e.setCancelled(true);
                        break;
                    case ENDER_PORTAL_FRAME:
                        pl.closeInventory();
                        Projplayer.setSpawnOfProject(pl.getLocation());
                        gui.CreateInventory(pl);
                        e.setCancelled(true);
                        break;
                    case EMERALD:
                        pl.closeInventory();
                        Projplayer.setWatIsSelect(StatuesOfSelect.SetTypeOfproject);
                        gui.typeGui(pl);
                        e.setCancelled(true);
                        break;
                    case MAGENTA_GLAZED_TERRACOTTA:
                        if (Projplayer.getChroma() == PPMCromas.smooth){
                            Projplayer.setChroma(PPMCromas.ppm);
                        } else {
                            Projplayer.setChroma(PPMCromas.smooth);
                        }
                        e.setCancelled(true);
                        gui.CreateInventory(pl);
                        e.setCancelled(true);
                        break;
                    case BOOK:
                        pl.closeInventory();
                        Projplayer.setWatIsSelect(StatuesOfSelect.SetOneOfNamesOfMenber);
                        e.setCancelled(true);
                        break;
                    case GRASS:
                        pl.closeInventory();
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Projplayer.setWatIsSelect(StatuesOfSelect.SetBlockViews);
                            }
                        }.runTaskLater(InstanceOfMain, 10);
                        e.setCancelled(true);
                        break;
                    case WATER_LILY:
                        e.setCancelled(true);
                        if (!(Projplayer.getSpawnOfProject() == null)){
                            if (!(Projplayer.getNameOfProject() == null)){
                                if (!(Projplayer.getMotivationOfPproject() == null)){
                                    if (!(Projplayer.getItemviews() == null)){
                                    if (!(Projplayer.getTypeOfproject() == null)){
                                        if (InstanceOfMain.containProjectbyName(Projplayer.getNameOfProject())){
                                            pl.sendMessage("Désolé, ce projet exist déjà.");
                                            e.setCancelled(true);
                                            break;
                                        }
                                        InstanceOfMain.AddProjets(Projplayer, pl);
                                        pl.sendMessage("Ajoutez correctement");
                                        InstanceOfMain.ClearCacheOfPlayer(pl);
                                        pl.closeInventory();
                                        e.setCancelled(true);
                                    }
                                    break;
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        break;
                        //Make this really

                    case BARRIER:
                        e.setCancelled(true);
                        Projplayer.setWatIsSelect(null);
                        pl.closeInventory();
                        break;

                    case STAINED_GLASS_PANE:
                        e.setCancelled(true);
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Motivation")){
                switch (e.getCurrentItem().getType()){
                    case GOLD_BLOCK:
                        Projplayer.setMotivationOfPproject("Argent");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                    case COOKED_BEEF:
                        Projplayer.setMotivationOfPproject("Projet personnelle");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Types")){
                switch (e.getCurrentItem().getType()){
                    case TOTEM:
                        Projplayer.setTypeOfproject("Organique");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                    case WOOD:
                        Projplayer.setTypeOfproject("Architecture");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                    case GRASS:
                        Projplayer.setTypeOfproject("Terraforming");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                    case STONE:
                        Projplayer.setTypeOfproject("Giant project");
                        gui.CreateInventory(pl);
                        Projplayer.setWatIsSelect(null);
                        break;
                }
            }else if (e.getInventory().getName().contains("§cPPM Projects §8Modif Type ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §8Modif Type ",""));
                switch (e.getCurrentItem().getType()){
                    case TOTEM:
                        ProjetTarget.setTypeOfproject("Organique");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case WOOD:
                        ProjetTarget.setTypeOfproject("Architecture");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case GRASS:
                        ProjetTarget.setTypeOfproject("Terraforming");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case STONE:
                        ProjetTarget.setTypeOfproject("Giant project");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case BARRIER:
                        gui.ModifProject(pl,ProjetTarget);
                        e.setCancelled(true);
                        return;

                }


            } else if (e.getInventory().getName().contains("§cPPM Projects §8Modif addpl ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §8Modif addpl ",""));

                if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    ProjetTarget.getNamesOfMenber().add(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                }else {
                    e.setCancelled(true);
                    return;
                }

            }else if (e.getInventory().getName().contains("§cPPM Projects §8Modif rmpl ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §8Modif rmpl ",""));

                if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    ProjetTarget.getNamesOfMenber().remove(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                }else {
                    e.setCancelled(true);
                    return;
                }

            }else if (e.getInventory().getName().contains("§cPPM Projects §8Modif owner ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §8Modif owner ",""));

                if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    ProjetTarget.setNameOfOwner(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    if (!ProjetTarget.getNamesOfMenber().contains(pl.getName())){
                        ProjetTarget.getNamesOfMenber().add(pl.getName());
                    }

                    if (ProjetTarget.getNamesOfMenber().contains(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""))){
                        ProjetTarget.getNamesOfMenber().remove(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    }

                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                } else {
                    e.setCancelled(true);
                    return;
                }

            } else if (e.getInventory().getName().contains("§cPPM Projects §8Modif Motive ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §8Modif Motive ",""));

                if (e.getCurrentItem().getType() == Material.COOKED_BEEF){
                    ProjetTarget.setMotivationOfPproject("Projet personnelle");
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.GOLD_BLOCK){
                    ProjetTarget.setMotivationOfPproject("Argent");
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ModifProject(pl,ProjetTarget);
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().contains("§cPPM Projects §7PermRm §8")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                    gui.permInventory(pl);
                    e.setCancelled(true);
                    return;
                }

                InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Projects §7PermRm §8",""))).getProjetAllowToPlayer().remove(e.getCurrentItem().getItemMeta().getDisplayName());
                if (InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Projects §7PermRm §8",""))).getFavori().contains(e.getCurrentItem().getItemMeta().getDisplayName())){
                    InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Projects §7PermRm §8",""))).getFavori().remove(e.getCurrentItem().getItemMeta().getDisplayName());
                }
                e.setCancelled(true);
                gui.permRmInventory(pl, e.getInventory().getName().replace("§cPPM Projects §7PermRm §8",""));
                return;

            } else if (e.getInventory().getName().contains("§cPPM Projects §7PermAdd §8")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                    gui.permInventory(pl);
                    e.setCancelled(true);
                    return;
                }

                InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(e.getInventory().getName().replace("§cPPM Projects §7PermAdd §8",""))).getProjetAllowToPlayer().add(e.getCurrentItem().getItemMeta().getDisplayName());
                e.setCancelled(true);
                gui.permAddInventory(pl, e.getInventory().getName().replace("§cPPM Projects §7PermAdd §8",""));
                return;

            } else if (e.getInventory().getName().contains("§cPPM Projects §7Modif ")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                PPMprojects ProjetTarget = InstanceOfMain.getProjectsByName(e.getInventory().getName().replace("§cPPM Projects §7Modif ",""));

                switch (e.getCurrentItem().getType()){
                    case EMERALD:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Modifier le type du projet")){
                            gui.ModifType(pl, ProjetTarget);
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case BOOK:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Ajouter un membre")){
                            gui.ModifaddMenber(pl,ProjetTarget);
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case PAPER:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Remouve un membre")){
                            gui.ModifrmMenber(pl,ProjetTarget);
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case COMPASS:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Permet de modifier le spawn")){
                            ProjetTarget.setSpawnOfProject(pl.getLocation());
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case DIAMOND:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Permet de céder l'owner")){
                            gui.ModifOwner(pl,ProjetTarget);
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case RECORD_3:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Modifier la motivation du projet")){
                            gui.ModifMotivatio(pl,ProjetTarget);
                            e.setCancelled(true);
                            return;
                        }
                        e.setCancelled(true);
                        return;
                    case CLAY_BALL:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Modifier l'apparence du projet")){

                        }
                        e.setCancelled(true);
                        return;
                    case BARRIER:
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                }

            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects")){
                if(e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Page suivante")){
                    InstanceOfMain.getPPMcreator(pl).setPage(InstanceOfMain.getPPMcreator(pl).getPage() + 1);
                    e.setCancelled(true);
                    gui.ListInventory(pl);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Page précédente")){
                    if (InstanceOfMain.getPPMcreator(pl).getPage() == 0){
                        e.setCancelled(true);
                        gui.ListInventory(pl);
                        return;
                    }else {
                        InstanceOfMain.getPPMcreator(pl).setPage(InstanceOfMain.getPPMcreator(pl).getPage() - 1);
                        e.setCancelled(true);
                        gui.ListInventory(pl);
                        return;
                    }
                } else if  (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cFavori")){
                    PPMDataPlayer ok = InstanceOfMain.getDataPlayer(pl);
                    ok.sortrmproject();
                    InstanceOfMain.getPPMcreator(pl).setSelection(SelectionOnList.Favori);
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPhiltres")){
                    gui.Philtre(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter")){
                    InstanceOfMain.getPPMcreator(pl).setPage(0);
                    pl.closeInventory();
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cNormal")){
                    InstanceOfMain.getPPMcreator(pl).setSelection(SelectionOnList.Normal);
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cGerer les projets des visiteurs")){
                    gui.permInventory(pl);
                    e.setCancelled(true);
                    return;
                }

                PPMprojects projetTarget;
                if (InstanceOfMain.containProjectbyName(e.getCurrentItem().getItemMeta().getDisplayName())){
                    projetTarget = InstanceOfMain.getProjectsByName(e.getCurrentItem().getItemMeta().getDisplayName());
                } else {
                    e.setCancelled(true);
                    return;
                }

                if(e.getClick() == ClickType.LEFT){
                 if (InstanceOfMain.containProjectbyName(e.getCurrentItem().getItemMeta().getDisplayName())){
                    gui.UnderProjects(pl,projetTarget);
                    //pl.teleport(projetTarget.getSpawnOfProject());
                    e.setCancelled(true);
                }
                } else if (e.getClick() == ClickType.RIGHT){
                    if (InstanceOfMain.containProjectbyName(e.getCurrentItem().getItemMeta().getDisplayName())){
                        gui.ManagerOfProject(pl,projetTarget);
                    }
                } else if (e.getClick() == ClickType.DROP){
                    if (projetTarget.getNamesOfMenber().contains(pl.getName()) || projetTarget.getNameOfOwner().equalsIgnoreCase(pl.getName()) || pl.getName().equalsIgnoreCase("leod1")){
                        gui.ModifProject(pl,projetTarget);
                    } else {
                        pl.sendMessage("Désolé, tu ne fait partie du projet");
                    }
                    e.setCancelled(true);
                    return;
                } else {
                    e.setCancelled(true);
                    return;
                }
            }else if (e.getInventory().getName().contains("§cPPM Project §7")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == null || e.getCurrentItem().getType() == Material.AIR){
                    return;
                }
                PPMprojects projet = InstanceOfMain.getProjectsByName(e.getInventory().getTitle().replace("§cPPM Project §7","").replace("§8",""));
                switch (e.getCurrentItem().getType()){
                    case STAINED_GLASS_PANE:
                        e.setCancelled(true);
                        break;
                    case MAGMA_CREAM:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cSupprimer le projet")){
                            if(pl.getName().equalsIgnoreCase(projet.getNameOfOwner()) || pl.getName().equalsIgnoreCase("leod1") || pl.getName().equalsIgnoreCase("Browlin_")){

                                InstanceOfMain.rmProjets(projet);
                                gui.ListInventory(pl);

                                File delete = new File(InstanceOfMain.getDataFolder().getPath()+ "/projects/"+projet.getNameOfProject()+".json" );
                                delete.delete();
                                return;
                            }
                            pl.sendMessage("Ce n'est pas ton projet, donc tu ne peut pas le supprimer");
                            //PPMprojects projet = InstanceOfMain.getProjectsByName(e.getCurrentItem().getItemMeta().getDisplayName().replace("§cSupprimer le projet §8","").replace("§8",""));
                        }
                        e.setCancelled(true);
                        break;
                    case SPECKLED_MELON:
                        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§cAjouter un sous projet à §8")){
                            if (projet.PlayerIsinProject(pl.getName())){
                                PPMcreatorProject playerce = InstanceOfMain.getPPMcreator(pl);
                                playerce.setCacheProjects(projet);
                                playerce.setWatIsSelect(StatuesOfSelect.addUnderProject);
                                pl.closeInventory();
                                e.setCancelled(true);
                                break;
                            } else {
                                pl.sendMessage("Désolé, mais tu ne fait pas parti du projet");
                                e.setCancelled(true);
                                break;
                            }
                        }
                        e.setCancelled(true);
                        break;
                    case MELON:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§cSupprimer un sous projet à §8")){
                            if (projet.PlayerIsinProject(pl.getName())) {
                                PPMcreatorProject playerce = InstanceOfMain.getPPMcreator(pl);
                                playerce.setCacheProjects(projet);
                                pl.closeInventory();
                                playerce.setWatIsSelect(StatuesOfSelect.rmUnderProject);
                                e.setCancelled(true);
                                break;
                            } else {
                                pl.sendMessage("Désolé, mais tu ne fait pas parti du projet");
                                e.setCancelled(true);
                                break;
                            }
                        }
                        e.setCancelled(true);
                        break;
                    case BARRIER:
                        e.setCancelled(true);
                        gui.ListInventory(pl);
                        break;
                    case GOLD_INGOT:
                        PPMDataPlayer dataPlayer = InstanceOfMain.getDataPlayer(pl);
                        if (!(dataPlayer.getFavori().contains(projet.getNameOfProject()))){
                            dataPlayer.getFavori().add(projet.getNameOfProject());
                            e.setCancelled(true);
                            gui.ManagerOfProject(pl,projet);
                            break;
                        }
                        pl.sendMessage("Vous avez déjà ce projet en favori");
                        e.setCancelled(true);
                        break;
                        //Favorie
                    case BLAZE_POWDER:
                        dataPlayer = InstanceOfMain.getDataPlayer(pl);
                        if ((dataPlayer.getFavori().contains(projet.getNameOfProject()))){
                            dataPlayer.getFavori().remove(projet.getNameOfProject());
                            e.setCancelled(true);
                            gui.ManagerOfProject(pl,projet);
                            break;
                        }
                        pl.sendMessage("Vous n'avez pas ce projet en favori");
                        e.setCancelled(true);
                        break;
                }
            }else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Perm")){
                if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    if (e.getClick() == ClickType.LEFT){
                        gui.permAddInventory(pl, e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                        e.setCancelled(true);
                        return;
                    }else if (e.getClick() == ClickType.RIGHT){
                        gui.permRmInventory(pl, e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                        e.setCancelled(true);
                        return;
                    } else {
                        e.setCancelled(true);
                        return;
                    }
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getType() == Material.MAP){
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrier les projects")){
                        e.setCancelled(true);
                        gui.PhiltreTriage(pl);
                        return;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrier les projets afficher")){
                        e.setCancelled(true);
                        gui.PhiltreAffichage(pl);
                        return;
                    }else {
                        e.setCancelled(true);
                        return;
                    }

                }else if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.ListInventory(pl);
                } else {
                    e.setCancelled(true);
                    return;
                }

            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre Affichage creator")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.PhiltreAffichage(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.SKULL_ITEM){
                    InstanceOfMain.getPPMcreator(pl).setPhiltreNameTarget(e.getCurrentItem().getItemMeta().getDisplayName().replace("§6",""));
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre Affichage Motive")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getType() == Material.COOKED_BEEF){
                    InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Projet personnelle");
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.GOLD_BLOCK){
                    InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Argent");
                    gui.ListInventory(pl);
                    e.setCancelled(true);
                    return;
                } else if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.PhiltreAffichage(pl);
                    e.setCancelled(true);
                    return;
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre Affichage")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                if (e.getCurrentItem().getType() == Material.MAP){
                    switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                        case "§cAfficher tous les projets du joueur":
                            gui.PhiltreAffichageAllCreator(pl);
                            e.setCancelled(true);
                            return;
                        case "§cAfficher tous les projets de la motivation choisi" :
                            gui.PhiltreAffichageMotive(pl);
                            e.setCancelled(true);
                            return;
                        case "§cAfficher tous les projets du type choisi":
                            gui.PhiltreAffichageTypes(pl);
                            e.setCancelled(true);
                            return;
                    }
                } else if (e.getCurrentItem().getType() == Material.BARRIER){
                    gui.Philtre(pl);
                    e.setCancelled(true);
                    return;
                }
            }else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre Affichage Type")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }

                switch (e.getCurrentItem().getType()){
                    case WOOD:
                        InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Architecture");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case TOTEM:
                        InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Organique");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case GRASS:
                        InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Terraforming");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case STONE:
                        InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv("Giant project");
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        return;
                    case BARRIER:
                        gui.PhiltreAffichage(pl);
                        e.setCancelled(true);
                        return;


                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§cPPM Projects §7Philtre Triage")){
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
                    e.setCancelled(true);
                    return;
                }
                PPMcreatorProject ppMcreatorProject = InstanceOfMain.getPPMcreator(pl);

                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrier dans l'ordre alphabétique")){
                    e.setCancelled(true);
                    ppMcreatorProject.setPhiltreSelections(PhiltreSelection.Alphabétique);
                    gui.ListInventory(pl);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrier dans l'ordre des dates")){
                    e.setCancelled(true);
                    ppMcreatorProject.setPhiltreSelections(PhiltreSelection.Date);
                    gui.ListInventory(pl);
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cTrier dans l'ordre des paricipants")){
                    e.setCancelled(true);
                    ppMcreatorProject.setPhiltreSelections(PhiltreSelection.NombreAide);
                    gui.ListInventory(pl);
                }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cQuitter")){
                    gui.Philtre(pl);
                    e.setCancelled(true);
                }

                /*switch (e.getCurrentItem().getItemMeta().getDisplayName()){
                    case "§cTrier dans l'ordre alphabétique":
                        e.setCancelled(true);
                        ppMcreatorProject.setPhiltreSelections(PhiltreSelection.Alphabétique);
                        gui.ListInventory(pl);
                        break;
                    case"§cTrier avec l'owner séléctionner":
                        e.setCancelled(true);
                        ppMcreatorProject.setPhiltreSelections(PhiltreSelection.Propriétaire);
                        break;
                    case "§cTrier dans l'ordre des dates":
                        e.setCancelled(true);
                        ppMcreatorProject.setPhiltreSelections(PhiltreSelection.Date);
                        gui.ListInventory(pl);
                        break;
                    case "§cTrier dans l'ordre des paricipants":
                        e.setCancelled(true);
                        ppMcreatorProject.setPhiltreSelections(PhiltreSelection.NombreAide);
                        gui.ListInventory(pl);
                        break;
                    case "§cQuitter":
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        break;

                }*/

            } else if (e.getInventory().getName().contains("§cPPM UnderProjects §7")){
                PPMprojects projet = InstanceOfMain.getProjectsByName(e.getInventory().getTitle().replace("§cPPM UnderProjects §7","").replace("§8",""));
                if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                switch (e.getCurrentItem().getType()){
                    case BARRIER:
                        InstanceOfMain.getPPMcreator(pl).setPageUnder(0);
                        gui.ListInventory(pl);
                        e.setCancelled(true);
                        break;
                    case PAPER:
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Page suivante")){
                            InstanceOfMain.getPPMcreator(pl).setPageUnder(InstanceOfMain.getPPMcreator(pl).getPageUnder() + 1);
                            gui.UnderProjects(pl,projet);
                            e.setCancelled(true);
                            break;
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Page précédente")){
                            if (InstanceOfMain.getPPMcreator(pl).getPageUnder() == 0){
                                e.setCancelled(true);
                                gui.UnderProjects(pl,projet);
                                return;
                            }else {
                                InstanceOfMain.getPPMcreator(pl).setPageUnder(InstanceOfMain.getPPMcreator(pl).getPageUnder() - 1);
                                e.setCancelled(true);
                                gui.UnderProjects(pl,projet);
                                return;
                            }
                        }
                        e.setCancelled(true);
                        break;
                    case LADDER:
                        e.setCancelled(true);
                        break;
                    case GRASS:
                        InstanceOfMain.getPPMcreator(pl).setPage(0);
                        InstanceOfMain.getPPMcreator(pl).setPageUnder(0);
                        pl.teleport(projet.getSpawnOfProject());
                        e.setCancelled(true);
                        break;
                    case ENDER_PEARL:
                        InstanceOfMain.getPPMcreator(pl).setPage(0);
                        InstanceOfMain.getPPMcreator(pl).setPageUnder(0);
                        pl.teleport(projet.getUnderProjetctbyName(e.getCurrentItem().getItemMeta().getDisplayName()).getSpawnOfProject());
                        e.setCancelled(true);
                        break;

                }
            }
            if (Projplayer.getWatIsSelect() == StatuesOfSelect.SetBlockViews){
                if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null){
                    return;
                }
                Projplayer.setItemviews(e.getCurrentItem());
                Projplayer.setWatIsSelect(null);
                e.setCancelled(true);
                gui.CreateInventory(pl);
            }
        }
    }
}
