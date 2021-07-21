package com.softserve.travelagency.dao.impl;

import com.softserve.travelagency.dao.UserDAO;
import com.softserve.travelagency.model.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
    }

    @Override
    public User readById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.find(User.class, id);
        } catch (NullPointerException e) {
            return  null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.find(User.class, id);
            session.delete(user);
        } catch (NullPointerException e) {
            e.getMessage();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("FROM User O WHERE O.email = :email", User.class);
            query.setParameter("email", email);
            User user = (User) query.uniqueResult();
            return Optional.of(user);
        } catch (NullPointerException npe) {
            return Optional.empty();
        } finally {
            transaction.commit();
        }
    }



    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("from User");
            return (List<User>) query.getResultList();
        } catch (NullPointerException npe) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

}
