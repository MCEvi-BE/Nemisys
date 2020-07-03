package org.itxtech.nemisys.network.rcon;

import java.nio.channels.SocketChannel;


public class RCONCommand {
    private SocketChannel sender;
    private int id;
    private String command;

    public RCONCommand(SocketChannel sender, int id, String command) {
        this.sender = sender;
        this.id = id;
        this.command = command;
    }

    public SocketChannel getSender() {
        return this.sender;
    }

    public int getId() {
        return this.id;
    }

    public String getCommand() {
        return this.command;
    }
}
