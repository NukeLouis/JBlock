package com.jorisboschmans.models;

import com.jorisboschmans.utils.StringUtils;

import java.time.Instant;

public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timestampMilli;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestampMilli = Instant.now().toEpochMilli();
        this.hash = calculateHash();
    }

    private String calculateHash() {
        return StringUtils.applySha256(
            previousHash
            + timestampMilli
            + data);
    }
}
