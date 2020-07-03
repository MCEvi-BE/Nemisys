package org.itxtech.nemisys.raknet.protocol.packet;

import org.itxtech.nemisys.raknet.protocol.Packet;


public class CLIENT_DISCONNECT_DataPacket extends Packet {
    public static byte ID = (byte) 0x15;

    @Override
    public byte getID() {
        return ID;
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new CLIENT_DISCONNECT_DataPacket();
        }

    }
}
