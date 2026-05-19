package com.dreamorder.orderService.controller;

import com.dreamorder.orderService.dto.request.PlaceOrderItemRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderShippingAddressRequest;
import com.dreamorder.orderService.dto.response.OrderStatusResponse;
import com.dreamorder.orderService.dto.response.OrderItemResponse;
import com.dreamorder.orderService.dto.response.PlaceOrderResponse;
import com.dreamorder.orderService.enums.OrderStatus;
import com.dreamorder.orderService.exception.CancellationWindowExpiredException;
import com.dreamorder.orderService.exception.IdempotencyKeyRequiredException;
import com.dreamorder.orderService.exception.InsufficientStockException;
import com.dreamorder.orderService.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({OrderController.class, HealthController.class})
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private PlaceOrderRequest buildRequest() {
        PlaceOrderItemRequest item = new PlaceOrderItemRequest();
        item.setProductId("prod-abc");
        item.setQuantity(2);
        item.setUnitPrice(45000L);

        PlaceOrderShippingAddressRequest address = new PlaceOrderShippingAddressRequest();
        address.setStreet("10 Broad Street");
        address.setCity("Lagos");
        address.setState("Lagos");
        address.setCountry("NG");

        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setItems(List.of(item));
        request.setShippingAddress(address);
        request.setPaymentMethod("WALLET");
        return request;
    }

    private PlaceOrderResponse buildResponse() {
        return PlaceOrderResponse.builder()
                .id("order-uuid-123")
                .buyerId("buyer-123")
                .status(OrderStatus.RESERVED)
                .totalAmount(90000L)
                .currency("NGN")
                .paymentMethod("WALLET")
                .items(List.of(OrderItemResponse.builder()
                        .id("item-uuid-1")
                        .productId("prod-abc")
                        .quantity(2)
                        .unitPrice(45000L)
                        .build()))
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("POST /orders - valid request with X-User-Id → 201 with RESERVED order")
    void placeOrder_validRequest_returns201() throws Exception {
        when(orderService.placeOrder(eq("buyer-123"), eq("idem-123"), any())).thenReturn(buildResponse());

        mockMvc.perform(post("/orders")
                        .header("X-User-Id", "buyer-123")
                        .header("X-Idempotency-Key", "idem-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildRequest())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("RESERVED"))
                .andExpect(jsonPath("$.buyerId").value("buyer-123"))
                .andExpect(jsonPath("$.totalAmount").value(90000));
    }

    @Test
    @DisplayName("POST /orders - missing X-User-Id header → 401")
    void placeOrder_missingUserId_returns401() throws Exception {
        mockMvc.perform(post("/orders")
                        .header("X-Idempotency-Key", "idem-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildRequest())))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("POST /orders - missing X-Idempotency-Key → 400")
    void placeOrder_missingIdempotencyKey_returns400() throws Exception {
        when(orderService.placeOrder(eq("buyer-123"), eq(null), any()))
                .thenThrow(new IdempotencyKeyRequiredException("X-Idempotency-Key header is required"));

        mockMvc.perform(post("/orders")
                        .header("X-User-Id", "buyer-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("IDEMPOTENCY_KEY_REQUIRED"));
    }

    @Test
    @DisplayName("POST /orders - inventory unavailable → 409 with INSUFFICIENT_STOCK error")
    void placeOrder_insufficientStock_returns409() throws Exception {
        when(orderService.placeOrder(any(), any(), any()))
                .thenThrow(new InsufficientStockException("Insufficient stock"));

        mockMvc.perform(post("/orders")
                        .header("X-User-Id", "buyer-123")
                        .header("X-Idempotency-Key", "idem-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildRequest())))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("INSUFFICIENT_STOCK"));
    }

    @Test
    @DisplayName("POST /orders - duplicate idempotency key → 200 with original order")
    void placeOrder_duplicateIdempotencyKey_returns200() throws Exception {
        when(orderService.idempotencyKeyExists("idem-123")).thenReturn(true);
        when(orderService.placeOrder(eq("buyer-123"), eq("idem-123"), any())).thenReturn(buildResponse());

        mockMvc.perform(post("/orders")
                        .header("X-User-Id", "buyer-123")
                        .header("X-Idempotency-Key", "idem-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("order-uuid-123"));
    }

    @Test
    @DisplayName("GET /orders - authenticated user → 200 with own orders")
    void listOrders_authenticatedUser_returnsOrders() throws Exception {
        when(orderService.listOrders("buyer-123")).thenReturn(List.of(buildResponse()));

        mockMvc.perform(get("/orders")
                        .header("X-User-Id", "buyer-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("order-uuid-123"));
    }

    @Test
    @DisplayName("GET /orders/{id}/status - authenticated user → 200 with status")
    void getOrderStatus_authenticatedUser_returnsStatus() throws Exception {
        when(orderService.getOrderStatus("buyer-123", "order-uuid-123"))
                .thenReturn(OrderStatusResponse.builder()
                        .orderId("order-uuid-123")
                        .status(OrderStatus.RESERVED)
                        .build());

        mockMvc.perform(get("/orders/order-uuid-123/status")
                        .header("X-User-Id", "buyer-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("RESERVED"));
    }

    @Test
    @DisplayName("DELETE /orders/{id} - cancellation window expired → 409")
    void cancelOrder_windowExpired_returns409() throws Exception {
        when(orderService.cancelOrder("buyer-123", "order-uuid-123"))
                .thenThrow(new CancellationWindowExpiredException("Cancellation window has expired"));

        mockMvc.perform(delete("/orders/order-uuid-123")
                        .header("X-User-Id", "buyer-123"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("CANCELLATION_WINDOW_EXPIRED"));
    }

    @Test
    @DisplayName("GET /actuator/health → 200 UP")
    void health_returnsUp() throws Exception {
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
