package com.Hr.Market.Repository;

import com.Hr.Market.Entity.Order_Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<Order_Items ,Long> {
}
