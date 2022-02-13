package pliexe.guimenu.commands.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

public class New extends SubCommand {
    private final GuiMenu plugin;

    public New(GuiMenu plugin) { super("new", "Test cmd"); this.plugin = plugin; }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
//        plugin.defaultConfig.set(args[0], String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
        sender.sendMessage(plugin.defaultConfig.get("testing ").getClass().getSimpleName());
    }
}
