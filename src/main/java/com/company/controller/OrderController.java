package com.company.controller;

import com.company.service.OrderService;
import com.company.service.dto.OrderDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("order", order);
        return "order/order";
    }

    @GetMapping("/getAll")
    public String getOrders(Model model) {
        List<OrderDto> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        model.addAttribute("message", "bookstore by Andreyenka");
        return "order/orders";
    }

    @GetMapping("/{user_id}")
    public String getOrderByUser(@PathVariable Long user_id, Model model) {
        OrderDto order = orderService.findById(user_id);
        List<OrderDto> orders = orderService.findByUserId(order.getId());
        model.addAttribute("message", "bookstore by Andreyenka");
        model.addAttribute("all_orders_by_order_id", orders);
        return "all_orders_by_order_id";
    }
}
