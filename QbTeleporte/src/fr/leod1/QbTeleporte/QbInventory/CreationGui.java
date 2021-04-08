package fr.leod1.QbTeleporte.QbInventory;

import fr.leod1.QbTeleporte.QbInventory.CustomHead.HeadManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class CreationGui {
    private CreationGuiSelection guiSelection = new CreationGuiSelection();
    private ListCategoriesGui listgui = new ListCategoriesGui();
    private ProjetOpen oprnproject = new ProjetOpen();
    private HeadManager head = new HeadManager();

    public void CreateProject(Player pl){

        ItemStack SelectBlockView = new ItemStack(Material.GRASS);
        ItemMeta metaSelectBlockView = SelectBlockView.getItemMeta();
        metaSelectBlockView.setDisplayName("§cDéfinir le block à afficher");
        metaSelectBlockView.setLore(Arrays.asList("§7Permet de définir le block affiché.", "§7Lors du click, le block dans la main", "§7est pris en compte"));
        SelectBlockView.setItemMeta(metaSelectBlockView);

        ItemStack Name = new ItemStack(Material.FEATHER);
        ItemMeta metaName = Name.getItemMeta();
        metaName.setDisplayName("§cDéfinir le nom");
        metaName.setLore(Arrays.asList("§7Permet de définir le nom du project.", "§7Lors du click, il suffit d'écrire le nom", "§7dans le chat"));
        Name.setItemMeta(metaName);

        ItemStack Motivation = new ItemStack(Material.RECORD_12);
        ItemMeta metaMotivation = Motivation.getItemMeta();
        metaMotivation.setDisplayName("§cDéfinir la motivation");
        metaMotivation.setLore(Arrays.asList("§7Permet de définir la motivation du projet.", "§7Lors du click, il suffit de selectionné ","§7la motivation du projet", "§7(Argent, pa$$ion)"));
        Motivation.setItemMeta(metaMotivation);

        ItemStack Categorie = new ItemStack(Material.PAPER);
        ItemMeta metaCategorie = Categorie.getItemMeta();
        metaCategorie.setDisplayName("§cDéfinir la catégorie");
        metaCategorie.setLore(Arrays.asList("§7Permet de définir la catégorie.", "§7Lors du click, il suffit de selectionner", "§7la catégorie"));
        Categorie.setItemMeta(metaCategorie);

        ItemStack Validation = new ItemStack(Material.CONCRETE_POWDER);
        ItemMeta metaValidation = Validation.getItemMeta();
        Validation.setDurability((short) 5);
        metaValidation.setDisplayName("§cValidation");
        Validation.setItemMeta(metaValidation);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cQuitter");
        Leave.setItemMeta(metaLeave);


        Inventory inv = Bukkit.createInventory(null, 54,"§8Qb §7Creator");



        for (int i = 0; i < 54; i++){
            inv.setItem(i,fast(Material.STAINED_GLASS_PANE));
        }


        inv.setItem(4,SelectBlockView);
        inv.setItem(11,Name);
        inv.setItem(22,Categorie);
        inv.setItem(29,fast(7));
        inv.setItem(30,fast(15));
        inv.setItem(31,fast(15));
        inv.setItem(32,fast(15));
        inv.setItem(33,fast(7));
        inv.setItem(38,fast(7));
        inv.setItem(39,fast(7));

        //inv.setItem(28);
        inv.setItem(41,fast(7));
        inv.setItem(42,fast(7));
        inv.setItem(47,fast(7));
        inv.setItem(48,fast(15));
        inv.setItem(49,fast(15));
        inv.setItem(50,fast(15));
        inv.setItem(51,fast(7));
        inv.setItem(15,Motivation);
        inv.setItem(45,Validation);
        inv.setItem(53,Leave);

        pl.openInventory(inv);
    }


    private ItemStack fast(int nb){
        ItemStack zebi = new ItemStack(Material.STAINED_GLASS_PANE);
        zebi.setDurability((short) nb);
        return zebi;
    }

    private ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }

    public CreationGuiSelection getGuiSelection() {
        return guiSelection;
    }

    public void setGuiSelection(CreationGuiSelection guiSelection) {
        this.guiSelection = guiSelection;
    }

    public ListCategoriesGui getListgui() {
        return listgui;
    }

    public void setListgui(ListCategoriesGui listgui) {
        this.listgui = listgui;
    }

    public ProjetOpen getOprnproject() {
        return oprnproject;
    }

    public void setOprnproject(ProjetOpen oprnproject) {
        this.oprnproject = oprnproject;
    }
}
