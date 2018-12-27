package com.alevel.prokopchuk.hw25;

import com.alevel.prokopchuk.hw25.dao.DishDao;
import com.alevel.prokopchuk.hw25.models.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {

    private static final BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message){
        System.out.println(message);
    }
    private ConsoleHelper(){}

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        System.out.println("Chose dishes what you want");
        System.out.println("List dishes");
        DishDao dao = DishDao.getInstance();
        List<Dish> menu = dao.findAll();
        menu.forEach(System.out :: println);
        List<Dish> dishes = new ArrayList<>();
        String dishIdString;
        Dish dish;
        int dishId;
        while (!(dishIdString = readString()).equals("exit")) {
            try {
                dishId = Integer.parseInt(dishIdString);
            } catch (NumberFormatException e) {
                System.out.println("You write id in incorrect format");
                continue;
            }
            dish = dao.getById(dishId);
            if (dish != null) {
                dishes.add(dish);
            } else {
                writeMessage("You enter incorrect id \r"
                        + "Please try again\r"
                + "or enter \"exit\".");
            }
        }
        return dishes;
    }


}
