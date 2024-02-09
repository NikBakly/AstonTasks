package org.example.Homework1;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyArrayListTest {
    private MyList<Integer> myArrayList;

    @BeforeEach
    public void setUp() {
        myArrayList = new MyArrayList<>();
        myArrayList.add(5);

    }

    @Test
    public void testAddAndGet() {
        myArrayList.add(10);
        assertEquals(5, myArrayList.get(0));
        assertEquals(10, myArrayList.get(1));
    }

    @Test
    public void testAddAtIndex() {
        myArrayList.add(1, 10);
        assertEquals(5, myArrayList.get(0));
        assertEquals(10, myArrayList.get(1));
    }

    @Test
    public void testRemove() {
        myArrayList.add(10);

        myArrayList.remove(5);

        assertEquals(10, myArrayList.get(0));
    }

    @Test
    public void testRemoveAll() {
        myArrayList.add(10);

        myArrayList.removeAll();

        assertEquals(0, myArrayList.size());
    }

    @Test
    public void testSort() {
        myArrayList.add(4);
        myArrayList.add(2);
        myArrayList.add(7);

        Comparator<Integer> c = Integer::compareTo;
        myArrayList.sort(c);

        assertEquals(2, myArrayList.get(0));
        assertEquals(4, myArrayList.get(1));
        assertEquals(5, myArrayList.get(2));
        assertEquals(7, myArrayList.get(3));
    }

    @Test
    public void testSortWithString() {
        MyList<String> myArrayList1 = new MyArrayList<>();
        myArrayList1.add("Apple");
        myArrayList1.add("Orange");
        myArrayList1.add("Banana");

        Comparator<String> c = String::compareTo;
        myArrayList1.sort(c);

        assertEquals("Apple", myArrayList1.get(0));
        assertEquals("Banana", myArrayList1.get(1));
        assertEquals("Orange", myArrayList1.get(2));
    }

    @Test
    public void testExceptionWhenAddAtIndex() {
        myArrayList.add(10);
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () -> {
            myArrayList.add(10, 14);
        });
        assertEquals("Index: 10, Size: 2", e.getMessage());
    }

    @Test
    public void testExceptionWhenGetAtIndex() {
        myArrayList.add(10);
        IndexOutOfBoundsException e = assertThrows(IndexOutOfBoundsException.class, () -> {
            myArrayList.get(10);
        });
        assertEquals("Index: 10, Size: 2", e.getMessage());
    }
}