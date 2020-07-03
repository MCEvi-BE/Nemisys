package org.itxtech.nemisys.network.protocol.mcpe;


public class MobArmorEquipmentPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.MOB_ARMOR_EQUIPMENT_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    public long eid;
    //public Item[] slots = new Item[4];

    @Override
    public void decode() {
        this.eid = this.getEntityRuntimeId();

    }

    @Override
    public void encode() {
        this.reset();
        this.putEntityRuntimeId(this.eid);

    }
}
