package fr.leod1.Gambling.Main;


import fr.leod1.Gambling.Main.Gui.GuiEvent;
import fr.leod1.Gambling.Main.Request.RequestManager;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private RequestManager requestManager = new RequestManager();
    public RequestManager getRequestManager() {
        return requestManager;
    }
    public void setRequestManager(RequestManager requestManager) {
        this.requestManager = requestManager;
    }
    public static Economy econ = null;

    @Override
    public void onEnable() {
        CommandExecutor exempleExecutor = new command();
        getServer().getPluginManager().registerEvents(new GuiEvent(), this);
        getCommand("Coinflip").setExecutor(exempleExecutor);
        this.setupEconomy();
    }


    public void setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getPluginManager().disablePlugin(this);
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            Bukkit.getPluginManager().disablePlugin(this);
        }
        econ = rsp.getProvider();

    }

    public static Economy getEconomy()
    {
        return econ;
    }
    @Override
    public void onDisable() {

    }
}
