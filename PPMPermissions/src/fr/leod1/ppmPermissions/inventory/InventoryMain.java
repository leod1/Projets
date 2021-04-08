package fr.leod1.ppmPermissions.inventory;

import fr.leod1.ppmPermissions.inventory.groups.InvRankCreation;
import fr.leod1.ppmPermissions.inventory.groups.InvSelectionColor;
import fr.leod1.ppmPermissions.inventory.groups.invListGroup;
import fr.leod1.ppmPermissions.inventory.groups.invListPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryMain {
    private InvRankCreation invRank;
    private InvSelectionColor invSelectionColor;
    private invListGroup invListGroup;
    private invListPlayer invListPlayer;

    public InventoryMain() {
        this.invRank = new InvRankCreation();
        this.invSelectionColor = new InvSelectionColor();
        this.invListGroup = new invListGroup();
        this.invListPlayer= new invListPlayer();
    }

    public void MainInventory(Player pl){
        ItemStack PaperRank = new ItemStack(Material.PAPER);
        ItemMeta metaPaperRank= PaperRank.getItemMeta();
        metaPaperRank.setDisplayName("§cRanks");
        metaPaperRank.setLore(Arrays.asList("§7[Click Droit] Créer un rank ","§7[Click Gauche] Modifier un rank existant"));
        PaperRank.setItemMeta(metaPaperRank);

        ItemStack PaperPlayer = new ItemStack(Material.PAPER);
        ItemMeta metaPaperPlayer = PaperPlayer.getItemMeta();
        metaPaperPlayer.setDisplayName("§cPlayers");
        metaPaperPlayer.setLore(Arrays.asList("§7[Click Droit] Créer un rank ","§7[Click Gauche] Modifier un rank existant"));
        PaperPlayer.setItemMeta(metaPaperPlayer);

        ItemStack Leave = new ItemStack(Material.BARRIER);
        ItemMeta metaLeave = Leave.getItemMeta();
        metaLeave.setDisplayName("§cLeave");
        metaLeave.setLore(Arrays.asList(""));
        Leave.setItemMeta(metaLeave);

        Inventory inv = Bukkit.createInventory(null, 27,"§cPPM Perms");

        for(int i = 0; i <= 26; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 1));
        }
        for(int i = 10; i <= 16; i++){
            inv.setItem(i, fast(Material.STAINED_GLASS_PANE, 6));
        }
        inv.setItem(11,PaperPlayer);
        inv.setItem(15,PaperRank);
        inv.setItem(22,Leave);
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

    public InvRankCreation getInvRank() {
        return invRank;
    }

    public void setInvRank(InvRankCreation invRank) {
        this.invRank = invRank;
    }


    public InvSelectionColor getInvSelectionColor() {
        return invSelectionColor;
    }

    public fr.leod1.ppmPermissions.inventory.groups.invListGroup getInvListGroup() {
        return invListGroup;
    }

    public fr.leod1.ppmPermissions.inventory.groups.invListPlayer getInvListPlayer() {
        return invListPlayer;
    }

    public void setInvListPlayer(fr.leod1.ppmPermissions.inventory.groups.invListPlayer invListPlayer) {
        this.invListPlayer = invListPlayer;
    }

}
