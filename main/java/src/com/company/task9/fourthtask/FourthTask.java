package com.company.task9.fourthtask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FourthTask {

    public static void main(String[] args) {
        WatchThread watchThread = new WatchThread(4);
        final Thread timeThread = new Thread(watchThread);
        final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(timeThread, 0, watchThread.getN(), TimeUnit.SECONDS);
    }
}
