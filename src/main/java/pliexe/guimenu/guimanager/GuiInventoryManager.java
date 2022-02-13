package pliexe.guimenu.guimanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;
import pliexe.guimenu.GuiMenu;

import java.util.*;

public class GuiInventoryManager {
    private final HashMap<String, InventoryGUI> inventories = new HashMap<>();
    private final GuiMenu plugin;

    public GuiInventoryManager(GuiMenu plugin)
    {
        this.plugin = plugin;
        setup();
    }

    private void setup() {
        try {
            Set<String> menus = plugin.defaultConfig.singleLayerKeySet("menus");
            if(menus.isEmpty()) {
                plugin.getLogger().info("No menus are setup in config.yml. You may set them up using /guimenu create or manually set them!");
            } else {
                menus.forEach(menu -> {
                    String path = "menus." + menu;
                    String displayName = plugin.defaultConfig.getString(path + ".displayName");
                    if(displayName == null) displayName = "DISPLAY-NAME UNSET";
                    int rows = plugin.defaultConfig.getInt(path + ".rows");
                    if(rows < 1) rows = 1;

                    inventories.put(menu, new InventoryGUI(ChatColor.translateAlternateColorCodes('&', displayName), rows, plugin, "menus." + menu + ".items"));
                });
            }
        } catch (ClassCastException e) {
            plugin.getLogger().severe("Menu is of invalid type...");
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public void addNewGui(String name, int rows, String displayName) {
        plugin.defaultConfig.set("menus." + name + ".rows", rows);
        plugin.defaultConfig.set("menus." + name + ".displayName", displayName);
        inventories.put(name, new InventoryGUI(displayName, rows, plugin, "menus." + name + ".items"));
    }

    public void addNewGui(String name, int rows) {
        String displayName = "Unnamed GUI";
        addNewGui(name, rows, displayName);
    }

    public void reload() {
        inventories.clear();
        setup();
    }


    public Set<String> getGuiNames() {
        return inventories.keySet();
    }

    public InventoryGUI getInventory(String name) {
        return inventories.get(name);
    }

    public @Nullable InventoryGUI getInventoryDisplayName(String name) {
        return inventories.values().stream().filter(it -> it.displayName.equals(name)).findFirst().orElse(null);
    }

    public boolean HasGui(String name) {
        return inventories.containsKey(name);
    }

    public boolean HasGuiDisplayName(String name) {
        String strippedName = ChatColor.stripColor(name);
        return inventories.values().stream().anyMatch( it -> it.strippedName.equals(strippedName));
    }

    public void openEditable(String name, Player player) {
        InventoryGUI invGui = inventories.get(name);
        if(invGui == null) return;

        Inventory inv = Bukkit.createInventory(null, invGui.rows * 9, ChatColor.AQUA + "" + ChatColor.BOLD + "GUI Items Editor!");

        for(Map.Entry<Integer, Item> entry : invGui.items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue().resolveItem());
        }

        player.openInventory(inv);
    }

    public boolean openGui(String name, Player player) {
        InventoryGUI invGui = inventories.get(name);
        if(invGui == null) return false;

        Inventory inv = Bukkit.createInventory(null, invGui.rows * 9, ChatColor.translateAlternateColorCodes('&', invGui.displayName));

        for(Map.Entry<Integer, Item> entry : invGui.items.entrySet()) {
            inv.setItem(entry.getKey(), entry.getValue().resolveItem());
        }

        player.openInventory(inv);

        return true;
    }
}

