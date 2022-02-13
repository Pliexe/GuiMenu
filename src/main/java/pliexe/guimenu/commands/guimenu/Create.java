package pliexe.guimenu.commands.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

import java.util.Arrays;

public class Create extends SubCommand {
    private final GuiMenu plugin;

    public Create(GuiMenu plugin) {
        super("create", "Create an menu", "<name> <rows> [display name]");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) {
            String menuName = args[0];

            if(plugin.getGuiInventoryManager().HasGui(menuName))
            {
                BadUsage(sender, "<name>", "Menu with this name already exists!");
                return;
            }

            if(args.length < 2)
            {
                BadUsage(sender, "<rows>");
                return;
            }

            int rows;

            try {
                rows = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                BadUsage(sender, "<rows>", "The row count must be an whole number!");
                return;
            }

            if(rows < 1)
            {
                BadUsage(sender, "<rows>", "Row count may not be lower than 1");
                return;
            }

            if(args.length > 2)
            {
                plugin.getGuiInventoryManager().addNewGui(menuName, rows, String.join(" ", Arrays.copyOfRange(args, 2, args.length)));
            } else plugin.getGuiInventoryManager().addNewGui(menuName, rows);

            sender.sendMessage("Created new GUI named " + menuName + "!");
        } else BadUsage(sender);
    }
}
