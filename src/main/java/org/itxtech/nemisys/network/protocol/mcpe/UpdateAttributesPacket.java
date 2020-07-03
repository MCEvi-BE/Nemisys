package org.itxtech.nemisys.network.protocol.mcpe;


public class UpdateAttributesPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.UPDATE_ATTRIBUTES_PACKET;

    //public Attribute[] entries;
    public long entityId;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public void decode() {

    }

    public void encode() {
        this.reset();

        this.putEntityRuntimeId(this.entityId);

        
    }

}
