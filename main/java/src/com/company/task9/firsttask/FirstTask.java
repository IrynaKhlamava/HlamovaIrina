package com.company.task9.firsttask;

import java.util.concurrent.TimeUnit;

public class FirstTask {
    public static final int SLEEP_DURATION = 2;
    private String operation;

    public String getOperation() {
        return operation;
    }

    public static void main(String[] args) {
        final FirstTask firstTask = new FirstTask();
        final Thread mainThread = new Thread(new StatusThread(firstTask), "Main thread");
        final Thread helpThread = new Thread(new StatusThread(firstTask), "Help thread");

        System.out.println(mainThread.getState());
        firstTask.sleep();
        mainThread.start();
        System.out.println(mainThread.getState());

        firstTask.operation = "Block";
        helpThread.start();
        firstTask.sleep();
        System.out.println(mainThread.getState());

        helpThread.interrupt();
        firstTask.operation = "Sleep";
        firstTask.sleep();
        System.out.println(mainThread.getState());

        firstTask.operation = "Wait";
        firstTask.sleep();
        System.out.println(mainThread.getState());

        mainThread.interrupt();
        firstTask.sleep();
        System.out.println(mainThread.getState());
    }

    public synchronized void blockThread() {

    }

    public synchronized void sleep() {
        try {
            TimeUnit.SECONDS.sleep(SLEEP_DURATION);
        } catch (InterruptedException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

}
