package me.cahrypt.simplemessaging.command.messaging;

import me.cahrypt.simplemessaging.SimpleMessaging;
import me.cahrypt.simplemessaging.config.ConfigManager;
import me.cahrypt.simplemessaging.event.SendPrivateMessageEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManager {
    private final ConfigManager config;
    private final Map<Player, Player> recentMessages;

    public MessageManager() {
        this.config = JavaPlugin.getPlugin(SimpleMessaging.class).getConfigManager();
        this.recentMessages = new HashMap<>();
    }

    private String getFormattedMessage(Player sender, Player receiver, String message, String format) {
        return format
                .replaceAll("%sender%", sender.getName())
                .replaceAll("%receiver%", receiver.getName())
                .replaceAll("%message%", message);
    }

    private boolean hasRecentlyMessaged(Player sender, Player wantedReceiver) {
        if (!recentMessages.containsKey(sender)) {
            return false;
        }

        Player receivedReceiver = recentMessages.get(sender);
        return wantedReceiver.getUniqueId() == receivedReceiver.getUniqueId();
    }

    protected void processMessage(Player sender, Player receiver, List<String> rawMessageList) {

        String coloredMessage = config.getMessageColor() + String.join(config.getMessageColor() + " ", rawMessageList);

        SendPrivateMessageEvent sendPrivateMessageEvent = new SendPrivateMessageEvent(sender, receiver, coloredMessage);
        Bukkit.getPluginManager().callEvent(sendPrivateMessageEvent);

        String finalMessage = sendPrivateMessageEvent.getMessage();

        sender.sendMessage(getFormattedMessage(sender, receiver, finalMessage, config.getSenderFormat()));
        receiver.sendMessage(getFormattedMessage(sender, receiver, finalMessage, config.getReceiverFormat()));

        if (!hasRecentlyMessaged(sender, receiver)) {
            recentMessages.remove(sender);
            recentMessages.put(sender, receiver);
        }
    }

    public void removeRecentMessenger(Player sender) {
        recentMessages.remove(sender);
    }

    public Player getRecentlyMessaged(Player sender) {
        return recentMessages.get(sender);
    }
}
