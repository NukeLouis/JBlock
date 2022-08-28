package com.jorisboschmans.models;

import com.jorisboschmans.utils.StringUtils;

import java.time.Instant;

public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timestampMilli;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestampMilli = Instant.now().toEpochMilli();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtils.applySha256(
            previousHash
            + timestampMilli
            + nonce
            + data);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}
