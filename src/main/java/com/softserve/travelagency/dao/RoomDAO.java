package com.softserve.travelagency.dao;

import com.softserve.travelagency.model.Room;

import java.util.List;

public interface RoomDAO {
    void create(Room room);
    Room readById(Long id);
    void delete(Long id);
    List<Room> getRoomsByHotelId(Long id);
    List<Room> getRoomsByNumber(int numb);
    List<Room> getAllRooms();
}
