package com.jorisboschmans;

import com.jorisboschmans.models.Block;

public final class Main {

    private Main() {}

    public static void main(String[] args) {

        Block block1 = new Block("Hello, I'm the first block", "0");
        System.out.println("Hash for block 1: " + block1.hash);

        Block block2 = new Block("Oi, I'm the second block", block1.hash);
        System.out.println("Hash for block 2: " + block2.hash);

        Block block3 = new Block("Hi, I'm the third block", block2.hash);
        System.out.println("Hash for block 3: " + block3.hash);

    }

}
