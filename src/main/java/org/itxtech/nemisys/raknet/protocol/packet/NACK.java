package org.itxtech.nemisys.raknet.protocol.packet;

import org.itxtech.nemisys.raknet.protocol.AcknowledgePacket;
import org.itxtech.nemisys.raknet.protocol.Packet;


public class NACK extends AcknowledgePacket {

    public static byte ID = (byte) 0xa0;

    @Override
    public byte getID() {
        return ID;
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new NACK();
        }

    }
}
