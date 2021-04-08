package fr.leod1.Gambling.Main.Gui;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import fr.leod1.undercover.Gambling.Main.Gui.GuiItem.MiseC;

import java.util.ArrayList;
import java.util.List;

public class GuiMethods {
    public static void zed (Player P){
        Inventory help = Bukkit.getServer().createInventory(P, 9, "Help");

        ItemStack ref1 = new ItemStack(Material.BOOK);
        ItemMeta metaref1 = ref1.getItemMeta();
        ArrayList<String> lore= new ArrayList<String>();

        lore.add(" ");
        lore.add("§for visit our site");
        lore.add(" ");
        lore.add("§atest.net/help");

        metaref1.setLore(lore);
        metaref1.setDisplayName("§6§lClick to get help");


        ref1.setItemMeta(metaref1);
        help.setItem(5, ref1);

        P.openInventory(help);

        for (Player Pl : Bukkit.getOnlinePlayers()) {

        }

    }
    public static void GuiTeteServeur (Player P){
        int i = -1;
        Inventory SelectionTete = Bukkit.getServer().createInventory(P, 9*6, "§7Selectionne un joueur");
        for (Player Pl : Bukkit.getOnlinePlayers()) {
            i++;

            ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
            SkullMeta skullm = (SkullMeta) skull.getItemMeta();
            skullm.setOwner(Pl.getName());
            skullm.setDisplayName("§6" + Pl.getName());
            skull.setItemMeta(skullm);

            SelectionTete.setItem(i,skull);


        }
        P.openInventory(SelectionTete);
    }

    public static void ChoisirMode(Player P){
        List<String> Lore = new ArrayList<>();

        Inventory Mode = Bukkit.getServer().createInventory(P, 9*6, "§7Selectionne un mode de jeu");

        ItemStack Item = new ItemStack(Material.DOUBLE_PLANT);
        ItemMeta ItemM = Item.getItemMeta();
        ItemM.setDisplayName("§6 CoinFlip");
        Lore.add("sr");

        ItemM.setLore(Lore);
        Item.setItemMeta(ItemM);

        Mode.setItem(5, Item);

        P.openInventory(Mode);

    }

    public static void ChoisirSome(Player P, Integer Mise){
        List<String> Lore = new ArrayList<>();

        Inventory Some = Bukkit.getServer().createInventory(P, 9*5, "§7Selectionne une somme a parier");



        ItemStack ItemV100 = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta ItemV100M = ItemV100.getItemMeta();
        ItemV100M.setDisplayName("+100");
        Lore.add("sr");

        ItemV100M.setLore(Lore);
        ItemV100.setItemMeta(ItemV100M);





        ItemStack ItemW = new ItemStack(Material.WOOL);
        ItemMeta ItemMW = ItemW.getItemMeta();
        ItemMW.setDisplayName("§aValider§6 "+ Mise);


        ItemW.setItemMeta(ItemMW);






        ItemStack VitreBlack = MiseC.GlassPaneBlack();
        ItemStack VitreWhite = MiseC.GlassPaneWhite();

        ItemStack Vitre1 = MiseC.GlassPane_1();
        ItemStack Vitre10 = MiseC.GlassPane_10();
        ItemStack Vitre100 = MiseC.GlassPane_100();
        ItemStack Vitre1000 = MiseC.GlassPane_1000();
        ItemStack Vitre10000 = MiseC.GlassPane_10000();
        ItemStack Vitre100000 = MiseC.GlassPane_100000();
        ItemStack Vitre1000000 = MiseC.GlassPane_1000000();
        ItemStack Vitre10000000 = MiseC.GlassPane_10000000();
        ItemStack Vitre100000000 = MiseC.GlassPane_100000000();

        ItemStack VitreMoin1 = MiseC.GlassPaneMoin1();
        ItemStack VitreMoin10 = MiseC.GlassPaneMoin2();
        ItemStack VitreMoin100 = MiseC.GlassPaneMoin3();
        ItemStack VitreMoin1000 = MiseC.GlassPaneMoin4();
        ItemStack VitreMoin10000 = MiseC.GlassPaneMoin5();
        ItemStack VitreMoin100000 = MiseC.GlassPaneMoin6();
        ItemStack VitreMoin1000000 = MiseC.GlassPaneMoin7();
        ItemStack VitreMoin10000000 = MiseC.GlassPaneMoin8();
        ItemStack VitreMoin100000000 = MiseC.GlassPaneMoin9();


        Some.setItem(22, ItemW);

        int i = 0;
        while (i <= 9){
            Some.setItem(i, VitreBlack);
            i++;
        }
        Some.setItem(17, VitreBlack);
        Some.setItem(18, VitreBlack);
        Some.setItem(26, VitreBlack);
        Some.setItem(27, VitreBlack);
        Some.setItem(35, VitreBlack);
        int i2 = 35;
        while (i2 <= 44){
            Some.setItem(i2, VitreBlack);
            i2++;
        }
        Some.setItem(13, VitreWhite);
        Some.setItem(31, VitreWhite);
        Some.setItem(10, Vitre1);
        Some.setItem(11, Vitre10);
        Some.setItem(12, Vitre100);
        Some.setItem(19, Vitre1000);
        Some.setItem(20, Vitre10000);
        Some.setItem(21, Vitre100000);
        Some.setItem(28, Vitre1000000);
        Some.setItem(29, Vitre10000000);
        Some.setItem(30, Vitre100000000);

        Some.setItem(14, VitreMoin1);
        Some.setItem(15, VitreMoin10);
        Some.setItem(16, VitreMoin100);
        Some.setItem(23, VitreMoin1000);
        Some.setItem(24, VitreMoin10000);
        Some.setItem(25, VitreMoin100000);
        Some.setItem(32, VitreMoin1000000);
        Some.setItem(33, VitreMoin10000000);
        Some.setItem(34, VitreMoin100000000);



        P.openInventory(Some);

    }

}
