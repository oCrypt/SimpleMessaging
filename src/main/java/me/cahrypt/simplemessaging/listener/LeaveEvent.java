package me.cahrypt.simplemessaging.listener;

import me.cahrypt.simplemessaging.SimpleMessaging;
import me.cahrypt.simplemessaging.command.messaging.MessageManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class LeaveEvent implements Listener {
    private final MessageManager messageManager;

    public LeaveEvent() {
        this.messageManager = JavaPlugin.getPlugin(SimpleMessaging.class).getMessageManager();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        messageManager.removeRecentMessenger(event.getPlayer());
    }
}
