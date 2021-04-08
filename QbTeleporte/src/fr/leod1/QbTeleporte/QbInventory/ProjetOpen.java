package fr.leod1.QbTeleporte.QbInventory;

import fr.leod1.QbTeleporte.QbPlayer.Affichage;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.Avancement;
import fr.leod1.QbTeleporte.QbProjects.QbUnderProject.QbUnderProject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class ProjetOpen extends CreationGuiSelection {

    public void ProjetOpenner(Player pl, QbProjects projet){

        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Project " + projet.getName());


        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



        ItemStack Encour = new ItemStack(Material.CONCRETE_POWDER);
        ItemMeta metaEncour = Encour.getItemMeta();
        metaEncour.setDisplayName("§6Affichage: En cour");
        Encour.setItemMeta(metaEncour);
        Encour.setDurability((short) 14);  //encour

        ItemStack all = new ItemStack(Material.CONCRETE_POWDER);
        ItemMeta metaall = all.getItemMeta();
        metaall.setDisplayName("§6Affichage: All");
        all.setItemMeta(metaall);
        all.setDurability((short) 1); //fini

        ItemStack fini = new ItemStack(Material.CONCRETE_POWDER);
        ItemMeta metafini = fini.getItemMeta();
        metafini.setDisplayName("§6Affichage: Fini");
        fini.setItemMeta(metafini);
        fini.setDurability((short) 5); //tout



        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);



        for (int i = 0; i < 9; i++){
            if(i >= 3){
                if (i >= 6){
                    inv.setItem(i,fast(15));
                }else {
                    inv.setItem(i,fast(7));
                }
            } else {
                inv.setItem(i,fast(0));
            }
        }
        for (int i = 45; i < 54; i++){
            if(i >= 48){
                if (i >= 51){
                    inv.setItem(i,fast(0));
                }else {
                    inv.setItem(i,fast(7));
                }
            } else {
                inv.setItem(i,fast(15));
            }
        }

        inv.setItem(44, PageSuivante);
        inv.setItem(43, Pageprécédent);
        inv.setItem(49, Leave);
        if (InstanceOfMain.playerCaches.get(pl).isFiniEncours() == Affichage.ALL){
            inv.setItem(42, all);
        } else if (InstanceOfMain.playerCaches.get(pl).isFiniEncours() == Affichage.FINI){
            inv.setItem(42, fini);
        }else {
            inv.setItem(42,Encour);
        }


        //
         ArrayList<QbUnderProject> FinaA = new ArrayList<>();
        for (int i = 0; i < projet.getUnderQbProjet().size(); i++){
            QbUnderProject sousprojet = projet.getUnderQbProjet().get(i);
            if (InstanceOfMain.playerCaches.get(pl).isFiniEncours() == Affichage.FINI){
                if (sousprojet.getAvancement() == Avancement.Fini){
                    FinaA.add(sousprojet);
                }
            } else if(InstanceOfMain.playerCaches.get(pl).isFiniEncours() == Affichage.ENCOURS){
                if (sousprojet.getAvancement() == Avancement.Encours){
                    FinaA.add(sousprojet);
                }
            } else {
                FinaA.add(sousprojet);
            }
        }
        //

        for (int i = 0; i < FinaA.size(); i++){
            QbUnderProject sousprojet = FinaA.get(i);

            /*if (InstanceOfMain.playerCaches.get(pl).isFiniEncours()){
                if (sousprojet.getAvancement() == Avancement.Encours){
                    return;
                }
            } else {
                if (sousprojet.getAvancement() == Avancement.Fini){
                    return;
                }
            }*/

            ItemStack item = sousprojet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(sousprojet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Avancement §r: " + sousprojet.getAvancement(),"",
                    "§7[Click Gauche] Téléportation au projet",
                    "§7[Drop] Changer le statue du projet"
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i + 9, item);
        }

        pl.openInventory(inv);
    }

    public void ProjetSettingsOpenner(Player pl, QbProjects projet) {

        ItemStack AddunderProjet = new ItemStack(Material.CONCRETE_POWDER,1,(short)5);
        ItemMeta metaAddunderProjet = AddunderProjet.getItemMeta();
        metaAddunderProjet.setDisplayName("§6Créer un projet");
        metaAddunderProjet.setLore(Arrays.asList("§7Lors du click, il suffit de mettre",
                "§7le nom dans le chat, et le","§7block dans la main et votre","§7possition sont pris en compte"));
        AddunderProjet.setItemMeta(metaAddunderProjet);

        ItemStack rmunderProjet = new ItemStack(Material.CONCRETE_POWDER,1,(short)1);
        ItemMeta metarmunderProjet = rmunderProjet.getItemMeta();
        metarmunderProjet.setDisplayName("§cSupprimer un projet");
        rmunderProjet.setItemMeta(metarmunderProjet);

        ItemStack PageSuivante = new ItemStack(Material.CONCRETE_POWDER,1,(short)14);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§4Supprimer le rangement");
        PageSuivante.setItemMeta(metaPageSuivante);

        Inventory inv = Bukkit.createInventory(null, 27, "§8Qb §7ProjectSettings " + projet.getName());

        for (int i = 0; i < 27; i++){
            inv.setItem(i,fast(0));
        }

        inv.setItem(10,AddunderProjet);
        inv.setItem(13,rmunderProjet);
        inv.setItem(16,PageSuivante);
        pl.openInventory(inv);

    }
}
