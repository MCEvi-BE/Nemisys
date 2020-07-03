package org.itxtech.nemisys.event.client;

import org.itxtech.nemisys.Client;
import org.itxtech.nemisys.event.HandlerList;


public class ClientConnectEvent extends ClientEvent {

    private static final HandlerList handlers = new HandlerList();

    public ClientConnectEvent(Client client) {
        super(client);
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

}
