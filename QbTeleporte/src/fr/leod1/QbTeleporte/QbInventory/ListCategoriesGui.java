package fr.leod1.QbTeleporte.QbInventory;

import fr.leod1.QbTeleporte.QbProjects.Categorie;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

import static fr.leod1.QbTeleporte.QbInventory.CustomHead.HeadManager.createSkull;
import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class ListCategoriesGui extends CreationGuiSelection {

    private CreationGuiSelection sort = new CreationGuiSelection();
    public void ListEvenement(Player pl){
        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Categorie Evenements");

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);

        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



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



        for(QbProjects projet : InstanceOfMain.Projects){
            if (!(projet.getCategorie() == Categorie.Evenementielle)){
                Projets.remove(projet);
            }
        }

        /*for (int i = 0; i < Projets.size(); i++){
            QbProjects projet = Projets.get(i);

            ItemStack item = projet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(projet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Motivation §r: " + projet.getStringMotivation()
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i, item);
        }*/
        sort.pageRangement(Projets,InstanceOfMain.playerCaches.get(pl).getPage(),pl,inv);

        pl.openInventory(inv);

    }


    public void ListTeamProject(Player pl){
        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Categorie teamprojects");


        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



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



        for(QbProjects projet : InstanceOfMain.Projects){
            if (!(projet.getCategorie() == Categorie.TeamProject)){
                Projets.remove(projet);
            }
        }

        for (int i = 0; i < Projets.size(); i++){
            QbProjects projet = Projets.get(i);

            ItemStack item = projet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(projet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Motivation §r: " + projet.getStringMotivation(),"",
                    "§7[Click Gauche] Liste des projets",
                    "§7[Click Droit] Configurer les projets"
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i + 9, item);
        }
        pl.openInventory(inv);

    }


    public void ListBundle(Player pl){
        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Categorie bundle");


        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



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



        for(QbProjects projet : InstanceOfMain.Projects){
            if (!(projet.getCategorie() == Categorie.Bundle)){
                Projets.remove(projet);
            }
        }

        for (int i = 0; i < Projets.size(); i++){
            QbProjects projet = Projets.get(i);

            ItemStack item = projet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(projet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Motivation §r: " + projet.getStringMotivation(),"",
                    "§7[Click Gauche] Liste des projets",
                    "§7[Click Droit] Configurer les projets"
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i + 9, item);
        }
        pl.openInventory(inv);

    }

    public void ListStelerio(Player pl){
        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Categorie stelerio");


        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



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



        for(QbProjects projet : InstanceOfMain.Projects){
            if (!(projet.getCategorie() == Categorie.Stelerio)){
                Projets.remove(projet);
            }
        }

        for (int i = 0; i < Projets.size(); i++){
            QbProjects projet = Projets.get(i);

            ItemStack item = projet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName("§6"+projet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Motivation §r: " + projet.getStringMotivation(),"",
                    "§7[Click Gauche] Liste des projets",
                    "§7[Click Droit] Configurer les projets"
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i + 9, item);
        }
        pl.openInventory(inv);

    }

    public void ListMemberProject(Player pl){
        ArrayList<QbProjects> Projets = new ArrayList<>();
        Projets.addAll(InstanceOfMain.Projects);

        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Categorie memberproject");


        ItemStack PageSuivante = new ItemStack(Material.ARROW);
        ItemMeta metaPageSuivante = PageSuivante.getItemMeta();
        metaPageSuivante.setDisplayName("§6Page suivante");
        PageSuivante.setItemMeta(metaPageSuivante);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        Leave.setItemMeta(metaLeave);
        inv.setItem(49, Leave);

        ItemStack Pageprécédent = new ItemStack(Material.ARROW);
        ItemMeta metaPageprécédent = Pageprécédent.getItemMeta();
        metaPageprécédent.setDisplayName("§6Page précédent");
        Pageprécédent.setItemMeta(metaPageprécédent);



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



        for(QbProjects projet : InstanceOfMain.Projects){
            if (!(projet.getCategorie() == Categorie.MemberProjects)){
                Projets.remove(projet);
            }
        }

        for (int i = 0; i < Projets.size(); i++){
            QbProjects projet = Projets.get(i);

            ItemStack item = projet.getItemviews();
            ItemMeta itemmeta = item.getItemMeta();
            itemmeta.setDisplayName(projet.getName());
            itemmeta.setLore(Arrays.asList(
                    "§6Motivation §r: " + projet.getStringMotivation(),"",
                    "§7[Click Gauche] Liste des projets",
                    "§7[Click Droit] Configurer les projets"
            ));

            item.setItemMeta(itemmeta);
            inv.setItem(i + 9, item);
        }
        pl.openInventory(inv);

    }


    public void SelectionList(Player pl){

        ItemStack Commision = head("quanticsbuild","zebi");
        ItemMeta metaCommision = Commision.getItemMeta();
        metaCommision.setDisplayName("§cProjet de team");
        Commision.setItemMeta(metaCommision);

        ItemStack Commisiond = head.getHead("white e");
        ItemMeta metaCommisiond = Commisiond.getItemMeta();
        metaCommisiond.setDisplayName("§cEvenementielle");
        Commisiond.setItemMeta(metaCommisiond);

        ItemStack Commisionf = head.getHead("white b");
        ItemMeta metaCommisionf = Commisionf.getItemMeta();
        metaCommisionf.setDisplayName("§cBundle");
        Commisionf.setItemMeta(metaCommisionf);

        ItemStack Commisiong = head(pl.getName(),"§6Projets des membres");

        ItemStack stelerio = createSkull("e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGEzODhiNTI3MmQxMTZmYWQxMjkxODBjYjhlM2JkMjZmNjhjMTc3MTNlZDkwNmJkMTMzYjc1Y2NiMDBlY2ZiZSJ9fX0=","§cStelerio");
        ItemMeta itemmete = stelerio.getItemMeta();
        itemmete.setDisplayName("§cstelerio");
        stelerio.setItemMeta(itemmete);

        Inventory inv = Bukkit.createInventory(null, 27,"§8Qb §7Categories");
        for (int i = 0; i < 27; i++){
            inv.setItem(i,fast(Material.STAINED_GLASS_PANE));
        }
        inv.setItem(0,fast(15));
        inv.setItem(2,fast(15));
        inv.setItem(4,fast(15));
        inv.setItem(6,fast(15));
        inv.setItem(8,fast(15));
        inv.setItem(18,fast(15));
        inv.setItem(20,fast(15));
        inv.setItem(22,fast(15));
        inv.setItem(24,fast(15));
        inv.setItem(26,fast(15));

        inv.setItem(9,Commision);
        inv.setItem(11,Commisiond);
        inv.setItem(13,Commisionf);
        inv.setItem(15,stelerio);
        inv.setItem(17,Commisiong);

        pl.openInventory(inv);
    }
}
