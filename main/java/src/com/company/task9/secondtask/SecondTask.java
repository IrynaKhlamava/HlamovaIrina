package com.company.task9.secondtask;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SecondTask {

    public static final int SLEEP_DURATION = 2;

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(1, true);
        final Thread firstThread = new Thread(new NamedThread(semaphore), "First thread");
        final Thread secondThread = new Thread(new NamedThread(semaphore), "Second thread");
        firstThread.start();
        secondThread.start();
        sleep();
        firstThread.interrupt();
        secondThread.interrupt();
    }

    public static synchronized void sleep() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
