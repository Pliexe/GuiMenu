package pliexe.guimenu.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.commandhandler.GroupCommand;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commands.guimenu.*;

public class GroupGuiMenu extends GroupCommand implements CommandExecutor {

    private final GuiMenu plugin;

    public GroupGuiMenu(GuiMenu plugin) {
        super("guimenu", "yes");
        this.plugin = plugin;
        loadCommands();
    }

    @Override
    protected void loadCommands() {
        LoadCommand(new Show(plugin));
        LoadCommand(new List(plugin));
        LoadCommand(new New(plugin));
        LoadCommand(new Save(plugin));
        LoadCommand(new Reload(plugin));
        LoadCommand(new Create(plugin));
        LoadCommand(new Edit(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Run(sender, command, label, args, 0);
        return true;
    }
}
