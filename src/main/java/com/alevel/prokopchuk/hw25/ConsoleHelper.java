package com.alevel.prokopchuk.hw25;

import com.alevel.prokopchuk.hw25.models.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        System.out.println("Chose dishes what you want");
        System.out.println("List dishes");
        List<Dish> menu;
        List<Dish> dishes = new ArrayList<>();
        String dishName;
        while (!(dishName = readString()).equals("exit")) {

        }
        return dishes;
    }
}
