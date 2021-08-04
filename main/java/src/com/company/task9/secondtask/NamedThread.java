package com.company.task9.secondtask;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class NamedThread implements Runnable {

    private final Semaphore semaphore;

    public NamedThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                semaphore.acquire();
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println(Thread.currentThread().getName());
                semaphore.release();
            } catch (InterruptedException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
    }
}
