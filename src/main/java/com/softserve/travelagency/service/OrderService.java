package com.softserve.travelagency.service;

import com.softserve.travelagency.model.Order;

import java.util.List;

public interface OrderService {

    void create(Order order);

    Order readById(Long id);

    void delete(Long id);

    List<Order> getAllOrders();

    List<Order> getAllOrdersByUserId(Long id);

}
