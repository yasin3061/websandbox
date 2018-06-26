package com.yaisnbee.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    private MyList list;

    @BeforeEach
    public void initializeList() {
        list = new MyList();
    }

    @Test
    public void whenListInitializedThenSizeShouldBeZero() {
        assertEquals(0, list.size());
    }

    @Test
    public void whenItemAddedToListThenSizeShouldIncrease() {
        list.add(10);
        assertEquals(1, list.size());
    }

    @Test
    public void whenMultipleItemsAddedThenSizeShouldIncrease() {
        list.add(10);
        list.add(15);
        list.add(20);
        assertEquals(3, list.size());
    }

    @Test
    public void whenGetItemThenItemShouldBeRetrieved() {
        list.add(10);
        list.add(15);
        assertEquals(10, list.get(0));
        assertEquals(15, list.get(1));
    }

    @Test
    public void whenAddAlotOfItemsThenAllShouldBeRetrieved() {

        int count = 0;
        while(count < 100) {
            list.add(count);
            count++;
        }

        count = 0;
        while (count < 100) {
            assertEquals(count, list.get(count));
            count++;
        }
    }

}