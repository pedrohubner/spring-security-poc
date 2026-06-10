package br.com.pedrohubner.springsecuritypoc.controller.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderRestController {
    public OrderRestController() {
    }

    @GetMapping("/orders")
    public List<String> getOrderIds() {
        return List.of("1q2d", "86jdf0");
    }

    @GetMapping("/admin/orders")
    public List<String> getAdminOrderIds() {
        return List.of("1q2d3123123", "86jdf0123131");
    }
}
