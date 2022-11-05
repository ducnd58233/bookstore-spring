package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class UID {
    @JsonIgnore
    private Integer localId;

    @JsonIgnore
    private Integer objectType;

    @JsonIgnore
    private Integer shardID;

    public UID(Integer localId, Integer objectType, Integer shardID) {
        this.localId = localId;
        this.objectType = objectType;
        this.shardID = shardID;
    }

    @Override
    public String toString() {
        Long val = Long.valueOf(localId) << 28 | Long.valueOf(objectType) << 18 | Long.valueOf(shardID) << 0;
        return Long.toString(val);
    }

    public static UID DecomposeUID(String s) {
        Long uid = Long.valueOf(s);

        UID u = new UID((int)(uid >> 28), (int)(uid >> 18 & 0x3ff), (int)(uid >> 0 & 0x3FFFF));
        return u;
    }
}
