package pliexe.guimenu.commandhandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.commandhandler.SubCommand;

public abstract class SubCommandExtended extends SubCommand {

    protected SubCommandExtended(String name, String description, String usage) {
        super(name, description, usage);
    }

    protected SubCommandExtended(String name, String description) {
        super(name, description);
    }

    protected  abstract  void Run(CommandSender sender, Command command, String label, String[] args, String data);

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) { }
}
