package me.antja03.chesttools.command;

import me.antja03.chesttools.ChestTools;
import me.antja03.chesttools.menu.ToolsMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        ChestTools.getInstance().getLwmHandler().openMenuForPlayer(player, new ToolsMenu());
        return true;
    }

}
