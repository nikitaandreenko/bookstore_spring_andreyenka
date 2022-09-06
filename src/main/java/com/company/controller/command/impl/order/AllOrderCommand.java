package com.company.controller.command.impl.order;

import com.company.controller.command.Command;
import com.company.service.OrderService;
import com.company.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("all_orders")
public class AllOrderCommand implements Command {

    private OrderService orderService;

    @Autowired
    public AllOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<OrderDto> orders = orderService.findAll();
        req.setAttribute("all_orders", orders);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/order/all_orders.jsp";
    }
}
