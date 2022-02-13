package pliexe.guimenu.commandhandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
    protected final String name;
    protected final String description;
    protected final String usage;

    String getName() {
        return name;
    }
    String getDescription() { return description; }

    protected SubCommand(String name, String description, String usage) {
        this.name = name;
        this.description = description;
        this.usage = usage;
    }

    protected SubCommand(String name, String description) {
        this.name = name;
        this.description = description;
        this.usage = null;
    }

    protected void BadUsage(CommandSender sender, String wrongUsage, String reason) {
        sender.sendMessage("Wrong Syntax: " + ChatColor.YELLOW + usage.replace(wrongUsage, ChatColor.BOLD + wrongUsage + ChatColor.YELLOW) + ChatColor.RESET + ". " + reason);
    }

    protected void BadUsage(CommandSender sender, String wrongUsage) {
        sender.sendMessage("Missing Syntax: " + ChatColor.YELLOW + usage.replace(wrongUsage, ChatColor.BOLD + wrongUsage + ChatColor.YELLOW) + ChatColor.RESET);
    }

    protected void BadUsage(CommandSender sender) {
        sender.sendMessage("Missing Syntax: " + ChatColor.YELLOW + usage);
    }

    protected abstract void Run(CommandSender sender, Command command, String label, String[] args);
}
