package com.microservice.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.orderservice.dto.OrderLineItemsDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.model.Order;
import com.microservice.orderservice.model.OrderLineItems;
import com.microservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
//        List<Order> orders = orderRepository.findAll();
//
//        return orders.stream().map(this::mapToOrderResponse)
//                .collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Order order) {

        List<OrderLineItems> orderLineItemsList = order.getOrderLineItemsList();

        ObjectMapper mapper = new ObjectMapper();

        List<OrderLineItemsDto> lineItemsDtos = orderLineItemsList.stream().map(i -> mapper.convertValue(i, OrderLineItemsDto.class))
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderLineItemsDtoList(lineItemsDtos)
                .build();
    }
}
