package com.softserve.travelagency.controller;


import com.softserve.travelagency.model.Order;
import com.softserve.travelagency.model.Room;
import com.softserve.travelagency.service.HotelService;
import com.softserve.travelagency.service.OrderService;
import com.softserve.travelagency.service.RoomService;
import com.softserve.travelagency.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final RoomService roomService;

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final HotelService hotelService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('developers:user')")
    public String allUsers(Model model) {
        model.addAttribute("myusers", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/getHotels")
    @PreAuthorize("hasAuthority('developers:user')")
    public String getHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "get-hotels";
    }

    @GetMapping("/getRooms/{hotelId}")
    @PreAuthorize("hasAuthority('developers:user')")
    public String getHotelRooms(@PathVariable("hotelId") Long hotelId, Model model) {
        model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
        return "new-order";
    }




    @GetMapping("/checkRooms/{hotelId}")
    @PreAuthorize("hasAuthority('developers:user')")
    public String checkRooms(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                             @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                             @PathVariable("hotelId") Long hotelId,
                             Model model) {
        if (departureDate == null || arrivalDate == null || arrivalDate.compareTo(departureDate) >= 0
            || arrivalDate.compareTo(LocalDate.now()) < 0 || departureDate.compareTo(LocalDate.now()) < 0) {
            model.addAttribute("error", "Invalid departure or arrival date");
            model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
        } else {
            model.addAttribute("rooms", roomService.getAvailableRoomsInHotelAtCertainDate(arrivalDate, departureDate, hotelId));
        }
        return "new-order";
    }


    @PostMapping("/addOrder/{hotelId}")
    @PreAuthorize("hasAuthority('developers:user')")
    public String addOrderPost(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                               @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                               @RequestParam("roomId") Long roomId,
                               @PathVariable("hotelId") Long hotelId,
                               Model model, Principal principal) {
        if (departureDate == null || arrivalDate == null || arrivalDate.compareTo(departureDate) >= 0
            || arrivalDate.compareTo(LocalDate.now()) < 0 || departureDate.compareTo(LocalDate.now()) < 0) {
            model.addAttribute("error", "Invalid departure or arrival date");
            model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
            return "new-order";
        } else if (!roomService.isRoomAvailableInCertainHotel(roomId, arrivalDate, departureDate)) {
            model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
            model.addAttribute("dateError", "This date is already taken");
            return "new-order";
        } else {
            Room room = roomService.readById(roomId);
            Order order = Order.builder().
                    room(room).
                    hotelinorder(room.getHotelinroom()).
                    owner(userService.getUserByEmail(principal.getName())).
                    arrivalDate(arrivalDate).
                    departureDate(departureDate).
                    orderDate(LocalDateTime.now()).
                    build();
            orderService.create(order);
            return "success";
        }


    }
}

