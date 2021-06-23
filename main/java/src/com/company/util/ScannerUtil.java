package com.company.util;

import java.util.Scanner;

public class ScannerUtil {

    public static Long readLong() {
        try {
            Scanner scanner = new Scanner(System.in);
            Long input = scanner.nextLong();
            return input;
        } catch (Exception e) {
            System.out.println("Вы ввели не число");
            return null;
        }
    }

    public static Integer readInteger() {
        try {
            Scanner scanner = new Scanner(System.in);
            Integer inputInt = scanner.nextInt();
            return inputInt;
        } catch (Exception e) {
            System.out.println("Вы ввели не число");
            return null;
        }
    }

    public static String readString() {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            return input;
        } catch (Exception e) {
            System.out.println("Вы ввели неверные данные");
            return null;
        }
    }

    public static Double readDouble() {
        try {
            Scanner scanner = new Scanner(System.in);
            Double input = scanner.nextDouble();
            return input;
        } catch (Exception e) {
            System.out.println("Вы ввели неверные данные");
            return null;
        }
    }


}
