package pliexe.guimenu.guimanager;

import de.leonhard.storage.sections.FlatFileSection;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pliexe.guimenu.GuiMenu;

import java.util.ArrayList;
import java.util.List;

public class Item {
    public Material material;
    public String displayName;
    public int amount = 1;
    public byte data = 0;
    private final FlatFileSection itemConfig;
    private final GuiMenu plugin;
    private boolean closeInv;

    public Item(Material material, FlatFileSection itemConfig, GuiMenu plugin) {
        this.material = material;
        this.itemConfig = itemConfig;
        this.plugin = plugin;
        setupItem();
    }

    public Item(Material material, FlatFileSection itemConfig, byte data, GuiMenu plugin) {
        this.material = material;
        this.itemConfig = itemConfig;
        this.data = data;
        this.plugin = plugin;
        setupItem();
    }

    private void setupItem() {
        try {
            String displayName = itemConfig.getString("displayName");
            if (displayName != null) this.displayName = displayName;
        } catch (ClassCastException e) {
            plugin.getLogger().severe("Item displayName is of invalid type! It must be of string (text). Path: " + itemConfig.getPathPrefix() + ".displayName");
            e.printStackTrace();
        }

        try {
            closeInv = itemConfig.getOrDefault("close", false);
        } catch (ClassCastException e) {
            plugin.getLogger().severe("close option may only be true or false or (yes/no). At " + itemConfig.getPathPrefix() + ".close");
            closeInv = false;
        }
    }

    public ItemStack resolveItem() {
        ItemStack item;

        if (GuiMenu.legacy) {
            item = new ItemStack(material, amount, data);
        } else {
            item = new ItemStack(material, amount);
        }

        if (displayName != null) item.getItemMeta().setDisplayName(displayName);

        return item;
    }

    public void runTask(HumanEntity entity) {
        Player player = (Player) entity;

        String menu = itemConfig.getString("menu");

        if (menu != null)
            plugin.getGuiInventoryManager().openGui(menu, player);

        Object userCommand = itemConfig.get("userCommand");
        if (userCommand instanceof String) {
            player.performCommand((String) userCommand);
        } else if (userCommand instanceof List) {
            for (String cmd : ((List<String>) userCommand)) {
                player.performCommand(cmd);
            }
        }

        Object serverCommand = itemConfig.get("serverCommand");
        if (serverCommand instanceof String) {
            if (GuiMenu.placeholderAPIActive)
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, (String) serverCommand));
            else
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), (String) serverCommand);
        } else if (serverCommand instanceof ArrayList) {
            if (GuiMenu.placeholderAPIActive) {
                for (String cmd : ((List<String>) serverCommand))
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), PlaceholderAPI.setPlaceholders(player, cmd));
            } else {
                for (String cmd : ((List<String>) serverCommand))
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }

        if (closeInv)
            entity.closeInventory();
    }
}
