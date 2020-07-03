package org.itxtech.nemisys.network.protocol.spp;


public class DisconnectPacket extends SynapseDataPacket {

    public static final byte NETWORK_ID = SynapseInfo.DISCONNECT_PACKET;
    public static final byte TYPE_WRONG_PROTOCOL = 0;
    public static final byte TYPE_GENERIC = 1;
    public byte type;
    public String message;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    @Override
    public void encode() {
        this.reset();
        this.putByte(this.type);
        this.putString(this.message);
    }

    @Override
    public void decode() {
        this.type = (byte) this.getByte();
        this.message = this.getString();
    }
}
