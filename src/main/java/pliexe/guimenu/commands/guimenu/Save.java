package pliexe.guimenu.commands.guimenu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import pliexe.guimenu.GuiMenu;
import pliexe.guimenu.commandhandler.SubCommand;

public class Save extends SubCommand {
    private final GuiMenu plugin;

    public Save(GuiMenu plugin) { super("pombar", "Test cmd"); this.plugin = plugin; }

    @Override
    protected void Run(CommandSender sender, Command command, String label, String[] args) {
//        sender.sendMessage("BEFORE CHECK");
//
////        List<String> list = plugin.defaultConfig.getStringList("testingFields");
//
//        sender.sendMessage("START");
//        /*
//        plugin.defaultConfig.get*/
//
////        if(list.isEmpty())
////            sender.sendMessage("This path is not a config section");
////        else sender.sendMessage(String.join("\n", list));
//
//
//        plugin.defaultConfig.getSection("testingFields");
//
//        sender.sendMessage(plugin.defaultConfig.getString("testingFields"));
//
//        sender.sendMessage("END");

        sender.sendMessage(plugin.defaultConfig.get("menus.default.displayName").getClass().getTypeName());

        if(sender instanceof Player) {

            Player player = (Player) sender;

            player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);

            sender.sendMessage("POMBAR!");
        }

    }
}
