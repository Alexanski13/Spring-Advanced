package bg.softuni.events.web;

import bg.softuni.events.model.OrderDTO;
import bg.softuni.events.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders/dummy/create")
    void createDummyOrder() {
        OrderDTO dummyOrderDTO = new OrderDTO();

        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            dummyOrderDTO.addProductID(random.nextLong(100));
        }

        orderService.createOrder(dummyOrderDTO);
    }
}
