package me.cahrypt.simplemessaging.command;

import me.cahrypt.simplemessaging.SimpleMessaging;
import me.cahrypt.simplemessaging.config.ConfigManager;
import me.cahrypt.simplemessaging.permission.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ReloadCommand extends Command {
    private final ConfigManager config;

    public ReloadCommand() {
        super(Permissions.ADMIN.getPerm() + "reload", 0);
        this.config = JavaPlugin.getPlugin(SimpleMessaging.class).getConfigManager();
    }

    @Override
    public void onCommand(Player sender, String[] args) {
        sender.sendMessage(ChatColor.RED +
                "Config reloaded. Please note that reloading the configuration file is NOT recommended during server uptime. Please check the console for any errors.");
        config.reloadConfig();
    }
}
