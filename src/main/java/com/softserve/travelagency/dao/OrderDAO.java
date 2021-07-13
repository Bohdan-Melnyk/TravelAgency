package com.softserve.travelagency.dao;

import com.softserve.travelagency.model.Order;

import java.util.List;

public interface OrderDAO {
    void create(Order order);
    Order readById(Long id);
    void delete(Long id);
    List<Order> getAllOrders();
    List<Order> getAllOrdersByUserId(Long id);
}
