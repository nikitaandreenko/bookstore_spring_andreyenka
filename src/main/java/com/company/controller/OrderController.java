package com.company.controller;

import com.company.service.OrderService;
import com.company.service.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class  OrderController {
    private final OrderService orderService;
    ObjectMapperService mapper;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/order";
    }

    @GetMapping("/getAll")
    public String getOrders(Model model) {
        List<OrderDto> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/orders";
    }

    @GetMapping("/order/{user_id}")
    public String getOrderByUser(@PathVariable Long user_id, Model model) {
        OrderDto order = orderService.findById(user_id);
        List<OrderDto> orders = orderService.findByUserId(order.getId());
        model.addAttribute("all_orders_by_order_id", orders);
        return "order/all_orders_by_order_id";
    }

    @PostMapping("/create")
    public String createOrder(HttpSession session) {
        List<CartDto> cart = (List<CartDto>) session.getAttribute("cart");
        UserDto userDto = (UserDto) session.getAttribute("user");
        orderService.createOrderNew(cart, userDto);
        return "redirect:/orders/order/" + userDto.getId();
    }
}
