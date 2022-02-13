package pliexe.guimenu.commands.guimenu;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

public class Show extends SubCommand {
    private final GuiMenu plugin;

    public Show(GuiMenu plugin) {
        super("show", "Show an gui menu!", "<gui name>");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length > 0) {

                StringBuilder name = new StringBuilder();

                for(int i = 0; i < args.length; i++)
                    name.append(args[i]).append(" ");

                if(!plugin.getGuiInventoryManager().openGui(name.toString().trim(), (Player) sender)) {
                    BadUsage(sender, "<gui name>", ChatColor.RED + "Menu named \"" + ChatColor.BOLD + name.toString().trim() + ChatColor.RED + "\" was not found!");
                }

            } else BadUsage(sender, "<gui name>");
        } else sender.sendMessage("You may not run this command in console!");
    }
}
