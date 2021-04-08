package fr.leod1.ppmPermissions.inventory.groups;

import fr.leod1.ppmPermissions.PlayerData.PlayerData;
import fr.leod1.ppmPermissions.ServerRank.Rank;
import fr.leod1.ppmPermissions.inventory.ColorHeads;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

import static fr.leod1.ppmPermissions.PPMPermissions.plugin;

public class invListPlayer {
    public void listPlayer(Player pl){
        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Perms §8List players");
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

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        int i = 9;
        for (Player ple : Bukkit.getOnlinePlayers()){
            SkullMeta skullm = (SkullMeta) skull.getItemMeta();
            skullm.setOwner(ple.getName());
            skullm.setDisplayName("§6" +ple.getName());
            skull.setItemMeta(skullm);
            inv.setItem(i,skull);
            i++;
        }

        pl.openInventory(inv);

    }

    public void listPlayerTarget(Player pl, Player target) {

        PlayerData TargetData = plugin.playerData.get(target);

        ItemStack error = new ItemStack(Material.BARRIER);
        ItemMeta metaerror = error.getItemMeta();
        metaerror.setDisplayName("§cErreur");
        metaerror.setLore(Arrays.asList("§4Merci de lui définir un role","§4et de définir un role par defaut","§4puis de redémarrer le serveur."));
        error.setItemMeta(metaerror);

        ItemStack Perm = new ItemStack(Material.TOTEM);
        ItemMeta metaPerm = Perm.getItemMeta();
        metaPerm.setDisplayName("§cPermisions personnaliser");
        metaPerm.setLore(Arrays.asList(""));
        Perm.setItemMeta(metaPerm);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        ItemStack skull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) skull.getItemMeta();
        skullm.setOwner(target.getName());
        skullm.setDisplayName("§6" +target.getName());
        skull.setItemMeta(skullm);


        Inventory inv = Bukkit.createInventory(null, 27, "§cPPM Perms §7players " + target.getName());

        for(int i = 0; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 10; i <= 16; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        inv.setItem(4,skull);
        if (TargetData.getServerRank() == null){
            inv.setItem(11,error);
        }
        inv.setItem(11,a(plugin.getRankByName(TargetData.getServerRank()).getColor(), TargetData.getServerRank()));
        inv.setItem(15,Perm);
        inv.setItem(22,Leave);

        pl.openInventory(inv);
    }

    public void listGroupForSetAtTarget(Player pl, Player Target){
        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 54,"§cPPM Perms §7List rank for " + Target.getName());
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

        for(int i = 9; i < plugin.ServerRank.size() + 9; i++){
            Rank rank = plugin.ServerRank.get(i-9);
            inv.setItem(i, a(rank.getColor(), rank.getName()));
        }
        pl.openInventory(inv);
    }


    public void listGroupForSetPermAtTarget(Player pl, Player Target) {
        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cRetour");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 54, "§cPPM Perms §7List perm for " + Target.getName());

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

        for(int i = 9; i < plugin.playerData.get(Target).getPrivatePermissions().size() + 9; i++){
            inv.setItem(i, a(new ItemStack(Material.TOTEM), plugin.playerData.get(Target).getPrivatePermissions().get(i-9)));
        }

        pl.openInventory(inv);
    }

    private ItemStack a(ItemStack item, String name){
        ItemMeta zebi = item.getItemMeta();
        zebi.setDisplayName(name);
        item.setItemMeta(zebi);
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
    public ItemStack a(ColorHeads Logo, String name){
        ItemStack item = ColorHeads.createSkull(Logo.getUrl());
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }
}
