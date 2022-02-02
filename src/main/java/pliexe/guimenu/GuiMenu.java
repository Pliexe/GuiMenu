package pliexe.guimenu;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pliexe.guimenu.commands.GroupGuiMenu;

public final class GuiMenu extends JavaPlugin {

    public static final String pluginName = "GuiMenu";

    public static boolean placeholderAPIActive = false;

    private final GuiInventoryManager guiInventoryManager = new GuiInventoryManager();

    public GuiInventoryManager getGuiInventoryManager() {
        return guiInventoryManager;
    }

    @Override
    public void onEnable() {

        saveDefaultConfig();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            placeholderAPIActive = true;

        // Event
        getServer().getPluginManager().registerEvents(new Events(), this);
        // Commands
        getCommand("guimenu").setExecutor(new GroupGuiMenu(this));



        getLogger().info("[GuiMenu]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
