package com.softserve.travelagency.dao.impl;


import com.softserve.travelagency.dao.HotelDAO;
import com.softserve.travelagency.model.Hotel;
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
public class HotelDAOImpl implements HotelDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        try {
        session.save(hotel);
//        } catch (NullPointerException e){
//            e.getMessage();
//        } finally {
        transaction.commit();
//        }
    }

    @Override
    public Hotel readById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Hotel hotel = session.find(Hotel.class, id);
            return hotel;
        } catch (NullPointerException e){
            e.getMessage();
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
            Hotel hotel = session.find(Hotel.class, id);
            session.delete(hotel);
        } catch (NullPointerException e){
            e.getMessage();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Hotel> getAllHotels() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from Hotel ");
            return query.getResultList();
        } catch (NullPointerException e){
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }


    @Override
    public Hotel getHotelByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from Hotel U where U.name  =:name");
            query.setParameter("name", name);
            return (Hotel) query.getSingleResult();
        } catch (NullPointerException e){
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Hotel> getHotelsByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from Hotel H where lower(H.country) like lower(:country)");
            query.setParameter("country", country+"%");
            return query.getResultList();
        } catch (NullPointerException e){
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<String> getAllCountries() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("SELECT country from Hotel ");
            return query.getResultList();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }
}
