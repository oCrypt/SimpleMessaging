package me.cahrypt.simplemessaging.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class Command implements CommandExecutor {
    private final String permission;
    private final int minArgs;

    public Command(String permission, int minArgs) {
        this.permission = permission;
        this.minArgs = minArgs;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            Bukkit.getLogger().info("You must be a player to run that command!");
            return true;
        }

        if (!player.hasPermission(permission)) {
            player.sendMessage(ChatColor.RED + "You do not have the proper permissions to run that command!");
            return true;
        }

        if (args.length < minArgs) {
            player.sendMessage(ChatColor.RED + "Invalid arguments! Please follow the below usage:\n" + command.getUsage());
            return true;
        }

        onCommand(player, args);
        return true;
    }

    public abstract void onCommand(Player sender, String[] args);
}
