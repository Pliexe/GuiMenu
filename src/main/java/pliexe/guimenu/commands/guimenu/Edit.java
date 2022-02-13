package pliexe.guimenu.commands.guimenu;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.commandhandler.GroupCommandExtended;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commands.guimenu.edit.Items;
import pliexe.guimenu.commands.guimenu.edit.Rows;

public class Edit extends GroupCommandExtended {

    private final GuiMenu plugin;

    public Edit(GuiMenu plugin) {
        super("edit", "Edit an GUI", "<menu name>");
        this.plugin = plugin;
        loadCommands();
    }

    @Override
    protected void loadCommands() {
        LoadCommand(new Rows(plugin));
        LoadCommand(new Items(plugin));
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args, int subLevel) {
        if(args.length > 1) {

            if(plugin.getGuiInventoryManager().HasGui(args[1]))
            {
                super.Run(sender, command, label, args, subLevel + 1, args[1]);
            } else BadUsage(sender, "<menu name>", ChatColor.RED + "" + ChatColor.BOLD + "Menu with the name \"" + ChatColor.BOLD + args[1] + ChatColor.RED + "\" was not found!");
        } else BadUsage(sender, "<menu name>");
    }
}
