package pliexe.guimenu.commands.guimenu;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

public class List extends SubCommand {
    private final GuiMenu plugin;

    public List(GuiMenu plugin) {
        super("list", "List all menus");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(
                ChatColor.BOLD +
                "▁▂▃▄▅▆"+ChatColor.YELLOW +
                "" + ChatColor.BOLD +
                " List of available GUI's" +
                ChatColor.RESET + ChatColor.BOLD +
                " ▆▅▄▃▂▁\n"
                + ChatColor.RESET + ChatColor.BOLD + ChatColor.RESET + ChatColor.BOLD + " > "
                + ChatColor.YELLOW + ChatColor.BOLD +
                String.join("\n" + ChatColor.RESET + ChatColor.BOLD + " > " + ChatColor.YELLOW + ChatColor.BOLD, plugin.getGuiInventoryManager().getGuiNames()));
    }
}
