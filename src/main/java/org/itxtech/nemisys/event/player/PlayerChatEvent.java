package org.itxtech.nemisys.event.player;

import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.event.Cancellable;
import org.itxtech.nemisys.event.HandlerList;


public class PlayerChatEvent extends PlayerMessageEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    protected String format;

    public PlayerChatEvent(Player player, String message) {
        this(player, message, "chat.type.text");
    }

    public PlayerChatEvent(Player player, String message, String format) {
        super(player, message);

        this.format = format;
    }

    
    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
