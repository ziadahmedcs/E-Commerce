package com.Hr.Market.service;
import com.Hr.Market.Entity.Order_Items;
import com.Hr.Market.Entity.Orders;
import com.Hr.Market.Entity.Products;
import com.Hr.Market.Entity.Users;
import com.Hr.Market.Repository.OrderItemRepository;
import com.Hr.Market.Repository.OrderRepository;
import com.Hr.Market.Repository.ProductRepository;
import com.Hr.Market.Repository.UserRepository;
import com.Hr.Market.dto.CreateOrderRequest;
import com.Hr.Market.dto.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
     private OrderRepository orderRepository ;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private ProductRepository productRepository ;
    @Transactional
    public Orders CreateOrder (CreateOrderRequest createOrderRequest)
    {
        if (!userRepository.existsById(createOrderRequest.getUser_id()))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND ,"User Not Found") ;
        }
        Users users = userRepository.findById(createOrderRequest.getUser_id()).get() ;
        Orders orders = new Orders() ;
        orders.setUsers(users);
        orders.setOrder_date(LocalDateTime.now());
        orders.setStatus(Orders.OrderStatus.PENDING);
        orders.setTotal_price(BigDecimal.ZERO);
        Orders savedOrder = orderRepository.save(orders);
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItemRequest> orderItems = createOrderRequest.getOrderItemRequests();
        for (int i = 0; i<orderItems.size();i++)
        {
         if (!productRepository.existsById(orderItems.get(i).getProduct_id()))
         {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND , "The Product Not Found") ;
         }
         else
         {
             Products products = productRepository.findById(orderItems.get(i).getProduct_id()).get();
             if (products.getQuantity()>=orderItems.get(i).getQuantity())
             {
                 BigDecimal itemPrice =
                         products.getPrice()
                                 .multiply(
                                         BigDecimal.valueOf(
                                                 orderItems.get(i).getQuantity()));
                 Order_Items orderItemm = new Order_Items() ;

                 orderItemm.setOrders(savedOrder);
                 orderItemm.setProducts(products);
                 orderItemm.setQuantity(orderItems.get(i).getQuantity());
                 orderItemm.setPrice(itemPrice);
                 orderItemRepository.save(orderItemm) ;
                 products.setQuantity(
                         products.getQuantity()
                                 - orderItems.get(i).getQuantity());
                 productRepository.save(products);
                 totalPrice = totalPrice.add(itemPrice);

             }
             else
             {
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "The Amount is Greater Than Value") ;
             }

         }
        }
        savedOrder.setTotal_price(totalPrice);
        return orderRepository.save(savedOrder) ;
    }
}
