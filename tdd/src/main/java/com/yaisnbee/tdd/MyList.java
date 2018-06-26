package com.yaisnbee.tdd;

import java.util.Arrays;

public class MyList {

    private static final int MIN_SIZE = 10;
    private int size = 0;
    private int[] items = new int[MIN_SIZE];

    public int size() {
        return size;
    }

    public void add(int item) {
        if (size == items.length - 1) {
            items = Arrays.copyOf(items, items.length * 2);
        }
        items[size] = item;
        size++;
    }

    public int get(int index) {
        return items[index];
    }
}
