package org.itxtech.nemisys.network.rcon;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class RCONPacket {
    private int id;
    private int type;
    private byte[] payload;

    public RCONPacket(int id, int type, byte[] payload) {
        this.id = id;
        this.type = type;
        this.payload = payload;
    }

    public RCONPacket(ByteBuffer buffer) throws IOException {
        int size = buffer.getInt();

        this.id = buffer.getInt();
        this.type = buffer.getInt();
        this.payload = new byte[size - 10];
        buffer.get(this.payload);

        buffer.get(new byte[2]);
    }

    public ByteBuffer toBuffer() {
        ByteBuffer buffer = ByteBuffer.allocate(this.payload.length + 14);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        buffer.putInt(this.payload.length + 10);
        buffer.putInt(this.id);
        buffer.putInt(this.type);
        buffer.put(this.payload);

        buffer.put((byte) 0);
        buffer.put((byte) 0);

        buffer.flip();
        return buffer;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public byte[] getPayload() {
        return payload;
    }
}
