package pliexe.guimenu.commands.guimenu.edit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommandExtended;

public class Items extends SubCommandExtended {
    private final GuiMenu plugin;

    public Items(GuiMenu plugin) {
        super("items", "Edit the items inside the inventory!");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args, String data) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            plugin.getGuiInventoryManager().openEditable(data, player);

        } else sender.sendMessage("This command can't be run in console!");
    }
}
