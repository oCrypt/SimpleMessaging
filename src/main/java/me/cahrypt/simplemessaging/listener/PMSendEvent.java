package me.cahrypt.simplemessaging.listener;

import me.cahrypt.com.keywords.listener.KeywordListener;
import me.cahrypt.simplemessaging.event.SendPrivateMessageEvent;
import org.bukkit.event.EventHandler;

public class PMSendEvent extends KeywordListener {

    public PMSendEvent() {
        super();
    }

    @Override
    protected boolean shouldListen() {
        return true;
    }

    @EventHandler
    public void onPMSendEvent(SendPrivateMessageEvent event) {
        event.setMessage(formatMessage(event.getMessage()));
    }
}
