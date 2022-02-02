package pliexe.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    protected String name;
    protected String description;

    String getName() {
        return name;
    }
    String getDescription() { return description; }

    protected SubCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected abstract void Run(CommandSender sender, Command command, String label, String[] args);
}
