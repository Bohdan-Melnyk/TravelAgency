package com.softserve.travelagency.service.impl;

import com.softserve.travelagency.dao.HotelDAO;
import com.softserve.travelagency.model.Hotel;
import com.softserve.travelagency.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelDAO hotelDAO;

    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO){
        this.hotelDAO = hotelDAO;
    }

    @Override
    public void create(Hotel hotel) {
        if (hotel != null) {
            hotelDAO.create(hotel);
        } else throw new NullPointerException("Object is empty");
    }

    @Override
    public Hotel readById(Long id) {
        Hotel hotel = hotelDAO.readById(id);
        if (hotel != null) {
            return hotel;
        } else throw new NullPointerException("Object is empty");
    }

    @Override
    public void delete(Long id) {
        Hotel hotel = hotelDAO.readById(id);
        if (hotel != null) {
            hotelDAO.delete(hotel.getId());
        } else throw new NullPointerException("Object is empty");
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDAO.getAllHotels();
    }

    @Override
    public Hotel getHotelByName(String name) {
        Hotel hotel = hotelDAO.getHotelByName(name);
        if (hotel != null){
            return hotel;
        } else throw new NullPointerException("Object is empty");
    }

    @Override
    public List<Hotel> getHotelsByCountry(String country) {
        return getHotelsByCountry(country);
    }

    @Override
    public List<String> getAllCountries() {
        return hotelDAO.getAllCountries();
    }
}
