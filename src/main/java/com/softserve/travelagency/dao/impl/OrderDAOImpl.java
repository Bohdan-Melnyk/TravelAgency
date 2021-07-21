package com.softserve.travelagency.dao.impl;

import com.softserve.travelagency.dao.OrderDAO;
import com.softserve.travelagency.model.Order;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class OrderDAOImpl implements OrderDAO {

    private final SessionFactory sessionFactory;


    @Override
    public void create(Order order) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        try {
            session.save(order);
//        } catch (NullPointerException e){
//            e.getMessage();
//        } finally {
            transaction.commit();
//        }
    }

    @Override
    public Order readById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order = session.find(Order.class, id);
            return order;
        } catch (NullPointerException e){
            return null;
        } finally {
            transaction.commit();
        }

    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Order order = session.find(Order.class, id);
            session.delete(order);
        } catch (NullPointerException e){
            e.getMessage();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("from orders");
            return query.getResultList();
        } catch (NullPointerException e){
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try{
            Query query = session.createQuery("from Order O where O.owner.id =: id");
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NullPointerException e){
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }
}
