package com.Hr.Market.dto;

import java.util.List;

public class CreateOrderRequest {

    private  Long user_id ;
    List <OrderItemRequest> orderItemRequests  ;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(List<OrderItemRequest> orderItemRequests, Long user_id) {
        this.orderItemRequests = orderItemRequests;
        this.user_id = user_id;
    }

    public List<OrderItemRequest> getOrderItemRequests() {
        return orderItemRequests;
    }

    public void setOrderItemRequests(List<OrderItemRequest> orderItemRequests) {
        this.orderItemRequests = orderItemRequests;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
