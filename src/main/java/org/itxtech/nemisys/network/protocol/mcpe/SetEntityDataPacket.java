package org.itxtech.nemisys.network.protocol.mcpe;



public class SetEntityDataPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.SET_ENTITY_DATA_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    //public EntityMetadata metadata;

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid);
        //this.put(Binary.writeMetadata(this.metadata));
    }
}
