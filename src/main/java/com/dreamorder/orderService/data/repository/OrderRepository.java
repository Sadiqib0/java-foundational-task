package com.dreamorder.orderService.data.repository;

import com.dreamorder.orderService.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findByIdempotencyKey(String idempotencyKey);

    List<Order> findByBuyerIdOrderByCreatedAtDesc(String buyerId);

    Optional<Order> findByIdAndBuyerId(String id, String buyerId);

    List<Order> findDistinctByItemsProductIdInOrderByCreatedAtDesc(Collection<String> productIds);
}
