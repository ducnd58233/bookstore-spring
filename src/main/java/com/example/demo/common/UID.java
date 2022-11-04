package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class UID {
    @JsonIgnore
    private Long localId;

    @JsonIgnore
    private Long objectType;

    @JsonIgnore
    private int shardID;

    public UID(Long localId, Long objectType, int shardID) {
        this.localId = localId;
        this.objectType = objectType;
        this.shardID = shardID;
    }

    @Override
    public String toString() {
        Long val = localId << 28 | objectType << 18 | Long.valueOf(shardID) << 0;
        return Long.toString(val);
    }

    public static UID DecomposeUID(String s) {
        Long uid = Long.valueOf(s);

        UID u = new UID(uid >> 28, uid >> 18 & 0x3ff, (int)(uid >> 0 & 0x3FFFF));
        return u;
    }
}
