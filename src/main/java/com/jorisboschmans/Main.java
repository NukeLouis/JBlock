package com.jorisboschmans;

import com.google.gson.GsonBuilder;
import com.jorisboschmans.models.Block;

import java.util.ArrayList;
import java.util.List;

public final class Main {

    private static final List<Block> chain = new ArrayList<>();
    private static final int DIFFICULTY = 6;

    private Main() {}

    public static void main(String[] args) {

        chain.add(new Block("Hello, I'm the first block", "0"));
        System.out.println("Trying to mine block 1...");
        chain.get(0).mineBlock(DIFFICULTY);
        chain.add(new Block("Oi, I'm the second block", chain.get(chain.size() - 1).hash));
        System.out.println("Trying to mine block 2...");
        chain.get(1).mineBlock(DIFFICULTY);
        chain.add(new Block("Hi, I'm the third block", chain.get(chain.size() - 1).hash));
        System.out.println("Trying to mine block 3...");
        chain.get(2).mineBlock(DIFFICULTY);

        if (!isChainValid())
            throw new RuntimeException();

        String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(chainJson);

    }

    private static boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

        for (int i = 1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i - 1);

            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous hashes not equal");
                return false;
            }

            if(!currentBlock.hash.substring(0, DIFFICULTY).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }

        return true;
    }

}
