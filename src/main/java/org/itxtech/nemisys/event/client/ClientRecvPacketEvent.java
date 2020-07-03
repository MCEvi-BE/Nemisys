package org.itxtech.nemisys.event.client;

import org.itxtech.nemisys.Client;
import org.itxtech.nemisys.event.HandlerList;
import org.itxtech.nemisys.network.protocol.spp.SynapseDataPacket;


public class ClientRecvPacketEvent extends ClientEvent {

    private static final HandlerList handlers = new HandlerList();
    private SynapseDataPacket pk;

    public ClientRecvPacketEvent(Client client, SynapseDataPacket pk) {
        super(client);
        this.pk = pk;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    public SynapseDataPacket getPacket() {
        return pk;
    }
}
