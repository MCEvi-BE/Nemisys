package org.itxtech.nemisys.network.protocol.mcpe;

import org.itxtech.nemisys.math.BlockVector3;


public class ItemFrameDropItemPacket extends DataPacket {

    public static final byte NETWORK_ID = ProtocolInfo.ITEM_FRAME_DROP_ITEM_PACKET;

    public int x;
    public int y;
    public int z;

    @Override
    public void decode() {
        BlockVector3 v = this.getBlockVector3();
        this.z = v.z;
        this.y = v.y;
        this.x = v.x;
    }

    @Override
    public void encode() {

    }

    @Override
    public byte pid() {
        return NETWORK_ID;
    }
}
