package fr.leod1.QbTeleporte.QbInventory;

import fr.leod1.QbTeleporte.QbInventory.CustomHead.HeadManager;
import fr.leod1.QbTeleporte.QbProjects.QbProjects;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Arrays;

import static fr.leod1.QbTeleporte.QbInventory.CustomHead.HeadManager.createSkull;
import static fr.leod1.QbTeleporte.QbTeleporte.InstanceOfMain;

public class CreationGuiSelection {

    public HeadManager head = new HeadManager();

    public void SelectionCatégories(Player pl){
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

        Inventory inv = Bukkit.createInventory(null, 27,"§8Qb §7Creator Categories");
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


    public void SelectionMotivation(Player pl){

        ItemStack Commision = new ItemStack(Material.GOLD_INGOT);
        ItemMeta metaCommision = Commision.getItemMeta();
        metaCommision.setDisplayName("§cCommision");
        Commision.setItemMeta(metaCommision);

        ItemStack Projet = new ItemStack(Material.TIPPED_ARROW);

        PotionMeta metaProjet = (PotionMeta) Projet.getItemMeta();
        metaProjet.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
        //ItemMeta metaProjet = Projet.getItemMeta();

        metaProjet.setDisplayName("§cProjet");
        Projet.setItemMeta(metaProjet);

        ItemStack MarketP = new ItemStack(Material.DIAMOND);
        ItemMeta metaMarketP = MarketP.getItemMeta();
        metaMarketP.setDisplayName("§cMarket Place");
        MarketP.setItemMeta(metaMarketP);


        Inventory inv = Bukkit.createInventory(null, 27,"§8Qb §7Creator Motivation");




        for (int i = 0; i < 27; i++){
            inv.setItem(i,fast(Material.STAINED_GLASS_PANE));
        }

        inv.setItem(11, Commision);
        inv.setItem(13, Projet);
        inv.setItem(15,MarketP);

        pl.openInventory(inv);

    }
    public ItemStack fast(int nb){
        ItemStack zebi = new ItemStack(Material.STAINED_GLASS_PANE);
        ItemMeta erbimeta = zebi.getItemMeta();
        erbimeta.setDisplayName("§6");
        zebi.setItemMeta(erbimeta);

        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack fast(Material mat){
        ItemStack zebi = new ItemStack(mat);
        return zebi;
    }

    private ItemStack fast(Material mat, int nb){
        ItemStack zebi = new ItemStack(mat);
        zebi.setDurability((short) nb);
        return zebi;
    }

    public ItemStack head(String name, String nameItem){
        ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());
        SkullMeta skullm = (SkullMeta) item.getItemMeta();
        skullm.setOwner(name);
        skullm.setDisplayName(nameItem);
        item.setItemMeta(skullm);

        return item;
    }

    public Inventory pageRangement(ArrayList<QbProjects> Projects, int page, Player pl, Inventory inv){
        float PageThrori = Projects.size() / 32;
        int MaxPage = (int) Math.floor(PageThrori);

        if (page > MaxPage || page < 0){
            InstanceOfMain.playerCaches.get(pl).setPage(0);
            page = 0;
        }

        if (page == 0){
            if (MaxPage == 0){
                for (int i = 0; i < Projects.size(); i++){
                    QbProjects projet = Projects.get(i);

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
                return inv;

            } else {
                for (int i = 0; i <= 32; i++){
                    QbProjects projet = Projects.get(i);

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
                return inv;
            }
        } else if (MaxPage > 0 && MaxPage > page){
            for (int i = 0; i <= 32; i++){
                QbProjects projet = Projects.get(i + (32 * page));

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
            return inv;
        } else if (MaxPage > 0 && MaxPage == page){
            for (int i = 0; i < Projects.size() - (page * 32); i++){
                QbProjects projet = Projects.get(i + (32 * page));

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
            return inv;
        }
        return inv;
    }
}
