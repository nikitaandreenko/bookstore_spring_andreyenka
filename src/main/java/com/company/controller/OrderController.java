package com.company.controller;

import com.company.service.OrderService;
import com.company.service.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    ObjectMapperService mapper;

    @GetMapping("/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/order";
    }

    @GetMapping("/getAll")
    public String getOrders(Model model, @RequestParam(required = false) Integer page) {
        if(page == null){
            page = 0;
        }else{
            page--;
        }
        Pageable pageable = PageRequest.of(page, 5, Sort.Direction.ASC, "id");
        Page <OrderDto> orders = orderService.findAll(pageable);
        model.addAttribute("orders", orders.toList());
        model.addAttribute("currentPage", page+1);
        model.addAttribute("totalPages", orders.getTotalPages());
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
        OrderDto dto = orderService.createOrderNew(cart, userDto);
        session.removeAttribute("cart");
        return "redirect:/orders/" + dto.getId();
    }

    @GetMapping("/update/{id}")
    public String updateOrderForm(@PathVariable Long id, Model model) {
        OrderDto order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order/updateOrderForm";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable Long id, @RequestParam String status, Model model) {
        OrderDto order = orderService.updateStatus(id, status);
        model.addAttribute(order);
        return "redirect:/orders/" + order.getId();
    }

}
