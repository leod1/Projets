package fr.leod1.ppmPermissions.inventory.groups;

import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.inventory.ColorHeads;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class invListGroup {

    public void listGroup(Player pl){
        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Perms §8List rank");
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        for(int i = 45; i <= 53; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        inv.setItem(0, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(3, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(5, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(8, fast(Material.STAINED_GLASS_PANE, 1));

        inv.setItem(50, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(48, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(45, fast(Material.STAINED_GLASS_PANE, 1));

        inv.setItem(49, Leave);

        inv.setItem(53, fast(Material.ARROW));
        inv.setItem(52, fast(Material.ARROW));
        pl.openInventory(inv);


        for(int i = 9; i < plugin.ServerRank.size() + 9; i++){
            Rank rank = plugin.ServerRank.get(i-9);
            inv.setItem(i, a(rank.getColor(), rank.getName()));
        }

    }

    public void groupModif(Player pl, Rank rank) {
        Inventory inv = Bukkit.createInventory(null, 27, "§cPPM Perms §7Modif " +rank.getName());
        ItemStack defaults;
        for(int i = 0; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 10; i <= 16; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }

        ItemStack featherPrefix = new ItemStack(Material.FEATHER);
        ItemMeta metafeatherPrefix = featherPrefix.getItemMeta();
        metafeatherPrefix.setDisplayName("§cPrefix");
        metafeatherPrefix.setLore(Arrays.asList("§r"+ rank.getPrefix()));
        featherPrefix.setItemMeta(metafeatherPrefix);

        ItemStack featherSufix = new ItemStack(Material.FEATHER);
        ItemMeta metafeatherSufix = featherSufix.getItemMeta();
        metafeatherSufix.setDisplayName("§cSuffix");
        metafeatherSufix.setLore(Arrays.asList("§r"+ rank.getSufix()));
        featherSufix.setItemMeta(metafeatherSufix);

        if (!rank.isRankDefault()){
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

        ItemStack Color = ColorHeads.createSkull(ColorHeads.MULTICOLOR.getUrl());
        ItemMeta metaColor = Color.getItemMeta();
        metaColor.setDisplayName("§cColor");
        metaColor.setLore(Arrays.asList("§r"+ rank.getColor()));
        Color.setItemMeta(metaColor);

        ItemStack Permission = new ItemStack(Material.TOTEM);
        ItemMeta metaPermission = Permission.getItemMeta();
        metaPermission.setDisplayName("§cPermissions");
        metaPermission.setLore(Arrays.asList("§7[Click Droit] Ajouter une permission ","§7[Click Gauche] Lister et supprimer des permissions"));
        Permission.setItemMeta(metaPermission);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        inv.setItem(11,featherPrefix);
        inv.setItem(12,featherSufix);
        inv.setItem(14,defaults);
        inv.setItem(15,Color);
        inv.setItem(16,Permission);
        inv.setItem(22,Leave);

        pl.openInventory(inv);

    }

    public void groupModifColor(Player pl, Rank rank) {
        Inventory inv = Bukkit.createInventory(null, 18,"§cPPM Perms §8Modif Color " +rank.getName());
        inv.setItem(0,a(ColorHeads.createSkull(ColorHeads.BLACK.getUrl()),"Noir"));
        inv.setItem(1,a(ColorHeads.createSkull(ColorHeads.DARK_BLUE.getUrl()),"Bleu foncé"));
        inv.setItem(2,a(ColorHeads.createSkull(ColorHeads.DARK_GREEN.getUrl()),"Vert foncé"));
        inv.setItem(3,a(ColorHeads.createSkull(ColorHeads.DARK_AQUA.getUrl()),"Cyan foncé"));
        inv.setItem(4,a(ColorHeads.createSkull(ColorHeads.DARK_RED.getUrl()),"Rouge foncé"));
        inv.setItem(5,a(ColorHeads.createSkull(ColorHeads.DARK_PURPLE.getUrl()),"Violet foncé"));
        inv.setItem(6,a(ColorHeads.createSkull(ColorHeads.GOLD.getUrl()),"Or"));
        inv.setItem(7,a(ColorHeads.createSkull(ColorHeads.GRAY.getUrl()),"Gris"));
        inv.setItem(8,a(ColorHeads.createSkull(ColorHeads.DARK_GRAY.getUrl()),"Gris foncé"));
        inv.setItem(9,a(ColorHeads.createSkull(ColorHeads.BLUE.getUrl()),"Bleu"));
        inv.setItem(10,a(ColorHeads.createSkull(ColorHeads.GREEN.getUrl()),"Vert"));
        inv.setItem(11,a(ColorHeads.createSkull(ColorHeads.AQUA.getUrl()),"Cyan"));
        inv.setItem(12,a(ColorHeads.createSkull(ColorHeads.LIGHT_PURPLE.getUrl()),"Violet"));
        inv.setItem(13,a(ColorHeads.createSkull(ColorHeads.RED.getUrl()),"Rouge"));
        inv.setItem(14,a(ColorHeads.createSkull(ColorHeads.YELLOW.getUrl()),"Jaune"));
        inv.setItem(15,a(ColorHeads.createSkull(ColorHeads.WHITE.getUrl()),"Blanc"));
        pl.openInventory(inv);
    }

    public void groupModifPerm(Player pl, Rank rank, int page) {

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Perms §8Modif Perm " + rank.getName());
        for(int i = 0; i <= 8; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        for(int i = 45; i <= 53; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        inv.setItem(0, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(3, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(5, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(8, fast(Material.STAINED_GLASS_PANE, 1));

        inv.setItem(50, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(48, fast(Material.STAINED_GLASS_PANE, 1));
        inv.setItem(45, fast(Material.STAINED_GLASS_PANE, 1));

        inv.setItem(49, Leave);





        float PageThrori = rank.getPermissionsRank().size() / 36;
        int MaxPage = (int) Math.floor(PageThrori);

        if (page > MaxPage){
            plugin.playerDataCache.get(pl).setPagePermRank(0);
            page = 0;
        }

        inv.setItem(52, a(new ItemStack(Material.ARROW),"§cPage Précédente (" + page + "/" +MaxPage+")"));
        inv.setItem(53, a(new ItemStack(Material.ARROW),"§cPage Suivante (" + page + "/" +MaxPage+")"  ));
        pl.openInventory(inv);

        if (page == 0){
            if (MaxPage == 0){

                for (int i = 0; i < rank.getPermissionsRank().size(); i++){

                    inv.setItem(i+9, a(new ItemStack(Material.TOTEM), rank.getPermissionsRank().get(i)));

                }

            } else {
                for (int i = 0; i <= 36; i++){

                    inv.setItem(i+9 , a(new ItemStack(Material.TOTEM), rank.getPermissionsRank().get(i)));

                }
            }
        } else if (MaxPage > 0 && MaxPage > page){
            for (int i = 0; i <= 36; i++){
                inv.setItem(i + 9, a(new ItemStack(Material.TOTEM), rank.getPermissionsRank().get(i + (36 * page))));
            }
        } else if (MaxPage > 0 && MaxPage == page){

            for (int i = 0; i < rank.getPermissionsRank().size() - (page * 36); i++){
                inv.setItem(i, a(new ItemStack(Material.TOTEM), rank.getPermissionsRank().get(i + (44 * page))));
            }

        }
    }

    private ItemStack a(ItemStack item, String name){
        ItemMeta zebi = item.getItemMeta();
        zebi.setDisplayName(name);
        item.setItemMeta(zebi);
        return item;
    }

    public ItemStack a(ColorHeads Logo, String name){
        ItemStack item = ColorHeads.createSkull(Logo.getUrl());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
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
