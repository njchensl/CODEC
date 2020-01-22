package me.nanjingchj.codec;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class BitIterableByteArray implements Iterable<Boolean> {
    private final byte[] array;

    public BitIterableByteArray(byte[] array) {
        this.array = array;
    }

    @NotNull
    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<>() {
            private int bitIndex = 0;
            private int arrayIndex = 0;

            @Override
            public boolean hasNext() {
                return (arrayIndex < array.length) && (bitIndex < 8);
            }

            @Override
            public Boolean next() {
                Boolean val = (array[arrayIndex] >> (7 - bitIndex) & 1) == 1;
                bitIndex++;
                if (bitIndex == 8) {
                    bitIndex = 0;
                    arrayIndex++;
                }
                return val;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
