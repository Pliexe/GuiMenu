package pliexe.guimenu.commands.guimenu.edit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommandExtended;

public class Rows extends SubCommandExtended {
    private final GuiMenu plugin;

    public Rows(GuiMenu plugin) {
        super("rows", "Edit the amount of rows the GUI has!", "<rows>");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args, String data) {
        if(args.length > 0) {
            int newRows;
            try {
                newRows = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                BadUsage(sender, "<rows>", "Row count may only be a whole number!");
                return;
            }

            plugin.getGuiInventoryManager().getInventory(data).editRows(newRows);

            sender.sendMessage("Updated rows for \"" + data + "\" to \"" + newRows + "\" rows!");
        } else BadUsage(sender, "<rows>");
    }
}
