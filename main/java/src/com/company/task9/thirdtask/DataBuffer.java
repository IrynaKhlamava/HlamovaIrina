package com.company.task9.thirdtask;

import javax.xml.crypto.Data;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataBuffer {
    private static final int LIMIT = 4;
    public static DataBuffer instance;
    private final Queue<Integer> queue;
    private final Lock lock;
    private final Condition condition;

    private DataBuffer() {
        this.queue = new ConcurrentLinkedDeque<>();
        this.lock = new ReentrantLock();
        this.condition = lock.newCondition();
    }

    public static DataBuffer getInstance() {
        instance = Objects.requireNonNullElse(instance, new DataBuffer());
        return instance;
    }

    public void put(Integer element) {
        try {
            lock.lock();
            if (queue.size() == LIMIT) {
                condition.await();
            }
            condition.signal();
            queue.add(element);
            System.out.printf("Buffer data: [%s]", queue);
            lock.unlock();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

    }

    public Integer take() throws InterruptedException {
        lock.lock();
        if (queue.isEmpty()) {
            condition.await();
        }
        condition.signal();
        lock.unlock();
        System.out.println(" Data: " + queue);
        return queue.poll();
    }
}

