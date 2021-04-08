package fr.leod1.ppmproject.Inventorygui;

import fr.leod1.ppmproject.ppmcreatorprojects.PPMCromas;
import fr.leod1.ppmproject.ppmcreatorprojects.PPMcreatorProject;
import fr.leod1.ppmproject.ppmcreatorprojects.SelectionOnList;
import fr.leod1.ppmproject.ppmdataplayer.PPMDataPlayer;
import fr.leod1.ppmproject.ppmprojects.PPMprojects;
import fr.leod1.ppmproject.ppmprojects.comparotor.AlphabethiqueComparator;
import fr.leod1.ppmproject.ppmprojects.comparotor.DateComparator;
import fr.leod1.ppmproject.ppmprojects.comparotor.NbParticipantComparator;
import fr.leod1.ppmproject.ppmunderproject.PPMUnderProject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.*;

import static fr.leod1.ppmproject.PPMproject.InstanceOfMain;

public class CreationGUI {
    public void ModifOwner(Player pl, PPMprojects Projet){
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);
        int i = 0;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §8Modif owner " + Projet.getNameOfProject());


        for (Player ple : Bukkit.getOnlinePlayers()){
            if (pl.getName().equalsIgnoreCase(ple.getName())){

            }else {
                SkullMeta skullm = (SkullMeta) skull.getItemMeta();
                skullm.setOwner(ple.getName());
                skullm.setDisplayName("§6" +ple.getName());
                skull.setItemMeta(skullm);
                inv.setItem(i,skull);
                i++;
            }
        }

        inv.setItem(49,retour);
        pl.openInventory(inv);
    }
    public void ModifMotivatio(Player pl, PPMprojects Projet){

        //
        ItemStack Preview = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cCommition");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.COOKED_BEEF);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cProjet passionnelle");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §8Modif Motive " + Projet.getNameOfProject());
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        inv.setItem(8,retour);
        pl.openInventory(inv);

    }
    public void ModifrmMenber(Player pl, PPMprojects Projet){
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);
        int i = 0;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §8Modif rmpl " + Projet.getNameOfProject());


        for (String ple : Projet.getNamesOfMenber()){

            SkullMeta skullm = (SkullMeta) skull.getItemMeta();
            skullm.setOwner(ple);
            skullm.setDisplayName("§6" +ple);
            skull.setItemMeta(skullm);
            inv.setItem(i,skull);
            i++;
        }

        inv.setItem(49,retour);
        pl.openInventory(inv);
    }
    public void ModifaddMenber(Player pl, PPMprojects Projet){
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        ArrayList<Player> Players = new ArrayList<>();

        Players.addAll(Bukkit.getOnlinePlayers());

        for (Player ple : Bukkit.getOnlinePlayers()){
            if (Projet.getNamesOfMenber().contains(ple.getName())){
                Players.remove(ple);
            }

            if (Projet.getNameOfOwner().equalsIgnoreCase(ple.getName())){
                Players.remove(ple);
            }
        }

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §8Modif addpl " + Projet.getNameOfProject());
        int i = 0;
        for (Player ple : Players){
            SkullMeta skullm = (SkullMeta) skull.getItemMeta();
            skullm.setOwner(ple.getName());
            skullm.setDisplayName("§6" +ple.getName());
            skull.setItemMeta(skullm);
            inv.setItem(i,skull);
            i++;
        }

        inv.setItem(49,retour);
        pl.openInventory(inv);

    }
    public void ModifType(Player pl, PPMprojects Projet){
        //
        ItemStack Preview = new ItemStack(Material.WOOD);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cArchitecture");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.TOTEM);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cOrganique");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //
        //
        ItemStack ppe = new ItemStack(Material.GRASS);
        ItemMeta metappe = ppe.getItemMeta();
        metappe.setDisplayName("§cTerraforming");
        metappe.setLore(Arrays.asList(""));
        ppe.setItemMeta(metappe);
        //
        //
        ItemStack ppz = new ItemStack(Material.STONE);
        ItemMeta metappz = ppz.getItemMeta();
        metappz.setDisplayName("§cGiant project");
        metappz.setLore(Arrays.asList(""));
        ppz.setItemMeta(metappz);
        //
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §8Modif Type " + Projet.getNameOfProject());
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        inv.setItem(2,ppe);
        inv.setItem(3,ppz);
        inv.setItem(8,retour);
        pl.openInventory(inv);
    }

    public void ModifProject(Player pl, PPMprojects Projet){

        ItemStack addmenber = new ItemStack(Material.BOOK);
        ItemMeta metaaddmenber = addmenber.getItemMeta();
        metaaddmenber.setDisplayName("§6Ajouter un membre");
        addmenber.setItemMeta(metaaddmenber);
        //

        ItemStack rmMenber = new ItemStack(Material.PAPER);
        ItemMeta metarmMenber = rmMenber.getItemMeta();
        metarmMenber.setDisplayName("§6Remouve un membre");
        rmMenber.setItemMeta(metarmMenber);
        //

        ItemStack projetType = new ItemStack(Material.EMERALD);
        ItemMeta metaprojetType = projetType.getItemMeta();
        metaprojetType.setDisplayName("§6Modifier le type du projet");
        projetType.setItemMeta(metaprojetType);
        //

        ItemStack Motive = new ItemStack(Material.RECORD_3);
        ItemMeta metaMotive = Motive.getItemMeta();
        metaMotive.setDisplayName("§6Modifier la motivation du projet");
        Motive.setItemMeta(metaMotive);
        //

        ItemStack Apparence = new ItemStack(Material.CLAY_BALL);
        ItemMeta metaApparence = Apparence.getItemMeta();
        metaApparence.setDisplayName("§6Modifier l'apparence du projet");
        Apparence.setItemMeta(metaApparence);
        //

        ItemStack resetspawn = new ItemStack(Material.COMPASS);
        ItemMeta metaPresetspawn = resetspawn.getItemMeta();
        metaPresetspawn.setDisplayName("§6Permet de modifier le spawn");
        resetspawn.setItemMeta(metaPresetspawn);
        //

        ItemStack rightt = new ItemStack(Material.DIAMOND);
        ItemMeta metarightt = rightt.getItemMeta();
        metarightt.setDisplayName("§6Permet de céder l'owner");
        rightt.setItemMeta(metarightt);
        //

        ItemStack Barrier = new ItemStack(Material.BARRIER);
        ItemMeta metaBarrier = Barrier.getItemMeta();
        metaBarrier.setDisplayName("§cQuitter");
        Barrier.setItemMeta(metaBarrier);


        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Projects §7Modif " + Projet.getNameOfProject());
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 9; i <= 17; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 4));
        }

        for(int i = 18; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }

        if (pl.getName().equalsIgnoreCase(Projet.getNameOfOwner())){

            inv.setItem(4, projetType);
            inv.setItem(9, addmenber);
            inv.setItem(11, rmMenber);
            inv.setItem(15, Motive);
            inv.setItem(17, Apparence);
            inv.setItem(21, resetspawn);
            inv.setItem(23,rightt);
            inv.setItem(22, Barrier);

        } else {
            inv.setItem(9, addmenber);
            inv.setItem(11, rmMenber);
            inv.setItem(13, projetType);
            inv.setItem(15, Motive);
            inv.setItem(17, Apparence);
            inv.setItem(22, Barrier);
        }
        pl.openInventory(inv);

    }

    public void UnderProjects(Player pl, PPMprojects Projet){

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM UnderProjects §7" + Projet.getNameOfProject());



        ////Private display
        displayUnderProject(Projet,InstanceOfMain.getPPMcreator(pl).getPageUnder(),pl,inv);


        ItemStack Pagesuivante = new ItemStack(Material.PAPER);
        ItemMeta metaPagesuivante = Pagesuivante.getItemMeta();
        metaPagesuivante.setDisplayName("§6Page suivante");
        Pagesuivante.setItemMeta(metaPagesuivante);
        //
        //
        ItemStack Pageprécedente = new ItemStack(Material.PAPER);
        ItemMeta metaPageprécedente = Pageprécedente.getItemMeta();
        metaPageprécedente.setDisplayName("§6Page précédente");
        Pageprécedente.setItemMeta(metaPageprécedente);
        //
        //
        ItemStack Philtre = new ItemStack(Material.LADDER);
        ItemMeta metaPhiltre = Philtre.getItemMeta();
        metaPhiltre.setDisplayName("§cPhiltres");
        Philtre.setItemMeta(metaPhiltre);
        //
        //
        ItemStack Barrier = new ItemStack(Material.BARRIER);
        ItemMeta metaBarrier = Barrier.getItemMeta();
        metaBarrier.setDisplayName("§cQuitter");
        Barrier.setItemMeta(metaBarrier);

        inv.setItem(48,Pageprécedente);
        inv.setItem(49,Barrier);
        inv.setItem(50,Pagesuivante);
        inv.setItem(52,Philtre);

        pl.openInventory(inv);

    }

    public void Philtre(Player pl){


        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Projects §7Philtre");
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 9; i <= 17; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 4));
        }

        for(int i = 18; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }

        ItemStack Triage = new ItemStack(Material.MAP);
        ItemMeta Triagemeta = Triage.getItemMeta();
        Triagemeta.setDisplayName("§cTrier les projects");
        Triagemeta.setLore(Arrays.asList("§7Permet de trier les projets"));
        Triage.setItemMeta(Triagemeta);

        ItemStack Affichage = new ItemStack(Material.MAP);
        ItemMeta Affichagemeta = Affichage.getItemMeta();
        Affichagemeta.setDisplayName("§cTrier les projets afficher");
        Affichagemeta.setLore(Arrays.asList("§7Permet de trier les projets afficher"));
        Affichage.setItemMeta(Affichagemeta);

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        inv.setItem(12,Triage);
        inv.setItem(14,Affichage);
        inv.setItem(22,retour);

        pl.openInventory(inv);
    }

    public void PhiltreAffichageMotive(Player pl){
        //
        ItemStack Preview = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cCommition");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.COOKED_BEEF);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cProjet passionnelle");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §7Philtre Affichage Motive");
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        inv.setItem(8,retour);
        pl.openInventory(inv);
    }

    public void PhiltreAffichageTypes(Player pl){
        //
        ItemStack Preview = new ItemStack(Material.WOOD);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cArchitecture");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.TOTEM);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cOrganique");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //
        //
        ItemStack ppe = new ItemStack(Material.GRASS);
        ItemMeta metappe = ppe.getItemMeta();
        metappe.setDisplayName("§cTerraforming");
        metappe.setLore(Arrays.asList(""));
        ppe.setItemMeta(metappe);
        //
        //
        ItemStack ppz = new ItemStack(Material.STONE);
        ItemMeta metappz = ppz.getItemMeta();
        metappz.setDisplayName("§cGiant project");
        metappz.setLore(Arrays.asList(""));
        ppz.setItemMeta(metappz);
        //
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §7Philtre Affichage Type");
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        inv.setItem(2,ppe);
        inv.setItem(3,ppz);
        inv.setItem(8,retour);
        pl.openInventory(inv);
    }

    public void PhiltreAffichageAllCreator(Player pl){
        ArrayList<String> allCreator = new ArrayList<>();

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §7Philtre Affichage Creator");
        for (PPMprojects proj : InstanceOfMain.getProjects()){
            if (allCreator.contains(proj.getNameOfOwner())){
            }else {
                allCreator.add(proj.getNameOfOwner());
            }
        }
        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());

        for (int i = 0; i < allCreator.size(); i++){
            SkullMeta skullm = (SkullMeta) skull.getItemMeta();
            skullm.setOwner(allCreator.get(i));
            skullm.setDisplayName("§6" + allCreator.get(i));
            skull.setItemMeta(skullm);
            inv.setItem(i,skull);
        }
        inv.setItem(49,retour);
        pl.openInventory(inv);

    }
    public void PhiltreAffichage(Player pl){
        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);

        ItemStack PlayerCreator = new ItemStack(Material.MAP);
        ItemMeta PlayerCreatormeta = PlayerCreator.getItemMeta();
        PlayerCreatormeta.setDisplayName("§cAfficher tous les projets du joueur");
        PlayerCreatormeta.setLore(Arrays.asList("§7Permet d'afficher tous les projets", "§7du joueur, si le joueur souhaiter","§7n'apparais pas, c'est qu'il","§7n'as aucuns projets"));
        PlayerCreator.setItemMeta(PlayerCreatormeta);

        ItemStack Motiv = new ItemStack(Material.MAP);
        ItemMeta Motivmeta = Motiv.getItemMeta();
        Motivmeta.setDisplayName("§cAfficher tous les projets de la motivation choisi");
        Motivmeta.setLore(Arrays.asList("§7Permet d'afficher tous les projets", "§7dont la motivation, est la motivation","§7choisi"));
        Motiv.setItemMeta(Motivmeta);

        ItemStack Type = new ItemStack(Material.MAP);
        ItemMeta Typemeta = Type.getItemMeta();
        Typemeta.setDisplayName("§cAfficher tous les projets du type choisi");
        Typemeta.setLore(Arrays.asList("§7Permet d'afficher tous les projets", "§7dont le type, est le type choisi"));
        Type.setItemMeta(Typemeta);

        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Projects §7Philtre Affichage");
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 9; i <= 17; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 4));
        }

        for(int i = 18; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }

        inv.setItem(22,retour);
        inv.setItem(10,PlayerCreator);
        inv.setItem(13,Motiv);
        inv.setItem(16,Type);

        pl.openInventory(inv);
    }


    public void PhiltreTriage(Player pl){
        //
        ItemStack Alphabétique = new ItemStack(Material.MAP);
        ItemMeta Alphabétiquemeta = Alphabétique.getItemMeta();
        Alphabétiquemeta.setDisplayName("§cTrier dans l'ordre alphabétique");
        Alphabétiquemeta.setLore(Arrays.asList("§7Permet de trier les projets","§7dans l'ordre alphabétique"));
        Alphabétique.setItemMeta(Alphabétiquemeta);
        //
        //
        ItemStack Propriétaire = new ItemStack(Material.MAP);
        ItemMeta Propriétairemeta = Propriétaire.getItemMeta();
        Propriétairemeta.setDisplayName("§cTrier avec l'owner séléctionner");
        Propriétairemeta.setLore(Arrays.asList("§7Après avoir clicker, ","§7rentrer le pseudo de l'owner","§7dont vous voulez voir c'est projets"));
        Propriétaire.setItemMeta(Propriétairemeta);
        //
        //
        ItemStack Date = new ItemStack(Material.MAP);
        ItemMeta Datemeta = Date.getItemMeta();
        Datemeta.setDisplayName("§cTrier dans l'ordre des dates");
        Datemeta.setLore(Arrays.asList("§7Permet de trier les projets","§7dans l'ordre du plus réscent"));
        Date.setItemMeta(Datemeta);
        //
        //
        ItemStack Participant = new ItemStack(Material.MAP);
        ItemMeta Participantmeta = Participant.getItemMeta();
        Participantmeta.setDisplayName("§cTrier dans l'ordre des paricipants");
        Participantmeta.setLore(Arrays.asList("§7Permet de trier les projets","§7dans l'ordre des paricipants"));
        Participant.setItemMeta(Participantmeta);
        //
        ItemStack leave = new ItemStack(Material.BARRIER);
        ItemMeta leavemeta = leave.getItemMeta();
        leavemeta.setDisplayName("§cQuitter");
        leave.setItemMeta(leavemeta);
        //



        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Projects §7Philtre Triage");
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 9; i <= 17; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 4));
        }

        for(int i = 18; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }

        inv.setItem(10,Alphabétique);
        //inv.setItem(12,Propriétaire);
        inv.setItem(13,Date);
        inv.setItem(16,Participant);
        inv.setItem(22,leave);
        pl.openInventory(inv);

    }

    public void ManagerOfProject(Player pl, PPMprojects proj){
        //
        ItemStack supr = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta suprmeta = supr.getItemMeta();
        suprmeta.setDisplayName("§cSupprimer le projet §8"+ proj.getNameOfProject());
        suprmeta.setLore(Arrays.asList("§7Il suffit de clicker","§7dessus pour supprimer le projet"));
        supr.setItemMeta(suprmeta);
        //
        //
        ItemStack AddunderProj = new ItemStack(Material.SPECKLED_MELON);
        ItemMeta AddunderProjmeta = AddunderProj.getItemMeta();
        AddunderProjmeta.setDisplayName("§cAjouter un sous projet à §8"+ proj.getNameOfProject());
        AddunderProjmeta.setLore(Arrays.asList("§7Il suffit de clicker","§7et d'écrire le nom du", "§7sous projet et il sera créer"));
        AddunderProj.setItemMeta(AddunderProjmeta);



        ItemStack rmunderProj = new ItemStack(Material.MELON);
        ItemMeta rmunderProjmeta = rmunderProj.getItemMeta();
        rmunderProjmeta.setDisplayName("§cSupprimer un sous projet à §8"+ proj.getNameOfProject());
        rmunderProjmeta.setLore(Arrays.asList("§7Il suffit de clicker","§7et d'écrire le nom du", "§7sous projet et il sera supprimer"));
        rmunderProj.setItemMeta(rmunderProjmeta);



        //
        //
        ItemStack Favori = new ItemStack(Material.GOLD_INGOT);
        ItemMeta Favorimeta = Favori.getItemMeta();
        Favorimeta.setDisplayName("§cMettre le projet §8"+ proj.getNameOfProject()+"§c en favori");
        Favorimeta.setLore(Arrays.asList("§7Il suffit de clicker","§7dessus pour mettre le projet en favori"));
        Favori.setItemMeta(Favorimeta);
        //
        ItemStack leave = new ItemStack(Material.BARRIER);
        ItemMeta leavemeta = leave.getItemMeta();
        leavemeta.setDisplayName("§cLeave le menu");
        leave.setItemMeta(leavemeta);
        //
        ItemStack Supp = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta Suppmeta = Supp.getItemMeta();
        Suppmeta.setDisplayName("§cSupprimer §8"+proj.getNameOfProject()+"§c de vos favoris");
        Supp.setItemMeta(Suppmeta);
        //


        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Project §7"+ proj.getNameOfProject());

        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 9; i <= 17; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 4));
        }

        for(int i = 18; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }

        inv.setItem(10,supr);
        inv.setItem(14,AddunderProj);
        inv.setItem(12,rmunderProj);
        inv.setItem(22,leave);

        PPMDataPlayer dataPlayer = InstanceOfMain.getDataPlayer(pl);
        if (!(dataPlayer.getFavori().contains(proj.getNameOfProject()))){
            inv.setItem(16, Favori);
        } else {
            inv.setItem(16, Supp);
        }

        pl.openInventory(inv);
    }

    public void permRmInventory(Player pl,String ple){
        ArrayList<PPMprojects> Projects = new ArrayList<>();
        for (String st : InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(ple)).getProjetAllowToPlayer()){
            Projects.add(InstanceOfMain.getProjectsByName(st));
        }



        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §7PermRm §8" + ple);

        displayProjectsPage(Projects,0,inv,pl);

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);
        inv.setItem(49,retour);

        pl.openInventory(inv);

    }
    public void permAddInventory(Player pl,String ple){

        ArrayList<PPMprojects> Projects = new ArrayList<>();
        Projects.addAll(InstanceOfMain.getProjects());

        PPMDataPlayer DataPle = InstanceOfMain.getDataPlayer(Bukkit.getPlayerExact(ple));

        for (PPMprojects projet : InstanceOfMain.getProjects()){
            if (DataPle.getProjetAllowToPlayer().contains(projet.getNameOfProject())){
                Projects.remove(projet);
            }
        }

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §7PermAdd §8" + ple);

        displayProjectsPage(Projects,0,inv,pl);

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);
        inv.setItem(49,retour);

        pl.openInventory(inv);
    }

    public void permInventory(Player pl){
        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects §7Perm");
        ArrayList<Player> Plays = new ArrayList<>();
        Plays.addAll(Bukkit.getOnlinePlayers());
        for (Player ple : Bukkit.getOnlinePlayers()){
            if (ple.hasPermission("Project.Fp")){
                Plays.remove(ple);
            }
        }

        ItemStack retour = new ItemStack(Material.BARRIER);
        ItemMeta retourmeta = retour.getItemMeta();
        retourmeta.setDisplayName("§cRetour");
        retour.setItemMeta(retourmeta);
        int i = 0;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());


        for (Player ple : Plays ){
                SkullMeta skullm = (SkullMeta) skull.getItemMeta();
                skullm.setOwner(ple.getName());
                skullm.setDisplayName("§6" +ple.getName());
                skull.setItemMeta(skullm);
                inv.setItem(i,skull);
                i++;
            }
        inv.setItem(49,retour);
        pl.openInventory(inv);


    }
    public void ListInventory(Player pl){
        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects");
        ArrayList<PPMprojects> Projects;
        ItemStack Favori;
        PPMDataPlayer ok;

            if(InstanceOfMain.getDataPlayer(pl) == null){
                ok = new PPMDataPlayer();
                InstanceOfMain.getPlayerData().remove(pl);
                InstanceOfMain.getPlayerData().put(pl, ok);
            }else {
                ok = InstanceOfMain.getDataPlayer(pl);
                ok.sortrmproject();
            }

        if (pl.hasPermission("Project.Fp")){

            if (InstanceOfMain.getPPMcreator(pl).getSelection() == SelectionOnList.Normal){
                Projects = InstanceOfMain.getProjects();

                Favori = new ItemStack(Material.DOUBLE_PLANT);
                ItemMeta metaFavori = Favori.getItemMeta();
                metaFavori.setDisplayName("§cFavori");
                Favori.setItemMeta(metaFavori);

            }else {
                Projects = InstanceOfMain.getPPMProjectbyPlayerFavori(InstanceOfMain.getDataPlayer(pl).getFavori());

                Favori = new ItemStack(Material.BEDROCK);
                ItemMeta metaFavori = Favori.getItemMeta();
                metaFavori.setDisplayName("§cNormal");
                Favori.setItemMeta(metaFavori);
            }
        } else {

            if (InstanceOfMain.getPPMcreator(pl).getSelection() == SelectionOnList.Normal){
                Projects = getPPMprojects(InstanceOfMain.getDataPlayer(pl).getProjetAllowToPlayer());

                Favori = new ItemStack(Material.DOUBLE_PLANT);
                ItemMeta metaFavori = Favori.getItemMeta();
                metaFavori.setDisplayName("§cFavori");
                Favori.setItemMeta(metaFavori);

            }else {


                Projects = InstanceOfMain.getPPMProjectbyPlayerFavori(InstanceOfMain.getDataPlayer(pl).getFavori());

                Favori = new ItemStack(Material.BEDROCK);
                ItemMeta metaFavori = Favori.getItemMeta();
                metaFavori.setDisplayName("§cNormal");
                Favori.setItemMeta(metaFavori);
            }

        }


        if (!(InstanceOfMain.getPPMcreator(pl).getPhiltreNameTarget() == null)){
            Projects = sortByNameOwner(Projects,InstanceOfMain.getPPMcreator(pl).getPhiltreNameTarget());
            InstanceOfMain.getPPMcreator(pl).setPhiltreNameTarget(null);
        } else if (!(InstanceOfMain.getPPMcreator(pl).getPhiltreMotiv() == null)){
            if (InstanceOfMain.getPPMcreator(pl).getPhiltreMotiv().equalsIgnoreCase("Projet personnelle")  ||  InstanceOfMain.getPPMcreator(pl).getPhiltreMotiv().equalsIgnoreCase("Argent")){
                Projects = sortByMotivation(Projects, InstanceOfMain.getPPMcreator(pl).getPhiltreMotiv());
                InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv(null);
            } else {
                Projects = sortByType(Projects, InstanceOfMain.getPPMcreator(pl).getPhiltreMotiv());
                InstanceOfMain.getPPMcreator(pl).setPhiltreMotiv(null);
            }

        }

        int page = InstanceOfMain.getPPMcreator(pl).getPage();
        sortPPMproj(pl,Projects);

        displayProjectsPage(Projects,page,inv,pl);




        //
        ItemStack Pagesuivante = new ItemStack(Material.PAPER);
        ItemMeta metaPagesuivante = Pagesuivante.getItemMeta();
        metaPagesuivante.setDisplayName("§6Page suivante");
        Pagesuivante.setItemMeta(metaPagesuivante);
        //
        //
        ItemStack Pageprécedente = new ItemStack(Material.PAPER);
        ItemMeta metaPageprécedente = Pageprécedente.getItemMeta();
        metaPageprécedente.setDisplayName("§6Page précédente");
        Pageprécedente.setItemMeta(metaPageprécedente);
        //
        //
        ItemStack Page = new ItemStack(Material.PAPER);
        //
        //
        //
        //
        ItemStack Philtre = new ItemStack(Material.LADDER);
        ItemMeta metaPhiltre = Philtre.getItemMeta();
        metaPhiltre.setDisplayName("§cPhiltres");
        Philtre.setItemMeta(metaPhiltre);
        //
        //
        //
        //
        ItemStack Barrier = new ItemStack(Material.BARRIER);
        ItemMeta metaBarrier = Barrier.getItemMeta();
        metaBarrier.setDisplayName("§cQuitter");
        Barrier.setItemMeta(metaBarrier);

        inv.setItem(46,Favori);
        inv.setItem(48,Pageprécedente);
        inv.setItem(49,Barrier);
        inv.setItem(50,Pagesuivante);
        inv.setItem(52,Philtre);
        if(pl.hasPermission("Project.Fp")){
            ItemStack Perm = new ItemStack(Material.PAPER);
            ItemMeta metaPerm = Perm.getItemMeta();
            metaPerm.setDisplayName("§cGerer les projets des visiteurs");
            Perm.setItemMeta(metaPerm);

            inv.setItem(45,Perm);
        }
        pl.openInventory(inv);
    }

    public void CreateInventory(Player pl){

        PPMcreatorProject Proj = InstanceOfMain.getPPMcreator(pl);

        String NameS;
        String Motiv;
        String Location;
        String ProjetType;


        if(Proj.getNameOfProject() == null){
            NameS = "§c§l§nNull";
        } else {
            NameS = Proj.getNameOfProject();
        }

        if(Proj.getMotivationOfPproject() == null){
            Motiv = "§c§l§nNull";
        } else {
            Motiv = Proj.getMotivationOfPproject();
        }

        if(Proj.getSpawnOfProject() == null){
            Location = "§c§l§nNull";
        } else {
            Location = "§3World= §b"+Proj.getSpawnOfProject().getWorld().getName() +" §3X= §b"+ Proj.getSpawnOfProject().getBlockX() +" §3Y= §b"+Proj.getSpawnOfProject().getBlockY()+" §3Z= §b"+Proj.getSpawnOfProject().getBlockZ();
        }

        if(Proj.getTypeOfproject() == null){
            ProjetType = "§c§l§nNull";
        } else {
            ProjetType = Proj.getTypeOfproject();
        }


        //
        ItemStack Name = new ItemStack(Material.FEATHER);
        ItemMeta metaName = Name.getItemMeta();
        metaName.setDisplayName("§cDéfinir le nom");
        metaName.setLore(Arrays.asList("§7Permet de définir le nom du project.", "Lors du click, il suffit d'écrire le nom", "dans le chat"));
        Name.setItemMeta(metaName);
        //
        //
        ItemStack Motivation = new ItemStack(Material.RECORD_3);
        ItemMeta metaMotivation = Motivation.getItemMeta();
        metaMotivation.setDisplayName("§cDéfinir la motivation");
        metaMotivation.setLore(Arrays.asList("§7Permet de définir la motivation du projet.", "Lors du click, il suffit de selectionné ","la motivation du projet", "(Argent, pa$$ion)"));
        Motivation.setItemMeta(metaMotivation);
        //
        //
        ItemStack Setspawn = new ItemStack(Material.ENDER_PORTAL_FRAME);
        ItemMeta metaSetspawn = Setspawn.getItemMeta();
        metaSetspawn.setDisplayName("§cDéfinir le spawn");
        metaSetspawn.setLore(Arrays.asList("§7Permet de définir le spawn du projet.", "Lors du click, le spawn est défini ","exactement à votre location"));
        Setspawn.setItemMeta(metaSetspawn);
        //
        //
        ItemStack TyprOfproj = new ItemStack(Material.EMERALD);
        ItemMeta metaTyprOfproj = TyprOfproj.getItemMeta();
        metaTyprOfproj.setDisplayName("§cDéfinir le type");
        metaTyprOfproj.setLore(Arrays.asList("§7Permet de définir le type du projet.", "Lors du click, il suffit de selectionné","le type du projet", "(Architecture, Oraganique, Terraforming, ","Giant Project)"));
        TyprOfproj.setItemMeta(metaTyprOfproj);
        //
        //
        ItemStack AddMenber = new ItemStack(Material.BOOK);
        ItemMeta metaAddMenber = AddMenber.getItemMeta();
        metaAddMenber.setDisplayName("§cAjouter un membre");
        metaAddMenber.setLore(Arrays.asList("§7Permet d'ajouter un menbre au projet.", "Lors du click, il suffit de marque le pseudo","du joueur que vous rajoutez au projet."));
        AddMenber.setItemMeta(metaAddMenber);
        //
        //
        ItemStack Preview = new ItemStack(Material.EYE_OF_ENDER);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cPrévisualisation");
        metaPreview.setLore(Arrays.asList("§6- "+ NameS,"§6- " +Motiv, "§6- "+ Location, "§6- "+ ProjetType,"§6- "+ Proj.getNamesOfMenber().size()));
        Preview.setItemMeta(metaPreview);
        //
        ItemStack chroma = new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA);
        ItemMeta metachroma = chroma.getItemMeta();
        metachroma.setDisplayName("§1C§5h§cr§6o§em§aa");
        chroma.setItemMeta(metachroma);
        //
        //
        ItemStack SelectBlockView = new ItemStack(Material.GRASS);
        ItemMeta metaSelectBlockView = SelectBlockView.getItemMeta();
        metaSelectBlockView.setDisplayName("§cDéfinir le block à afficher");
        SelectBlockView.setItemMeta(metaSelectBlockView);
        //
        if (Proj.getChroma() == PPMCromas.smooth){

            Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects creator");
            inv.setItem(0, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(1, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(2, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(3, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(4, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(5, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(6, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(7, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(8, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(9, Name);
            inv.setItem(10, fast(Material.STAINED_GLASS_PANE, 5));
            inv.setItem(11, Motivation);
            inv.setItem(12, fast(Material.STAINED_GLASS_PANE, 5));
            inv.setItem(13, Setspawn);
            inv.setItem(14, fast(Material.STAINED_GLASS_PANE, 5));
            inv.setItem(15, TyprOfproj);
            inv.setItem(16, fast(Material.STAINED_GLASS_PANE, 5));
            inv.setItem(17, AddMenber);
            inv.setItem(18, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(19, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(20, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(21, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(22, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(23, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(24, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(25, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(26, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(27, SelectBlockView);
            inv.setItem(28, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(29, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(30, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(31, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(32, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(33, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(34, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(35, chroma);
            inv.setItem(36, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(37, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(38, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(39, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(40, Preview);
            inv.setItem(41, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(42, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(43, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(44, fast(Material.STAINED_GLASS_PANE, 7));
            inv.setItem(45, fast(Material.WATER_LILY));
            inv.setItem(46, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(47, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(48, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(49, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(50, fast(Material.STAINED_GLASS_PANE, 11));
            inv.setItem(51, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(52, fast(Material.STAINED_GLASS_PANE, 15));
            inv.setItem(53, fast(Material.BARRIER));
            pl.openInventory(inv);
            return;

        } else {

            Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Projects creator");
            inv.setItem(0, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(1, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(2, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(3, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(4, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(5, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(6, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(7, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(8, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(9, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(10, Name);
            inv.setItem(11, Setspawn);
            inv.setItem(12, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(13, Preview);
            inv.setItem(14, fast(Material.STAINED_GLASS_PANE, 0));
            inv.setItem(15, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(16, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(17, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(18, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(19, AddMenber);
            inv.setItem(20, TyprOfproj);
            inv.setItem(21, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(22, fast(Material.STAINED_GLASS_PANE, 0));
            inv.setItem(23, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(24, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(25, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(26, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(27, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(28, Motivation);
            inv.setItem(29, SelectBlockView);
            inv.setItem(30, fast(Material.STAINED_GLASS_PANE, 0));
            inv.setItem(31, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(32, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(33, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(34, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(35, chroma);
            inv.setItem(36, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(37, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(38, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(39, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(40, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(41, fast(Material.STAINED_GLASS_PANE, 6));
            inv.setItem(42, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(43, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(44, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(45, fast(Material.WATER_LILY));
            inv.setItem(46, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(47, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(48, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(49, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(50, fast(Material.STAINED_GLASS_PANE, 1));
            inv.setItem(51, fast(Material.STAINED_GLASS_PANE, 4));
            inv.setItem(52, fast(Material.STAINED_GLASS_PANE, 14));
            inv.setItem(53, fast(Material.BARRIER));
            pl.openInventory(inv);

        }

    }

    public void motivGui(Player pl){
        //
        ItemStack Preview = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cCommition");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.COOKED_BEEF);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cProjet passionnelle");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §7Motivation");
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        pl.openInventory(inv);
    }
    public void typeGui(Player pl){
        //
        ItemStack Preview = new ItemStack(Material.WOOD);
        ItemMeta metaPreview = Preview.getItemMeta();
        metaPreview.setDisplayName("§cArchitecture");
        metaPreview.setLore(Arrays.asList(""));
        Preview.setItemMeta(metaPreview);
        //
        //
        ItemStack pp = new ItemStack(Material.TOTEM);
        ItemMeta metapp = pp.getItemMeta();
        metapp.setDisplayName("§cOrganique");
        metapp.setLore(Arrays.asList(""));
        pp.setItemMeta(metapp);
        //
        //
        ItemStack ppe = new ItemStack(Material.GRASS);
        ItemMeta metappe = ppe.getItemMeta();
        metappe.setDisplayName("§cTerraforming");
        metappe.setLore(Arrays.asList(""));
        ppe.setItemMeta(metappe);
        //
        //
        ItemStack ppz = new ItemStack(Material.STONE);
        ItemMeta metappz = ppz.getItemMeta();
        metappz.setDisplayName("§cGiant project");
        metappz.setLore(Arrays.asList(""));
        ppz.setItemMeta(metappz);
        //

        Inventory inv = Bukkit.createInventory(null,9,"§cPPM Projects §7Types");
        inv.setItem(0,Preview);
        inv.setItem(1,pp);
        inv.setItem(2,ppe);
        inv.setItem(3,ppz);
        pl.openInventory(inv);
    }





    private ItemStack fast(Material mat, int nb){
        ItemStack zebi = new ItemStack(mat);
        zebi.setDurability((short) nb);
        return zebi;
    }

    private ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }

    private ArrayList<PPMprojects> sortPPMproj(Player pl,ArrayList<PPMprojects> list){

        if (InstanceOfMain.getPPMcreator(pl).getPhiltreSelections() == null){
            return list;
        }
        switch (InstanceOfMain.getPPMcreator(pl).getPhiltreSelections()){
            case Date:
                Collections.sort(list,new DateComparator());
                return list;
            case NombreAide:
                Collections.sort(list,new NbParticipantComparator());
                return list;
            case Alphabétique:
                Collections.sort(list,new AlphabethiqueComparator());
                return list;
        }
        return list;
    }

    private Inventory displayProjectsPage(ArrayList<PPMprojects> Projects, int page, Inventory inv, Player pl){
        float PageThrori = Projects.size() / 44;
        int MaxPage = (int) Math.floor(PageThrori);

        if (page > MaxPage){
            InstanceOfMain.getPPMcreator(pl).setPage(0);
            page = 0;
        }

        if (page == 0){
            if (MaxPage == 0){
                for (int i = 0; i < Projects.size(); i++){
                    PPMprojects projet = Projects.get(i);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateStr = simpleDateFormat.format(projet.getDateOfCreationOfProject());

                    ItemStack item = projet.getItemviews();
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(projet.getNameOfProject());
                    itemmeta.setLore(Arrays.asList(
                            "§6Location §r: World= " +projet.getSpawnOfProject().getWorld().getName() +" X= "+projet.getSpawnOfProject().getBlockX()+" Y= "+ projet.getSpawnOfProject().getBlockY()+" Z= "+projet.getSpawnOfProject().getBlockZ(),
                            "§eProjet de §n"+ projet.getNameOfOwner(),
                            "§6Motivation §r: "+projet.getMotivationOfPproject(),
                            "§6Type §r: "+ projet.getTypeOfproject(),
                            "§eCréer le "+ dateStr,
                            "§6Aidé par §r: "+ projet.getNamesOfMenber()
                    ));

                    item.setItemMeta(itemmeta);
                    inv.setItem(i, item);
                }
                return inv;

            } else {
                for (int i = 0; i <= 44; i++){
                    PPMprojects projet = Projects.get(i);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateStr = simpleDateFormat.format(projet.getDateOfCreationOfProject());

                    ItemStack item = projet.getItemviews();
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(projet.getNameOfProject());
                    itemmeta.setLore(Arrays.asList(
                            "§6Location §r: World= " +projet.getSpawnOfProject().getWorld().getName() +" X= "+projet.getSpawnOfProject().getBlockX()+" Y= "+ projet.getSpawnOfProject().getBlockY()+" Z= "+projet.getSpawnOfProject().getBlockZ(),
                            "§eProjet de §n"+ projet.getNameOfOwner(),
                            "§6Motivation §r: "+projet.getMotivationOfPproject(),
                            "§6Type §r: "+ projet.getTypeOfproject(),
                            "§eCréer le "+ dateStr,
                            "§6Aidé par §r: "+ projet.getNamesOfMenber()
                    ));

                    item.setItemMeta(itemmeta);
                    inv.setItem(i, item);
                }
                return inv;
            }
        } else if (MaxPage > 0 && MaxPage > page){
            for (int i = 0; i <= 44; i++){
                PPMprojects projet = Projects.get(i + (44 * page));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = simpleDateFormat.format(projet.getDateOfCreationOfProject());

                ItemStack item = projet.getItemviews();
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(projet.getNameOfProject());
                itemmeta.setLore(Arrays.asList(
                        "§6Location §r: World= " +projet.getSpawnOfProject().getWorld().getName() +" X= "+projet.getSpawnOfProject().getBlockX()+" Y= "+ projet.getSpawnOfProject().getBlockY()+" Z= "+projet.getSpawnOfProject().getBlockZ(),
                        "§eProjet de §n"+ projet.getNameOfOwner(),
                        "§6Motivation §r: "+projet.getMotivationOfPproject(),
                        "§6Type §r: "+ projet.getTypeOfproject(),
                        "§eCréer le "+ dateStr,
                        "§6Aidé par §r: "+ projet.getNamesOfMenber()
                ));

                item.setItemMeta(itemmeta);
                inv.setItem(i, item);
            }
            return inv;
        } else if (MaxPage > 0 && MaxPage == page){
            for (int i = 0; i < Projects.size() - (page * 44); i++){
                PPMprojects projet = Projects.get(i + (44 * page));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateStr = simpleDateFormat.format(projet.getDateOfCreationOfProject());

                ItemStack item = projet.getItemviews();
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(projet.getNameOfProject());
                itemmeta.setLore(Arrays.asList(
                        "§6Location §r: World= " +projet.getSpawnOfProject().getWorld().getName() +" X= "+projet.getSpawnOfProject().getBlockX()+" Y= "+ projet.getSpawnOfProject().getBlockY()+" Z= "+projet.getSpawnOfProject().getBlockZ(),
                        "§eProjet de §n"+ projet.getNameOfOwner(),
                        "§6Motivation §r: "+projet.getMotivationOfPproject(),
                        "§6Type §r: "+ projet.getTypeOfproject(),
                        "§eCréer le "+ dateStr,
                        "§6Aidé par §r: "+ projet.getNamesOfMenber()
                ));

                item.setItemMeta(itemmeta);
                inv.setItem(i, item);
            }
            return inv;
        }
        return inv;
    }

    private Inventory displayUnderProject(PPMprojects projet, int page, Player pl, Inventory inv){
        float PageThrori = projet.getUnderProject().size() / 44;
        int MaxPage = (int) Math.floor(PageThrori);

        if (page > MaxPage){
            InstanceOfMain.getPPMcreator(pl).setPage(0);
            page = 0;
        }

        if (page == 0){
            if (MaxPage == 0){
                for (int i = 0; i < projet.getUnderProject().size(); i++){
                    //System.out.println("Zebi 1 " + page+ " " + projet.getUnderProject().size());
                    Material type;
                    PPMUnderProject underprojet = projet.getUnderProject().get(i);
                    if (underprojet.getName().equalsIgnoreCase("Main")){
                        type = Material.GRASS;
                    } else {
                        type = Material.ENDER_PEARL;
                    }
                    ItemStack item = new ItemStack(type);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(underprojet.getName());

                    item.setItemMeta(itemmeta);
                    inv.setItem(i, item);
                }
                return inv;
            } else {
                for (int i = 0; i <= 44; i++){
                    //System.out.println("Zebi 1 bis" + page);

                    Material type;
                    PPMUnderProject underprojet = projet.getUnderProject().get(i);
                    if (underprojet.getName().equalsIgnoreCase("Main")){
                        type = Material.GRASS;
                    } else {
                        type = Material.ENDER_PEARL;
                    }
                    ItemStack item = new ItemStack(type);
                    ItemMeta itemmeta = item.getItemMeta();
                    itemmeta.setDisplayName(underprojet.getName());

                    item.setItemMeta(itemmeta);
                    inv.setItem(i, item);
                }
                return inv;
            }
        } else if (MaxPage > 0 && MaxPage > page){
           // System.out.println("Zebi 2 " + page);
            for (int i = 0; i <= 44; i++){

                Material type;
                PPMUnderProject underprojet = projet.getUnderProject().get(i + (44 * page));
                if (underprojet.getName().equalsIgnoreCase("Main")){
                    type = Material.GRASS;
                } else {
                    type = Material.ENDER_PEARL;
                }
                ItemStack item = new ItemStack(type);
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(underprojet.getName());

                item.setItemMeta(itemmeta);
                inv.setItem(i, item);
            }
            return inv;
        } else {
            //System.out.println("Zebi 3 " + page);
            for (int i = 0; i < projet.getUnderProject().size() - (page * 44); i++){

                Material type;
                PPMUnderProject underprojet = projet.getUnderProject().get(i + (44 * page));
                if (underprojet.getName().equalsIgnoreCase("Main")){
                    type = Material.GRASS;
                } else {
                    type = Material.ENDER_PEARL;
                }
                ItemStack item = new ItemStack(type);
                ItemMeta itemmeta = item.getItemMeta();
                itemmeta.setDisplayName(underprojet.getName());

                item.setItemMeta(itemmeta);
                inv.setItem(i, item);
            }
            return inv;
        }
    }

    private ArrayList<PPMprojects> sortByNameOwner(ArrayList<PPMprojects> projets, String NameTarget){
        ArrayList<PPMprojects> projetToReturn = new ArrayList<>();
        projetToReturn.addAll(projets);

        for (int i=0; i < projets.size() ; i++){
            PPMprojects projet = projets.get(i);
            if (!projet.getNameOfOwner().equalsIgnoreCase(NameTarget)){
                projetToReturn.remove(projet);
            }
        }
        return projetToReturn;
    }



    private ArrayList<PPMprojects> sortByMotivation(ArrayList<PPMprojects> projets, String Motivation){
        ArrayList<PPMprojects> projetToReturn = new ArrayList<>();
        projetToReturn.addAll(projets);

        for (int i=0; i < projets.size() ; i++){
            PPMprojects projet = projets.get(i);
            if (!projet.getMotivationOfPproject().equalsIgnoreCase(Motivation)){
                projetToReturn.remove(projet);
            }
        }
        return projetToReturn;
    }



    private ArrayList<PPMprojects> sortByType(ArrayList<PPMprojects> projets, String Motivation){
        ArrayList<PPMprojects> projetToReturn = new ArrayList<>();
        projetToReturn.addAll(projets);

        for (int i=0; i < projets.size() ; i++){
            PPMprojects projet = projets.get(i);
            if (!projet.getTypeOfproject().equalsIgnoreCase(Motivation)){
                projetToReturn.remove(projet);
            }
        }
        return projetToReturn;
    }
    private ArrayList<PPMprojects> getPPMprojects(ArrayList<String> ProjetSt){
        ArrayList<PPMprojects> zebi = new ArrayList<>();

        for (String st : ProjetSt){
            if (InstanceOfMain.containProjectbyName(st)){
                zebi.add(InstanceOfMain.getProjectsByName(st));
            }
        }
        return zebi;
    }
}
