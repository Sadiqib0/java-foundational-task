package com.dreamorder.orderService.controller;

import com.dreamorder.orderService.dto.request.PlaceOrderRequest;
import com.dreamorder.orderService.dto.request.UpdateOrderStatusRequest;
import com.dreamorder.orderService.dto.response.OrderStatusResponse;
import com.dreamorder.orderService.dto.response.PlaceOrderResponse;
import com.dreamorder.orderService.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            @RequestHeader(value = "X-User-Id", required = false) String buyerId,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey,
            @Valid @RequestBody PlaceOrderRequest request) {
        if (buyerId == null || buyerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        boolean duplicate = orderService.idempotencyKeyExists(idempotencyKey);
        PlaceOrderResponse response = orderService.placeOrder(buyerId, idempotencyKey, request);
        return ResponseEntity.status(duplicate ? HttpStatus.OK : HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PlaceOrderResponse>> listOrders(
            @RequestHeader(value = "X-User-Id", required = false) String buyerId) {
        if (buyerId == null || buyerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.listOrders(buyerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaceOrderResponse> getOrder(
            @RequestHeader(value = "X-User-Id", required = false) String buyerId,
            @PathVariable String id) {
        if (buyerId == null || buyerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.getOrder(buyerId, id));
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<OrderStatusResponse> getOrderStatus(
            @RequestHeader(value = "X-User-Id", required = false) String buyerId,
            @PathVariable String id) {
        if (buyerId == null || buyerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.getOrderStatus(buyerId, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlaceOrderResponse> cancelOrder(
            @RequestHeader(value = "X-User-Id", required = false) String buyerId,
            @PathVariable String id) {
        if (buyerId == null || buyerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.cancelOrder(buyerId, id));
    }

    @GetMapping("/seller")
    public ResponseEntity<List<PlaceOrderResponse>> listSellerOrders(
            @RequestHeader(value = "X-User-Id", required = false) String sellerId,
            @RequestHeader(value = "X-Seller-Product-Ids", required = false) String productIdsHeader) {
        if (sellerId == null || sellerId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<String> productIds = productIdsHeader == null || productIdsHeader.isBlank()
                ? List.of()
                : Arrays.stream(productIdsHeader.split(","))
                .map(String::trim)
                .filter(productId -> !productId.isBlank())
                .toList();
        return ResponseEntity.ok(orderService.listSellerOrders(productIds));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PlaceOrderResponse> updateStatus(
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-Admin", required = false, defaultValue = "false") boolean admin,
            @RequestHeader(value = "X-Internal-Request", required = false, defaultValue = "false") boolean internal,
            @PathVariable String id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(orderService.updateStatus(id, request.getStatus(), request.getReason(), admin || internal));
    }
}
