package com.softserve.travelagency.dao.impl;

import com.softserve.travelagency.dao.RoomDAO;
import com.softserve.travelagency.model.Order;
import com.softserve.travelagency.model.Room;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
@AllArgsConstructor
public class RoomDAOImpl implements RoomDAO {

    private final SessionFactory sessionFactory;

    @Override
    public void create(Room room) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
//        try {
        session.save(room);
//        } catch (NullPointerException e){
//            e.getMessage();
//        } finally {
        transaction.commit();
//        }
    }

    @Override
    public Room readById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            return session.find(Room.class, id);
        } catch (NullPointerException e) {
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
            Room room = session.find(Room.class, id);
            session.delete(room);
        } catch (NullPointerException e) {
            e.getMessage();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Room> getRoomsByHotelId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("select * from rooms R where R.hotel_id = :id", Room.class);
            query.setParameter("id", id);
            return query.getResultList();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Room> getRoomsByNumber(int numb) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createNativeQuery("FROM rooms R where R.number = :numb", Room.class);
            return query.getResultList();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Room> getAllRooms() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("from Room ");
            return (List<Room>) query.getResultList();
        } catch (NullPointerException e) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }

    @Override
    public boolean isRoomAvailable(Long id, LocalDate arrival, LocalDate departure) {
        return readById(id).getOrders().stream().noneMatch(order -> arrival.isBefore(order.getArrivalDate()) && departure.isBefore(order.getArrivalDate())
                                                                    || arrival.isAfter(order.getDepartureDate()) && departure.isAfter(order.getDepartureDate()));
    }

    @Override
    public List<Room> getAvailableRoomsInHotelAtCertainDate(LocalDate arrivalDate,
                                        LocalDate departureDate, Long id) {

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            @SuppressWarnings("unchecked")
            Query query = session.createQuery("FROM Room R " +
                                              "WHERE R.hotelinroom.id = :id " +
                                              "AND R.id NOT IN " +
                                              "(SELECT O.room.id FROM Order O " +
                                              "WHERE :arrivalDate BETWEEN O.arrivalDate AND O.departureDate " +
                                              "OR :departureDate BETWEEN O.arrivalDate AND O.departureDate " +
                                              "OR O.arrivalDate BETWEEN :arrivalDate AND :departureDate " +
                                              "OR O.departureDate BETWEEN :arrivalDate AND :departureDate)", Room.class);
            query.setParameter("id", id);
            query.setParameter("arrivalDate", arrivalDate);
            query.setParameter("departureDate", departureDate);

            return (List<Room>) query.getResultList();
        } catch (NullPointerException npe) {
            return new ArrayList<>();
        } finally {
            transaction.commit();
        }
    }
}