package org.itxtech.nemisys.raknet.protocol.packet;

import org.itxtech.nemisys.raknet.protocol.DataPacket;
import org.itxtech.nemisys.raknet.protocol.Packet;


public class DATA_PACKET_D extends DataPacket {
    public static byte ID = (byte) 0x8d;

    @Override
    public byte getID() {
        return ID;
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new DATA_PACKET_D();
        }

    }

}
