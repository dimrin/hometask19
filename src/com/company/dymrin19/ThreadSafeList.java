package com.company.dymrin19;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadSafeList {
    private CopyOnWriteArrayList<String> safeList;

    public ThreadSafeList(CopyOnWriteArrayList<String> safeList) {
        this.safeList = safeList;
    }

    @Override
    public String toString() {
        return "ThreadSafeList{" +
                "safeList=" + safeList +
                '}';
    }

    public void add(String firstString, String secondString) {
        try {
            Thread t0 = new Thread(getRunnable(firstString));
            Thread t1 = new Thread(getRunnable(secondString));
            t0.start();
            t1.start();
            t0.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void getIndex(String string) {
        System.out.println(safeList.indexOf(string));
    }

    public void getValue(int index) {
        System.out.println(safeList.get(index));
    }

    public void remove(String string) {
        safeList.remove(string);
    }

    private Runnable getRunnable(String string) {
        return new Runnable() {
            @Override
            public void run() {
                safeList.addIfAbsent(string);
            }
        };
    }
}
