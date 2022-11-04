package com.example.demo.common;

import java.util.UUID;

public class LocalUUID {
    private static final String UUID_BASE = "0000-1000-8000-00805F9B34FB";
    private static final int MASK_UUID16 = 0x0000FFFF;

    public static final UUID fromUUID16(Long uuid16) {

        String s = uuidStringFromUuid16(uuid16);
        UUID uuid = UUID.fromString(s);

        return uuid;
    }

    private static final String uuidStringFromUuid16(Long uuid16) {
        StringBuilder b = new StringBuilder();
        String hex = Long.toHexString(uuid16 & MASK_UUID16);
        b.append("00000000".substring(hex.length()));
        b.append(hex);
        b.append('-');
        b.append(UUID_BASE);

        return b.toString();
    }
}
