package com.company.task9.fourthtask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WatchThread implements Runnable{
    private static  final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    int sec;

    public WatchThread(int sec) {
        this.sec = sec;
    }

    public int getSec() {
        return sec;
    }

    @Override
    public void run() {
        System.out.println(LocalDateTime.now().format(FORMATTER));
    }
}
