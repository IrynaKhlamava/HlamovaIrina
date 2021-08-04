package com.company.task9.firsttask;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StatusThread implements Runnable {

    private final FirstTask clazz;
    private final Lock lock;
    private final Condition condition;


    public StatusThread(FirstTask clazz) {
        this.clazz = clazz;
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    @Override
    public void run() {
        try {
            while (Thread.currentThread().isAlive()) {
                if ("Block".equals(clazz.getOperation())) {
                    clazz.blockThread();
                }
                if ("Sleep".equals(clazz.getOperation())) {
                    lock.lock();
                    condition.signal();
                    TimeUnit.SECONDS.sleep(1);
                }
                if ("Wait".equals(clazz.getOperation())) {
                    lock.lock();
                    condition.await();
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}
