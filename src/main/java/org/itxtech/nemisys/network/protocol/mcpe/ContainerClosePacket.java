package org.itxtech.nemisys.network.protocol.mcpe;


public class ContainerClosePacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.CONTAINER_CLOSE_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public int windowId;

    @Override
    public void decode() {
        this.windowId = this.getByte();
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte((byte) this.windowId);
    }
}
