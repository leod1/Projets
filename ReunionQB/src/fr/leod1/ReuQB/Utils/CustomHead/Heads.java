package fr.leod1.ReuQB.Utils.CustomHead;

import org.bukkit.inventory.ItemStack;

public enum Heads {
    YOUTUBE("ZDJmNmMwN2EzMjZkZWY5ODRlNzJmNzcyZWQ2NDU0NDlmNWVjOTZjNmNhMjU2NDk5YjVkMmI4NGE4ZGNlIn19fQ==","youtube"),
    CAT("Njk3NDljYTk1MjM4YjVhZjkzOTA3ZjkyNGYxZDU5MTVkZjc3MzFlZTgzYzE1YWM3NzZjYTgzMmM0M2U0MDYyIn19fQ==","cat"),
    WHITEB("NDJiOWUxNmUyNjIwNmE3MDliZjA3YzI0OTNjYTRjNWQyNGY1Njc1NjU0ZmMxMzBkMWQ1ZWM1ZThjNWJlNSJ9fX0=","white b"),
    STELERIO("","stelerio"),
    QB("","qb"),
    WHITEE("MWFlZWY4OGUyYzkyOGI0NjZjNmVkNWRlYWE0ZTE5NzVhOTQzNmMyYjFiNDk4ZjlmN2NiZjkyYTliNTk5YTYifX19","white e"),
    STARTREU("OTMxYjZjMzVhOGFjODM2NmE0ZGI1MWIwNDJhODcyOGRkMmU4ZTVmMmZlNDRhZmYwOGZjZjVhZjNiOTIxYzZkZCJ9fX0=","StartReu"),
    MEMBRE("MWQzY2ViNGU1MmVjZDgyYTJmZGU2NzdhMmNjNmUxNDY3OTU1MTlkN2E1Mzg4OGM4MzUyMTgyZTQ1OWQ1NTEyMSJ9fX0=","Menbre"),
    GROUPE("ZDliZWZmODY5YWYzNWQ1ZDU1NTgwMjVmYzkxMDRlYTE1ZjA0ZTNjMTgxOTBhMThmN2NiZTk0NjMxNTdiZGM2NCJ9fX0=","Groupe");

    private ItemStack item;
    private String idTag;
    private String prefix = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv";

    private Heads(String texture, String id)
    {
        item = HeadManager.createSkull(prefix + texture, id);
        idTag = id;
    }


    public ItemStack getItemStack()
    {
        return item;
    }

    public String getName()
    {
        return idTag;
    }

}
