package pliexe.guimenu.commandhandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public abstract class GroupCommandExtended extends GroupCommand {
    protected final String usage;

    protected GroupCommandExtended(String name, String description, String usage) {
        super(name, description);
        this.usage = usage;
    }

    protected void LoadCommand(SubCommandExtended cmd) {
        super.LoadCommand(cmd);
    }

    protected void Run(CommandSender sender, Command command, String label, String[] args, int subLevel, String data) {
        if(args.length > subLevel) {
            try {
                int index = Integer.parseInt(args[0]);
                ShowHelp(sender, index, args, command.getName(), subLevel);
            } catch (NumberFormatException e) {
                Object cmd = commands.get(args[subLevel]);
                if(cmd == null)
                {
                    sender.sendMessage(ChatColor.BOLD + "" + ChatColor.AQUA + "Sub Command not found! Showing list of commands!");
                    ShowHelp(sender, 0, args, command.getName(), subLevel);
                } else {
                    if(cmd instanceof SubCommand)
                        ((SubCommandExtended) cmd).Run(sender, command, label, Arrays.copyOfRange(args, subLevel + 1, args.length), data);
                    else ((GroupCommand) cmd).Run(sender, command, label, args, subLevel+1);
                }
            }
        } else ShowHelp(sender, 0, args, command.getName(), subLevel);
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
}
