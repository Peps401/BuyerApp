package hr.abysalto.hiring.api.junior.controller;

import hr.abysalto.hiring.api.junior.manager.OrderItemManager;
import hr.abysalto.hiring.api.junior.model.OrderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/orderitem")
@Controller
public class OrderItemController {

    @Autowired
    private OrderItemManager orderItemManager;

    @GetMapping("/add/{orderNr}")
    public String addItemForm(@PathVariable Long orderNr, Model model) {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderNr(orderNr);

        model.addAttribute("orderItem", orderItem);

        return "order/neworderitem";
    }

    @PostMapping("/save")
    public String saveItem(@ModelAttribute("orderItem") OrderItem orderItem) {

        orderItemManager.save(orderItem);

        return "redirect:/order/";
    }
}
