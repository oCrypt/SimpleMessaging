package me.cahrypt.simplemessaging.command.messaging;

import me.cahrypt.simplemessaging.SimpleMessaging;
import me.cahrypt.simplemessaging.command.Command;
import me.cahrypt.simplemessaging.permission.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ReplyCommand extends Command {
    private final MessageManager messageManager;

    public ReplyCommand() {
        super(Permissions.USER.getPerm() + "reply", 1);

        SimpleMessaging main = JavaPlugin.getPlugin(SimpleMessaging.class);
        this.messageManager = main.getMessageManager();
    }

    @Override
    public void onCommand(Player sender, String[] args) {
        Player receiver = messageManager.getRecentlyMessaged(sender);

        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + "You have nobody to reply to!");
            return;
        }

        messageManager.processMessage(sender, receiver, List.of(args));
    }
}
