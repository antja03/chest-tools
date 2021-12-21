package me.antja03.chesttools;

import me.antja03.chesttools.command.OpenMenuCommand;
import me.antja03.chesttools.listener.ChestListener;
import me.antja03.lwm.LightweightMenuHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChestTools extends JavaPlugin {

    private static ChestTools instance;
    private LightweightMenuHandler lwmHandler;

    @Override
    public void onEnable() {
        ChestTools.instance = this;
        this.lwmHandler = new LightweightMenuHandler();
        lwmHandler.registerListener(this);
        getServer().getPluginCommand("menu").setExecutor(new OpenMenuCommand());
        getServer().getPluginManager().registerEvents(new ChestListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static ChestTools getInstance() {
        return instance;
    }

    public LightweightMenuHandler getLwmHandler() {
        return this.lwmHandler;
    }

}
