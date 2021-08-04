package com.company.task9.thirdtask;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ProducerThread implements Runnable{

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                DataBuffer.getInstance().put(new Random().nextInt(50));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
