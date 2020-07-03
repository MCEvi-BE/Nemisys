package org.itxtech.nemisys.raknet.protocol.packet;

import org.itxtech.nemisys.raknet.RakNet;
import org.itxtech.nemisys.raknet.protocol.Packet;

import java.net.InetSocketAddress;


public class OPEN_CONNECTION_REQUEST_2 extends Packet {
    public static byte ID = (byte) 0x07;

    @Override
    public byte getID() {
        return ID;
    }

    public long clientID;
    public String serverAddress;
    public int serverPort;
    public short mtuSize;

    @Override
    public void encode() {
        super.encode();
        this.put(RakNet.MAGIC);
        this.putAddress(this.serverAddress, this.serverPort);
        this.putShort(this.mtuSize);
        this.putLong(this.clientID);
    }

    @Override
    public void decode() {
        super.decode();
        this.offset += 16; //skip magic bytes
        InetSocketAddress address = this.getAddress();
        this.serverAddress = address.getHostString();
        this.serverPort = address.getPort();
        this.mtuSize = this.getSignedShort();
        this.clientID = this.getLong();
    }

    public static final class Factory implements Packet.PacketFactory {

        @Override
        public Packet create() {
            return new OPEN_CONNECTION_REQUEST_2();
        }

    }
}
