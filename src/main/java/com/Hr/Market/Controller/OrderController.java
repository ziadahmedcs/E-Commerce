package com.Hr.Market.Controller;

import com.Hr.Market.Entity.Orders;
import com.Hr.Market.dto.CreateOrderRequest;
import com.Hr.Market.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Order")
public class OrderController {

    @Autowired
    private  OrderService orderService ;
    @PostMapping("/orders")
    public Orders createOrder(
            @RequestBody CreateOrderRequest request)
    {
        return orderService.CreateOrder(request);
    }

}
