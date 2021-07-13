package com.softserve.travelagency.service.impl;

import com.softserve.travelagency.dao.OrderDAO;
import com.softserve.travelagency.model.Order;
import com.softserve.travelagency.service.OrderService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void create(Order order) {
        if (order != null) {
            orderDAO.create(order);
        } else throw new NullPointerException("Order is null");
    }

    @Override
    public Order readById(Long id) {
        Order order = readById(id);
        if (order != null) {
            return order;
        } else return null;
    }

    @Override
    public void delete(Long id) {
        Order order = readById(id);
        if (order != null) {
            orderDAO.delete(order.getId());
        } else throw new NullPointerException("Order is null");
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        return orderDAO.getAllOrdersByUserId(id);
    }
}
