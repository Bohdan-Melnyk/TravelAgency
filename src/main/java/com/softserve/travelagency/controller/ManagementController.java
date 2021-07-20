package com.softserve.travelagency.controller;

import com.softserve.travelagency.model.Hotel;
import com.softserve.travelagency.model.Order;
import com.softserve.travelagency.model.Room;
import com.softserve.travelagency.model.User;
import com.softserve.travelagency.service.HotelService;
import com.softserve.travelagency.service.OrderService;
import com.softserve.travelagency.service.RoomService;
import com.softserve.travelagency.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/management")
@AllArgsConstructor
@NoArgsConstructor
public class ManagementController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/addUser")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addUserPost(@ModelAttribute("user") User user, BindingResult bindingResult) {

        userService.create(user);
        return "hello-world";
    }

    @GetMapping("/addHotel")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addHotel(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "new-hotel";
    }

    @PostMapping("/addHotel")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addHotelPost(@Valid @ModelAttribute("hotel") Hotel hotel, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            hotelService.create(hotel);
            return "hello-world";
        } else {
            model.addAttribute("error", bindingResult.getAllErrors());
        }
        return "new-hotel";
    }

    @GetMapping("/addRoom")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "add-room";
    }

    @PostMapping("/addRoom")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String addRoomPost(@RequestParam("hotelName") String hotelName,
                              @Valid @ModelAttribute("room") Room room, BindingResult bindingResult,
                              Model model) {
        if (!bindingResult.hasErrors()) {
            Hotel hotel = hotelService.getHotelByName(hotelName);
            room.setHotelinroom(hotel);
            roomService.create(room);
            return "hello-world";
        } else {
            model.addAttribute("hotels", hotelService.getAllHotels());
            return "add-room";
        }
    }



    @GetMapping("/order/{userId}")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String allUserOrders(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByUserId(userId));
        model.addAttribute("user", userService.readById(userId));
        return "orders";
    }
}




