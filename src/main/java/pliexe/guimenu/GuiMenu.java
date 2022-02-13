package pliexe.guimenu;

import de.leonhard.storage.Config;
import de.leonhard.storage.LightningBuilder;
import de.leonhard.storage.internal.settings.ConfigSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pliexe.guimenu.commands.GroupGuiMenu;
import pliexe.guimenu.guimanager.GuiInventoryManager;

public final class GuiMenu extends JavaPlugin {

    public static final String pluginName = "GuiMenu";
    public static boolean legacy;

    public Config defaultConfig = LightningBuilder
            .fromPath("config", getDataFolder().getPath())
            .addInputStreamFromResource("config.yml")
            .setConfigSettings(ConfigSettings.PRESERVE_COMMENTS)
//            .setre
            .createConfig();


    public static boolean placeholderAPIActive = false;

    private final GuiInventoryManager guiInventoryManager = new GuiInventoryManager(this);

    public GuiInventoryManager getGuiInventoryManager() {
        return guiInventoryManager;
    }



//    public Config defaultConfig = ConfigManager.getConfig("config.yml", this, "config.yml");

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null)
            placeholderAPIActive = true;

        legacy = Integer.parseInt(getServer().getVersion().substring(getServer().getVersion().indexOf('.'), getServer().getVersion().length() - 1).replace(".", "")) < 130;

        // Event
        getServer().getPluginManager().registerEvents(new Events(this), this);
        // Commands
        getCommand("guimenu").setExecutor(new GroupGuiMenu(this));

        getLogger().info("[GuiMenu]: Plugin is enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
