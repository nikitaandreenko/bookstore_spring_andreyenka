package com.company.controller.command.impl.order;

import com.company.controller.command.Command;
import com.company.service.OrderService;
import com.company.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

@Controller("order")
public class OrderCommand implements Command {

    private OrderService orderService;

    @Autowired
    public OrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String idRaw = req.getParameter("id");
        Long id = Long.parseLong(idRaw);
        OrderDto order = orderService.findById(id);
        req.setAttribute("order", order);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/order/order.jsp";
    }
}
