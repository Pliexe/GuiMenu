package pliexe.guimenu.commands.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

public class Reload extends SubCommand {
    private final GuiMenu plugin;

    public Reload(GuiMenu plugin) {
        super("reload", "Reload configuration!");
        this.plugin = plugin;
    }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("Reloading configuration...");
        plugin.defaultConfig.forceReload();
        plugin.getGuiInventoryManager().reload();
        sender.sendMessage("Done reloading configuration!");
    }
}
