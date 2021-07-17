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
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/addUser")
    public String addUserPost(@ModelAttribute("user") User user, BindingResult bindingResult) {

        userService.create(user);
        return "hello-world";
    }

    @GetMapping("/addHotel")
    public String addHotel(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "new-hotel";
    }

    @PostMapping("/addHotel")
    public String addHotelPost(@Valid @ModelAttribute("hotel") Hotel hotel, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            hotelService.create(hotel);
            return "hello-world";
        } else {
            if (bindingResult.hasFieldErrors("name")) {
                model.addAttribute("error", bindingResult.getFieldError("name"));
            }
            if (bindingResult.hasFieldErrors("city")) {
                model.addAttribute("cityError", bindingResult.getFieldError("city"));
            }
            if (bindingResult.hasFieldErrors("country")) {
                model.addAttribute("countryError", bindingResult.getFieldError("country"));
            }
        }
        return "new-hotel";
    }

    @GetMapping("/addRoom")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "add-room";
    }

    @PostMapping("/addRoom")
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
            model.addAttribute("numberError", bindingResult.getFieldError("number"));
            model.addAttribute("priceError", bindingResult.getFieldError("prise"));
            return "add-room";
        }
    }

    @GetMapping("/addOrder/{userId}")
    public String addOrder(@PathVariable("userId") Long userId,
//                           @RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
//                           @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                           Model model) {
//        model.addAttribute("user", userService.readById(userId));
        model.addAttribute("rooms", roomService.getAllRooms());
//        model.addAttribute("arrivalDate", arrivalDate);
//        model.addAttribute("departureDate", departureDate);
        return "new-order";
    }

    @Validated
    @PostMapping("/addOrder/{userId}")
    public String addOrderPost(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                              @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                              @RequestParam("roomId") Long roomId,
                              @PathVariable("userId") Long id,
                              Model model) {
        if (departureDate == null || arrivalDate == null || arrivalDate.compareTo(departureDate) >= 0 || arrivalDate.compareTo(LocalDate.now()) < 0 || departureDate.compareTo(LocalDate.now()) < 0) {
            model.addAttribute("error", "Invalid departure or arrival date");
            model.addAttribute("rooms", roomService.getAllRooms());
            return "new-order";
        } else {
            Room room = roomService.readById(roomId);
            Order order = Order.builder().
                    room(room).
                    hotelinorder(room.getHotelinroom()).
                    owner(userService.readById(id)).
                    arrivalDate(arrivalDate).
                    departureDate(departureDate).
                    orderDate(LocalDateTime.now()).
                    build();
            orderService.create(order);
            return "hello-world";
        }

    }

    @GetMapping("/order/{userId}")
    public String allUserOrders(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByUserId(userId));
        model.addAttribute("user", userService.readById(userId));
        return "orders";
    }
}




