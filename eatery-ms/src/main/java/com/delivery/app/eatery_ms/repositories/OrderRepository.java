package com.delivery.app.eatery_ms.repositories;

import com.delivery.app.eatery_ms.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
