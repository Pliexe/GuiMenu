package pliexe.guimenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class GuiInventoryManager {
    private final HashMap<String, InventoryGUI> inventories = new HashMap();

    public GuiInventoryManager()
    {
        String name = ChatColor.DARK_BLUE + "GUI TEST";
        inventories.put(ChatColor.stripColor(name), new InventoryGUI(name, 6));
    }

    public InventoryGUI getInventory(String name) {
        return inventories.get(name);
    }

    public boolean HasGui(String name) {
        return inventories.containsKey(name);
    }

    public boolean openGui(String name, Player player) {
        InventoryGUI invGui = inventories.get(name);
        if(invGui == null) return false;

        Inventory inv = Bukkit.createInventory(null, invGui.rows * 9, invGui.displayName);

        player.openInventory(inv);

        return true;
    }
}

class InventoryGUI {
    public String displayName;
    public int rows;

    public InventoryGUI(String displayName, int rows) {
        this.displayName = displayName;
        this.rows = rows;
    }
}
