package com.softserve.travelagency.service.impl;

import com.softserve.travelagency.dao.RoomDAO;
import com.softserve.travelagency.model.Room;
import com.softserve.travelagency.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    @Override
    public void create(Room room) {
        if (room != null){
            roomDAO.create(room);
        } else throw new NullPointerException("Room is null");
    }

    @Override
    public Room readById(Long id) {
        Room room = roomDAO.readById(id);
        if (room != null) {
            return room;
        } else throw new NullPointerException("Object is empty");
    }

    @Override
    public void delete(Long id) {
        Room room = readById(id);
        if (room != null) {
            roomDAO.delete(room.getId());
        } else throw new NullPointerException("Room is null");
    }

    @Override
    public List<Room> getRoomsByHotelId(Long id) {
        return roomDAO.getRoomsByHotelId(id);
    }

    @Override
    public List<Room> getRoomsByNumber(int numb) {
        return roomDAO.getRoomsByNumber(numb);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    @Override
    public boolean isRoomAvailableInCertainHotel(Long id, LocalDate arrival, LocalDate departure) {
        return roomDAO.isRoomAvailable(id, arrival, departure);
    }


    @Override
    public List<Room> getAvailableRoomsInHotelAtCertainDate(LocalDate arrival, LocalDate departure, Long hotelId) {
        return roomDAO.getAvailableRoomsInHotelAtCertainDate(arrival, departure, hotelId);
    }
}
