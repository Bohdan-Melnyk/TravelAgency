package com.softserve.travelagency.dao.impl;

import com.softserve.travelagency.dao.RoomDAO;
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
            Query query = session.createNativeQuery("select * from rooms R where R.hotel_id =:id", Room.class);
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
            Query query = session.createNativeQuery("FROM rooms R where R.number =: numb",Room.class);
            return  query.getResultList();
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
        } catch (NullPointerException e){
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
    public List<Room> getAvailableRoomsInHotelAtCertainDate(LocalDate arrival, LocalDate departure, Long hotelId) {
        var collect = getRoomsByHotelId(hotelId).stream()
                .map(room -> room.getOrders())
                .flatMap(Collection::stream)
                .filter(order -> isRoomAvailable(order.getRoom().getId(), arrival, departure))
                .map(order -> order.getRoom())
                .distinct()
                .map(room -> room.getNumber())
                .collect(Collectors.toSet());
        List<Room> list = getRoomsByHotelId(hotelId);
        for (int i = 0; i < list.size(); i++) {
            if(collect.contains(list.get(i).getNumber())){
                list.remove(i);
            }
        }
        return list;
    }

}
