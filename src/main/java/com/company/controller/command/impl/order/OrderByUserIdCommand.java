package com.company.controller.command.impl.order;

import com.company.controller.command.Command;
import com.company.entity.Order;
import com.company.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("order_by_user_id")
public class OrderByUserIdCommand implements Command {
    private OrderService orderService;

    @Autowired
    public OrderByUserIdCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        String idRaw = req.getParameter("user_id");
        Long id = Long.parseLong(idRaw);
        Order order = orderService.findById(id);
        List<Order> orders = orderService.findByUserId(order.getId());
        req.setAttribute("all_orders_by_order_id", orders);
        req.setAttribute("message", "bookstore by Andreyenka");
        return "jsp/order/all_orders_by_order_id.jsp";
    }
}
