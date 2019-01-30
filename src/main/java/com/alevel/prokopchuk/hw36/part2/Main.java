package com.alevel.prokopchuk.hw36.part2;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Integer> primeNumbers = new ArrayList<>();

    private static int counter;
    private static int lowLimit;
    private static int highLimit;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Input low limit range");
        lowLimit = scanner.nextInt();

        System.out.println("Input high limit range");
        highLimit = scanner.nextInt();

        System.out.println("Input threadCount");
        int threadCount = scanner.nextInt();

        for (int i = 0; i < threadCount; i++) {
            new Thread(new PrimeChecker()).start();
        }

    }

    private static boolean primeNumberCheck(int number) {
        for (int i = 2; i < Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static class PrimeChecker implements Runnable{
        @Override
        public void run() {
            while (counter <= highLimit) {
                int numberToCheck = counter++;
                if (primeNumberCheck(numberToCheck)) {
                    primeNumbers.add(numberToCheck);
                }
            }

        }
    }
}

