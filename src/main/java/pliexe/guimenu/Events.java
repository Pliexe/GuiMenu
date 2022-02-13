package pliexe.guimenu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import pliexe.guimenu.guimanager.InventoryGUI;
import pliexe.guimenu.guimanager.Item;

public class Events implements Listener {

    private final GuiMenu plugin;
    public Events(GuiMenu plugin) { this.plugin = plugin; }

    @EventHandler(ignoreCancelled = true)
    public void onItemDrag(InventoryClickEvent event) {
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
            if(event.getClickedInventory() != null) {
                if(this.plugin.getGuiInventoryManager().HasGuiDisplayName(event.getInventory().getTitle())) {
                    event.setCancelled(true);

                    if(event.getClickedInventory().getType().equals(InventoryType.CHEST)) {
                        InventoryGUI gui = this.plugin.getGuiInventoryManager().getInventoryDisplayName(event.getInventory().getTitle());

                        assert gui != null;
                        Item item = gui.getItem(event.getSlot());

                        if(item != null)
                            item.runTask(event.getWhoClicked());
                    }
                }
            }
        }
    }

    public void onInvClose(InventoryCloseEvent event) {
        if(event.getInventory().getType().equals(InventoryType.CHEST)) {
//            event.getPlayer().getUniqueId()

//            event.getInventory().getSize()
        }
    }
}
