package pliexe.guimenu.commands.guimenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.SubCommand;
import java.util.ArrayList;

public class Show extends SubCommand {
    private final GuiMenu plugin;

    public Show(GuiMenu plugin) {
        super("show", "Show an gui menu!");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if(args.length > 1) {

                StringBuilder name = new StringBuilder();

                for(int i = 1; i < args.length; i++)
                    name.append(args[i]).append(" ");

                if(!plugin.getGuiInventoryManager().openGui(name.toString().trim(), (Player) sender)) {
                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Menu named " + name.toString().trim() + " was not found!");
                }

            } else sender.sendMessage("Missing gui menu name! Usage: /guimenu show \"menuname\"");
        } else sender.sendMessage("You may not run this command in console!");
    }
}
