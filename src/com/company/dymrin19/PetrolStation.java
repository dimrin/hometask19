package com.company.dymrin19;

import java.util.concurrent.*;

public class PetrolStation {
    private int amount;


    public PetrolStation(int amount) {
        this.amount = amount;
    }

    public void doRefuel(int value) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(getCallable(3000, 10000, value));
        executorService.submit(getCallable(3000, 10000, value));
        executorService.submit(getCallable(3000, 10000, value));
        executorService.submit(getCallable(3000, 10000, value));
        executorService.shutdown();


    }

    private Callable<Integer> getCallable(int a, int b, int value) {
        return new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                doSleep(a, b);
                doingProcess(value);
                return amount;
            }
        };
    }


    private void doingProcess(int value) {
        if (amount >= value) {
            System.out.println(amount = amount - value);
        } else if (amount < value) {
            System.out.println("The Storages are empty");
        }
    }

    private static void doSleep(int a, int b) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(a, b));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
