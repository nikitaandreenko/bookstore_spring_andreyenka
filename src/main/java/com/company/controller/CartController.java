package com.company.controller;

import com.company.service.BookService;
import com.company.service.dto.BookDto;
import com.company.service.dto.CartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final BookService bookService;

    @GetMapping(value = "cart")
    public String index() {
        return "cart/cart";
    }

    @GetMapping(value = "buy/{id}")
    public String buy(@PathVariable("id") Long id, HttpSession session) {
        BookDto bookDto = bookService.findById(id);
        if(session.getAttribute("cart") == null){
            List<CartDto>cart = new ArrayList<>();
            cart.add(new CartDto(bookDto, 1));
            session.setAttribute("cart", cart);
        }
        else {
            List<CartDto> cart = (List<CartDto>) session.getAttribute("cart");
            int index = this.exists(id, cart);
            if(index == -1){
                cart.add(new CartDto(bookDto, 1));

            }else {
                int quantity = cart.get(index).getQuantity()+1;
                cart.get(index).setQuantity(quantity);
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/books/getAll";
    }
    @PostMapping(value = "remove/{index}")
    public String remove(@PathVariable("index") int index, HttpSession session) {
        List<CartDto> cart = (List<CartDto>) session.getAttribute("cart");
        if (cart.get(index).getQuantity() == 1){
            cart.remove(index);
        }
        else {
            cart.get(index).setQuantity(cart.get(index).getQuantity() - 1);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/cart";
    }
    private int exists(Long id, List<CartDto> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (Objects.equals(cart.get(i).getBookDto().getId(), id)) {
                return i;
            }
        }
        return -1;
    }
}
