package me.cahrypt.simplemessaging.config;

import me.cahrypt.simplemessaging.SimpleMessaging;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigManager {
    private final SimpleMessaging main;
    private final Pattern hexPattern;
    private FileConfiguration config;

    private String senderFormat;
    private String receiverFormat;
    private String messageColor;

    public ConfigManager() {
        this.main = JavaPlugin.getPlugin(SimpleMessaging.class);
        this.hexPattern = Pattern.compile("#[a-fA-f0-9]{6}");
        reloadConfig();
    }

    public void reloadConfig() {
        main.reloadConfig();
        main.getConfig().options().copyDefaults();
        main.saveDefaultConfig();

        config = main.getConfig();
        extractConfigVals();
    }

    private void extractConfigVals() {
        senderFormat = translateCodes(config.getString("sender-format"));
        receiverFormat = translateCodes(config.getString("receiver-format"));
        messageColor = translateCodes(config.getString("message-color"));
    }

    private String translateCodes(String msg) {
        Matcher matcher = hexPattern.matcher(msg);
        while(matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, ChatColor.of(color) + "");
            matcher = hexPattern.matcher(msg);
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public String getSenderFormat() {
        return senderFormat;
    }

    public String getReceiverFormat() {
        return receiverFormat;
    }

    public String getMessageColor() {
        return messageColor;
    }

}
