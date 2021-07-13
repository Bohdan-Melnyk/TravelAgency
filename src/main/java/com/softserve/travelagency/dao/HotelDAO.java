package com.softserve.travelagency.dao;


import com.softserve.travelagency.model.Hotel;

import java.util.List;

public interface HotelDAO {

    void create(Hotel hotel);

    Hotel readById(Long id);

    void delete(Long id);

    List<Hotel> getAllHotels();

    Hotel getHotelByName(String name);

    List<Hotel> getHotelsByCountry(String country);

    List<String> getAllCountries();

}
