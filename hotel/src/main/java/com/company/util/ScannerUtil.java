package com.company.util;

import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
;

public class ScannerUtil {

    private static final Logger LOGGER = Logger.getLogger(ScannerUtil.class.getName());

    public static Long readLong() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Long.valueOf(scanner.nextLine().trim());
            } catch (InputMismatchException e) {
                LOGGER.warn(e.getLocalizedMessage(), e);
                System.out.println("ввели неверное число. ожидается ввод Long  ");
            }
        }
    }

    public static Integer readInteger() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.valueOf(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                LOGGER.warn("Ошибка ввода", e);
                System.out.println("Ошибка ввода. Введите соответствующий пункт меню");
            } catch (IndexOutOfBoundsException e) {
                LOGGER.warn("Ошибка ввода", e);
                System.out.println("Выбран не    существующий пункт меню. Введите другой");
            }
        }
    }

    public static String readString() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextLine().trim();
            } catch (InputMismatchException e) {
                LOGGER.warn(e.getLocalizedMessage(), e);
                System.out.println("Ошибка ввода. ожидается строка");
            }
        }

    }

    public static Double readDouble() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return Double.valueOf(scanner.nextLine().trim().replace(",", "."));
            } catch (InputMismatchException e) {
                LOGGER.warn(e.getLocalizedMessage(), e);
                System.out.println("ввели неверное число. ожидается double");
            }
        }

    }
}
