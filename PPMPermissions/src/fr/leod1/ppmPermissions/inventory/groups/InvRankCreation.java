package fr.leod1.ppmPermissions.inventory.groups;

import fr.leod1.ppmPermissions.inventory.ColorHeads;
import fr.leod1.ppmPermissions.inventory.InventoryMain;
import fr.leod1.ppmPermissions.playerCaches.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class InvRankCreation {

    public void creationRank(Player pl){
        ItemStack featherName;
        ItemStack featherPrefix;
        ItemStack featherSufix;
        ItemStack defaults;
        ItemStack Color;

        PlayerCache plCache = plugin.playerDataCache.get(pl);
        //Validation
        ItemStack Validation = new ItemStack(Material.WOOL);
        Validation.setDurability((short) 5);
        ItemMeta metaValidation = Validation.getItemMeta();
        metaValidation.setDisplayName("§aValidation");
        metaValidation.setLore(Arrays.asList(""));
        Validation.setItemMeta(metaValidation);
        //Validation

        //Retour
        ItemStack Retour = new ItemStack(Material.BARRIER);
        ItemMeta metaRetour = Retour.getItemMeta();
        metaRetour.setDisplayName("§cRetour");
        metaRetour.setLore(Arrays.asList(""));
        Retour.setItemMeta(metaRetour);
        //Retour


        //Name
        if (plCache.getRank().getName() == null){

            featherName = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherName = featherName.getItemMeta();
            metafeatherName.setDisplayName("§cNom §7[§4✖§7]");
            metafeatherName.setLore(Arrays.asList(""));
            featherName.setItemMeta(metafeatherName);
        } else {
            featherName = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherName = featherName.getItemMeta();
            metafeatherName.setDisplayName("§cNom §7[§l§a✓§r§7]");
            metafeatherName.setLore(Arrays.asList("§r"+ plCache.getRank().getName()));
            featherName.setItemMeta(metafeatherName);
        }
        //Name

        //Suffix
        if (plCache.getRank().getSufix() == null){

            featherSufix = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherSufix = featherSufix.getItemMeta();
            metafeatherSufix.setDisplayName("§cSuffix §7[§4✖§7]");
            metafeatherSufix.setLore(Arrays.asList(""));
            featherSufix.setItemMeta(metafeatherSufix);
        } else {
            featherSufix = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherSufix = featherSufix.getItemMeta();
            metafeatherSufix.setDisplayName("§cSuffix §7[§l§a✓§r§7]");
            metafeatherSufix.setLore(Arrays.asList("§r"+ plCache.getRank().getSufix()));
            featherSufix.setItemMeta(metafeatherSufix);
        }
        //Suffix

        //Prefix
        if (plCache.getRank().getPrefix() == null){

            featherPrefix = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherPrefix = featherPrefix.getItemMeta();
            metafeatherPrefix.setDisplayName("§cPrefix §7[§4✖§7]");
            metafeatherPrefix.setLore(Arrays.asList(""));
            featherPrefix.setItemMeta(metafeatherPrefix);
        } else {
            featherPrefix = new ItemStack(Material.FEATHER);
            ItemMeta metafeatherPrefix = featherPrefix.getItemMeta();
            metafeatherPrefix.setDisplayName("§cPrefix §7[§l§a✓§r§7]");
            metafeatherPrefix.setLore(Arrays.asList("§r"+ plCache.getRank().getPrefix()));
            featherPrefix.setItemMeta(metafeatherPrefix);
        }
        //Prefix

        //Default
        if (!plCache.getRank().isRankDefault()){
            defaults = new ItemStack(Material.CONCRETE_POWDER);
            defaults.setDurability((short) 14);
            ItemMeta metadefaults = defaults.getItemMeta();
            metadefaults.setDisplayName("§cDefault : §7[§4✖§7]");
            defaults.setItemMeta(metadefaults);
        } else {
            defaults = new ItemStack(Material.CONCRETE_POWDER);
            defaults.setDurability((short) 5);
            ItemMeta metadefaults = defaults.getItemMeta();
            metadefaults.setDisplayName("§cDefault : §7[§l§a✓§r§7]");
            defaults.setItemMeta(metadefaults);
        }
        //Default

        //Color
        if (plCache.getRank().getColor() == null){
            Color = ColorHeads.createSkull(ColorHeads.MULTICOLOR.getUrl());
            ItemMeta metaColor = Color.getItemMeta();
            metaColor.setDisplayName("§cColor : §7[§4✖§7]");
            Color.setItemMeta(metaColor);
        } else {
            Color = ColorHeads.createSkull(ColorHeads.MULTICOLOR.getUrl());
            ItemMeta metaColor = Color.getItemMeta();
            metaColor.setDisplayName("§cColor : §7[§l§a✓§r§7]");
            metaColor.setLore(Arrays.asList("§r"+ plCache.getRank().getColor()));
            Color.setItemMeta(metaColor);
        }
        //Color


        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Perms §8Creation rank");

        for(int i = 0; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 10; i <= 16; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }

        inv.setItem(11, featherName);
        inv.setItem(12, featherPrefix);
        inv.setItem(13, featherSufix);
        inv.setItem(14, defaults);
        inv.setItem(15, Color);
        inv.setItem(22, Retour);
        inv.setItem(26, Validation);

        pl.openInventory(inv);
    }



    public ItemStack fast(Material mat, int nb){
        ItemStack zebi = new ItemStack(mat);
        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }
}
