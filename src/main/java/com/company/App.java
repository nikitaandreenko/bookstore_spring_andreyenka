package com.company;

import com.company.repository.impl.OrderRepositoryImpl;
import com.company.repository.entity.Order;

import java.util.List;

public class App {
    public static void main(String[] args) {
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        try {
            List <Order> orders= orderRepository.findAll();
            System.out.println(orders);
        } finally {orderRepository.close();
        }
    }
}
