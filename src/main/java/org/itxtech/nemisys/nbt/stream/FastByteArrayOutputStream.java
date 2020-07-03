package org.itxtech.nemisys.nbt.stream;




import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;



public class FastByteArrayOutputStream extends OutputStream {

    public static final long ONEOVERPHI = 106039;

    
    public final static int DEFAULT_INITIAL_CAPACITY = 16;

    
    public byte[] array;

    
    public int length;

    
    private int position;

    
    public FastByteArrayOutputStream() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    
    public FastByteArrayOutputStream(final int initialCapacity) {
        array = new byte[initialCapacity];
    }

    
    public FastByteArrayOutputStream(final byte[] a) {
        array = a;
    }

    
    public FastByteArrayOutputStream reset() {
        length = 0;
        position = 0;
        return this;
    }

    public void write(final int b) {
        if (position == length) {
            length++;
            if (position == array.length) array = grow(array, length);
        }
        array[position++] = (byte) b;
    }

    public static void ensureOffsetLength(final int arrayLength, final int offset, final int length) {
        if (offset < 0) throw new ArrayIndexOutOfBoundsException("Offset (" + offset + ") is negative");
        if (length < 0) throw new IllegalArgumentException("Length (" + length + ") is negative");
        if (offset + length > arrayLength)
            throw new ArrayIndexOutOfBoundsException("Last index (" + (offset + length) + ") is greater than array length (" + arrayLength + ")");
    }

    public static byte[] grow(final byte[] array, final int length) {
        if (length > array.length) {
            final int newLength = (int) Math.min(Math.max((ONEOVERPHI * array.length) >>> 16, length), Integer.MAX_VALUE);
            final byte[] t =
                    new byte[newLength];
            System.arraycopy(array, 0, t, 0, array.length);
            return t;
        }
        return array;
    }

    public static byte[] grow(final byte[] array, final int length, final int preserve) {
        if (length > array.length) {
            final int newLength = (int) Math.min(Math.max((ONEOVERPHI * array.length) >>> 16, length), Integer.MAX_VALUE);
            final byte[] t =
                    new byte[newLength];
            System.arraycopy(array, 0, t, 0, preserve);
            return t;
        }
        return array;
    }

    public void write(final byte[] b, final int off, final int len) throws IOException {
        if (position + len > array.length) array = grow(array, position + len, position);
        System.arraycopy(b, off, array, position, len);
        if (position + len > length) length = position += len;
    }

    public void position(long newPosition) {
        if (position > Integer.MAX_VALUE) throw new IllegalArgumentException("Position too large: " + newPosition);
        position = (int) newPosition;
    }

    public long position() {
        return position;
    }

    public long length() throws IOException {
        return length;
    }

    public byte[] toByteArray() {
        if (position == array.length) return array;
        return Arrays.copyOfRange(array, 0, position);
    }
}