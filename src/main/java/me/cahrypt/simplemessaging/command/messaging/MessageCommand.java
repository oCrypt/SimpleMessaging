package me.cahrypt.simplemessaging.command.messaging;


import me.cahrypt.simplemessaging.SimpleMessaging;
import me.cahrypt.simplemessaging.command.Command;
import me.cahrypt.simplemessaging.permission.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageCommand extends Command {
    private final MessageManager messageManager;

    public MessageCommand() {
        super(Permissions.USER.getPerm() + "message", 2);
        SimpleMessaging main = JavaPlugin.getPlugin(SimpleMessaging.class);
        this.messageManager = main.getMessageManager();
    }

    @Override
    public void onCommand(Player sender, String[] rawArgs) {
        List<String> args = new ArrayList<>(Arrays.asList(rawArgs));

        Player receiver = Bukkit.getPlayer(args.get(0));

        if (receiver == null) {
            sender.sendMessage(ChatColor.RED + args.get(0) + " is either offline, or not a valid player. Please try again!");
            return;
        }

        args.remove(0);
        messageManager.processMessage(sender, receiver, args);

    }
}
