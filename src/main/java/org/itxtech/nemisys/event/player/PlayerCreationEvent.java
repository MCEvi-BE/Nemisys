package org.itxtech.nemisys.event.player;

import org.itxtech.nemisys.Player;
import org.itxtech.nemisys.event.Event;
import org.itxtech.nemisys.event.HandlerList;
import org.itxtech.nemisys.network.SourceInterface;


public class PlayerCreationEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    private final SourceInterface interfaz;

    private final Long clientId;

    private final String address;

    private final int port;

    private Class<? extends Player> baseClass;

    private Class<? extends Player> playerClass;

    public PlayerCreationEvent(SourceInterface interfaz, Class<? extends Player> baseClass, Class<? extends Player> playerClass, long clientId, String address, int port) {
        this.interfaz = interfaz;
        this.clientId = clientId;
        this.address = address;
        this.port = port;

        this.baseClass = baseClass;
        this.playerClass = playerClass;
    }

    public SourceInterface getInterface() {
        return interfaz;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public long getClientId() {
        return clientId;
    }

    public Class<? extends Player> getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(Class<? extends Player> baseClass) {
        this.baseClass = baseClass;
    }

    public Class<? extends Player> getPlayerClass() {
        return playerClass;
    }

    public void setPlayerClass(Class<? extends Player> playerClass) {
        this.playerClass = playerClass;
    }
}
