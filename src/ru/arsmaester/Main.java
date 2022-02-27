package ru.arsmaester;

import java.lang.reflect.Array;
import java.util.*;
import java.lang.Object;

import static java.lang.Thread.sleep;


import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Random rd = new Random();
        int[] arr = new int[40000];
        int[] subArr1 = new int[20000];
        int[] subArr2 = new int[20000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt(100);
//            System.out.print(arr[i] + " ");
        }
        System.arraycopy(arr, 0, subArr1, 0, 20000);
        System.arraycopy(arr, 0, subArr2, 0, 20000);

        BubbleSort bs = new BubbleSort();
        BubbleSort bs1 = new BubbleSort();
        BubbleSort bs2 = new BubbleSort();
        bs.arr = arr;
        bs1.arr = subArr1;
        bs2.arr = subArr2;
//        System.out.println();
//        bs.bubbleSort(bs.arr);
//        for (int i = 0; i < bs.arr.length; i++) {
//            System.out.print(bs.arr[i] + " ");
//        }

        Thread thread = new Thread(bs);
        Thread thread1 = new Thread(bs1);
        Thread thread2 = new Thread(bs2);

        long time = System.currentTimeMillis();
        thread.start();
        System.out.println(System.currentTimeMillis() - time);

        long time1 = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int[] result = new int[bs1.arr.length + bs2.arr.length];
        System.arraycopy(bs1.arr, 0, result, 0, bs1.arr.length);
        System.arraycopy(bs2.arr, 0, result, bs1.arr.length, bs2.arr.length);
        System.out.println(System.currentTimeMillis() - time1);
    }
}

class BubbleSort
        implements Runnable {
    int[] arr;

    void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    @Override
    public void run() {
        bubbleSort(arr);
    }
}



