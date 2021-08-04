package com.company.task9.thirdtask;

import java.util.concurrent.TimeUnit;

public class ThirdTask {


    public static void main(String[] args) {
        final Thread consumerThread = new Thread(new ConsumerThread(), "Consumer");
        final Thread producerThread = new Thread(new ProducerThread(), "Producer");
        consumerThread.start();
        producerThread.start();
        sleep();
        consumerThread.interrupt();
        producerThread.interrupt();
    }

    public static synchronized void sleep() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}