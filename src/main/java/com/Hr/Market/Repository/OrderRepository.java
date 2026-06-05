package com.Hr.Market.Repository;

import com.Hr.Market.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders ,Long> {
}
