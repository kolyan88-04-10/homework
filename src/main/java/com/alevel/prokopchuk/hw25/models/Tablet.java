package com.alevel.prokopchuk.hw25.models;

import com.alevel.prokopchuk.hw25.ConsoleHelper;
import com.alevel.prokopchuk.hw25.dao.OrderDao;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class Tablet {
    @Id
    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        //Order order = new Order(this);
        Order order = new Order();
        order.setStatus(Status.OPENED);
        try {
            List<Dish> dishes = ConsoleHelper.getAllDishesForOrder();
            order.setDishes(dishes);
            OrderDao dao = OrderDao.getInstance();
            double bill = countBill(dishes);
            order.setBill(bill);
            ConsoleHelper.writeMessage("Your order is : ");
            ConsoleHelper.writeMessage(order.toString());
            dao.save(order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "console is unavailable");
        }
    }

    private double countBill(List<Dish> dishes) {
        double bill = dishes.stream().mapToDouble(d -> d.getCost()).sum();
        return bill;
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
