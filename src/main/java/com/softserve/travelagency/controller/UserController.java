package com.softserve.travelagency.controller;


import com.softserve.travelagency.model.Order;
import com.softserve.travelagency.model.Room;
import com.softserve.travelagency.service.OrderService;
import com.softserve.travelagency.service.RoomService;
import com.softserve.travelagency.service.UserService;
import lombok.AllArgsConstructor;
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

    private final UserService userService;

    private final RoomService roomService;

    private final OrderService orderService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('developers:user')")
    public String allUsers(Model model){
        model.addAttribute("myusers", userService.getAllUsers());
        return "users";
    }

//    @GetMapping("/{userId}")
//    public String userInfo(@PathVariable("userId") Long id, Model model){
//        model.addAttribute("user", userService.readById(id));
//        return "user-info";
//    }

    @GetMapping("/addOrder")
    @PreAuthorize("hasAuthority('developers:user')")
    public String addOrder(//@PathVariable("hotelId") Long hotelId,
                           @RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                           @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                           Model model) {
        model.addAttribute("rooms", roomService.getAvailableRoomsInHotelAtCertainDate(arrivalDate, departureDate, 1L));
        return "new-order";
    }

    @PostMapping("/addOrder")
    @PreAuthorize("hasAuthority('developers:user')")
    public String addOrderPost(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                               @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                               @RequestParam("roomId") Long roomId,
                               @RequestParam("hotelId") Long hotelId,
                               Principal principal,
                               Model model) {
        if (departureDate == null || arrivalDate == null || arrivalDate.compareTo(departureDate) >= 0
                || arrivalDate.compareTo(LocalDate.now()) < 0 || departureDate.compareTo(LocalDate.now()) < 0) {
            model.addAttribute("error", "Invalid departure or arrival date");
            model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
            return "new-order";
        }else if(!roomService.isRoomAvailableInCertainHotel(roomId, arrivalDate, departureDate)){
            model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
            model.addAttribute("dateError", "This date is already taken");
            return "new-order";
        }
        else {
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
            return "hello-world";
        }

    }
}
