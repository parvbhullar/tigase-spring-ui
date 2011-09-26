package com.mitian.airad.common.utils.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 代表一个byte数组。
 * 
 * @author zhoufengbo
 */
public class ByteArray {
    private byte[] bytes;
    private int offset;
    private int length;

    public ByteArray(byte[] bytes, int offset, int length) {
        this.bytes = bytes;
        this.offset = offset;
        this.length = length;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getOffset() {
        return offset;
    }

    public int getLength() {
        return length;
    }

    public byte[] toByteArray() {
        byte[] copy = new byte[length];

        System.arraycopy(bytes, offset, copy, 0, length);

        return copy;
    }

    public InputStream toInputStream() {
        return new ByteArrayInputStream(bytes, offset, length);
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(bytes, offset, length);
    }
}
