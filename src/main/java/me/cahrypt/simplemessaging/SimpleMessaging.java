package me.cahrypt.simplemessaging;

import me.cahrypt.simplemessaging.command.ReloadCommand;
import me.cahrypt.simplemessaging.command.messaging.MessageCommand;
import me.cahrypt.simplemessaging.command.messaging.MessageManager;
import me.cahrypt.simplemessaging.command.messaging.ReplyCommand;
import me.cahrypt.simplemessaging.config.ConfigManager;
import me.cahrypt.simplemessaging.listener.PMSendEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleMessaging extends JavaPlugin {
    private ConfigManager configManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {

        this.configManager = new ConfigManager();
        this.messageManager = new MessageManager();

        getCommand("message").setExecutor(new MessageCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("reloadmessaging").setExecutor(new ReloadCommand());

        new PMSendEvent();

    }

    @Override
    public void onDisable() {

    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
