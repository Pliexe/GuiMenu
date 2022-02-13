package pliexe.guimenu.pluginmenu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.guimanager.InventoryGUI;
import pliexe.guimenu.guimanager.Item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MenuManager implements Listener {

    private final GuiMenu plugin;

    public MenuManager(GuiMenu plugin) {
        this.plugin = plugin;
    }

    private HashMap<UUID, String> invEditQueue = new HashMap<>();

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
            if(invEditQueue.containsKey(event.getPlayer().getUniqueId())) {
                invEditQueue.remove(event.getPlayer().getUniqueId());

                String name = invEditQueue.get(event.getPlayer().getUniqueId());

                InventoryGUI gui = plugin.getGuiInventoryManager().getInventory(name);

                gui.items.clear();

                for(int i = 0; i < event.getInventory().getSize(); i++)
                {
                    ItemStack item = event.getInventory().getItem(i);
                    if(item != null) gui.items.put(i, new Item(item.getType(), plugin.defaultConfig.getSection("menus."+)));

                }
            }
        }
    }
}
