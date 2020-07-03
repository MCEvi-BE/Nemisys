package org.itxtech.nemisys.network.protocol.mcpe;


public class MobEquipmentPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.MOB_EQUIPMENT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    public int inventorySlot;
    public int hotbarSlot;
    public int windowId;

    @Override
    public void decode() {
        this.eid = this.getEntityRuntimeId(); //EntityRuntimeID

    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid); //EntityRuntimeID

    }
}
