package com.github.okeyja.java_meaningful.concurrent.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMap_Sample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> inventory = new ConcurrentHashMap<>();
        inventory.put("Apple iPhone 12 Pro MAX 128GB", 54);
    }
}
