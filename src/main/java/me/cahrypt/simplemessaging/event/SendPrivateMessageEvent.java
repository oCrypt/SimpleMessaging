package me.cahrypt.simplemessaging.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class SendPrivateMessageEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    private final Player sender;
    private final Player receiver;
    private String message;

    public SendPrivateMessageEvent(Player sender, Player receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}
