package pliexe.guimenu.commandhandler;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;

public abstract class GroupCommand {
    protected HashMap<String, Object> commands = new HashMap<>();

    protected final String name;
    protected final String description;

    String getName() {
        return name;
    }
    String getDescription() { return description; }

    protected GroupCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected abstract void loadCommands();

    protected void LoadCommand(GroupCommand cmd) {
        commands.put(cmd.name, cmd);
    }

    protected void LoadCommand(SubCommand cmd) {
        commands.put(cmd.name, cmd);
    }

    protected void Run(CommandSender sender, Command command, String label, String[] args, int subLevel)
    {
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
                        ((SubCommand) cmd).Run(sender, command, label, Arrays.copyOfRange(args, subLevel + 1, args.length));
                    else ((GroupCommand) cmd).Run(sender, command, label, args, subLevel+1);
                }
            }
        } else ShowHelp(sender, 0, args, command.getName(), subLevel);
    }

    protected void ShowHelp(CommandSender sender, int index, String args[], String commandName, int subLevel) {
        if(commands.size() > 0) {

            int maxLoop;
            int maxPages = 1;
            int i = 0;

            if(commands.size() > 10)
            {
                if(index <= 0) index = 1;
                maxPages = (int) Math.ceil((float)commands.size() / 10f);

                if(index > maxPages) index = maxPages;

                i = (index - 1) * 10;
                maxLoop = index * 10;
                if(maxLoop > commands.size()) maxLoop = commands.size();
            } else {
                maxLoop = commands.size();
            }

            StringBuilder response = new StringBuilder()
                    .append(ChatColor.BOLD)
                    .append("▁▂▃▄▅▆ ")
                    .append(ChatColor.YELLOW)
                    .append(ChatColor.BOLD)
                    .append("GuiMenu: Help. Page ")
                    .append(index + 1)
                    .append(" out of ")
                    .append(maxPages)
                    .append(ChatColor.RESET)
                    .append(ChatColor.BOLD)
                    .append(" ▆▅▄▃▂▁\n\n");

            int maxStringSize = 0;

            StringBuilder argsStr = new StringBuilder();

            if(args.length >= subLevel)
                for(int j = 0; j < subLevel; j++)
                    argsStr.append(args[j]).append(" ");

            Object[] values = commands.values().toArray();

            for (;i < maxLoop; i++)
            {
                StringBuilder line = new StringBuilder();

                line
                        .append(ChatColor.BOLD)
                        .append(" > ")
                        .append(ChatColor.YELLOW)
                        .append(ChatColor.BOLD)
                        .append("/")
                        .append(commandName)
                        .append(" ")
                        .append(argsStr)
                        .append(values[i] instanceof GroupCommand ? ((GroupCommand)values[i]).name : ((SubCommand)values[i]).name)
                        .append(" ")
                        .append(values[i] instanceof GroupCommand ? "<subcommand>" : (((SubCommand)values[i]).usage == null ? "" : ((SubCommand)values[i]).usage + " "))
                        .append(ChatColor.RESET)
                        .append(ChatColor.BOLD)
                        .append("- ")
                        .append(values[i] instanceof GroupCommand ? ((GroupCommand)values[i]).description : ((SubCommand)values[i]).description);

                if(line.length() > maxStringSize) maxStringSize = line.length();
                response.append(line.append("\n"));
            }

            sender.sendMessage(response.append("\n").toString());
        } else sender.sendMessage("Unknown command!");
    }
}
