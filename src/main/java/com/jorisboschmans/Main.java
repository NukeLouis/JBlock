package com.jorisboschmans;

import com.google.gson.GsonBuilder;
import com.jorisboschmans.models.Block;

import java.util.ArrayList;
import java.util.List;

public final class Main {

    private static final List<Block> chain = new ArrayList<>();

    private Main() {}

    public static void main(String[] args) {

        chain.add(new Block("Hello, I'm the first block", "0"));
        chain.add(new Block("Oi, I'm the second block", chain.get(chain.size() - 1).hash));
        chain.add(new Block("Hi, I'm the third block", chain.get(chain.size() - 1).hash));

        String chainJson = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(chainJson);

    }

}
