package org.itxtech.nemisys.network.protocol.mcpe;

public class ResourcePacksInfoPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.RESOURCE_PACKS_INFO_PACKET;

    public boolean mustAccept = false;
    

    @Override
    public void decode() {

    }

    @Override
    public void encode() {
        this.reset();
        
    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }
}
