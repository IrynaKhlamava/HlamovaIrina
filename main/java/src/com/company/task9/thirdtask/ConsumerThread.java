package com.company.task9.thirdtask;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ConsumerThread implements Runnable {

     private final List<Integer> list;

    public ConsumerThread() {
        this.list = new CopyOnWriteArrayList<>();
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                Integer element = DataBuffer.getInstance().take();
                list.add(element);
                System.out.println("Consumer data" + list);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
