package pliexe.guimenu.commands.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.SubCommand;

public class List extends SubCommand {
    public List() {
        super("list", "List all menus");
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("WORKS!");
    }
}
