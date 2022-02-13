package pliexe.guimenu.guimanager;

import de.leonhard.storage.sections.FlatFileSection;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;
import pliexe.guimenu.GuiMenu;

import java.util.HashMap;

public class InventoryGUI {
    public String displayName;
    public String strippedName;
    public int rows;
    public HashMap<Integer, Item> items = new HashMap<>();
    private final FlatFileSection guiConfig;

    public void editRows(int newRows) {
        if (newRows == rows) return;

        int limit = rows * 9 - 1;

        for(int key : items.keySet()) {
            if(key > limit) items.remove(key);
        }

        guiConfig.set("rows", newRows);
        this.rows = newRows;
    }

    public void changeDisplayName(String newDisplayName) {
        if (newDisplayName.equals(displayName)) return;
        guiConfig.set("displayName", newDisplayName);
        this.displayName = newDisplayName;
        this.strippedName = ChatColor.stripColor(newDisplayName);
    }

    public InventoryGUI(String displayName, int rows, GuiMenu plugin, String path) {
        this.displayName = displayName;
        this.rows = rows;
        this.guiConfig = plugin.defaultConfig.getSection(path);
        this.strippedName = ChatColor.stripColor(displayName);
        int maxElements = rows * 9;

        try {
            plugin.defaultConfig.singleLayerKeySet(path).forEach(it -> {
                int itemPosition;
                try {
                    itemPosition = Integer.parseInt(it);
                } catch (NumberFormatException e) {
                    plugin.getLogger().severe("Item index is invalid at " + path + "." + it + "! It may only be an number!");
                    return;
                }

                if (itemPosition + 1 > maxElements) return;

                FlatFileSection itemSection = plugin.defaultConfig.getSection(path + "." + it);

                String materialName;

                try {
                    materialName = itemSection.getString("material");
                } catch (ClassCastException e) {
                    plugin.getLogger().severe("Material name is of invalid data type. It may only be an item name (string/text). At " + itemSection.getPathPrefix());
                    return;
                }

                Material mat = Material.getMaterial(materialName);
                if (mat == null) {
                    plugin.getLogger().severe("Invalid material (" + materialName + ") at " + itemSection.getPathPrefix() + ".material");
                    return;
                }

                if (GuiMenu.legacy) {
                    items.put(itemPosition, new Item(mat, itemSection, itemSection.getOrDefault("data", (byte) 0), plugin));
                } else items.put(itemPosition, new Item(mat, itemSection, plugin));
            });
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public @Nullable Item getItem(int index) {
        return items.get(index);
    }
}
