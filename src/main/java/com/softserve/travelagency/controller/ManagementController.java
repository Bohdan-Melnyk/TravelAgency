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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/management")
@AllArgsConstructor
@NoArgsConstructor
public class ManagementController {

    private OrderService orderService;

    private UserService userService;

    private HotelService hotelService;

    private RoomService roomService;


    @GetMapping("/addUser")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/addUser")
    public String addUserPost(@ModelAttribute("user") User user){
        userService.create(user);
        return "hello-world";
    }

    @GetMapping("/addHotel")
    public String addHotel(Model model){
        model.addAttribute("hotel", new Hotel());
        return "new-hotel";
    }

    @PostMapping("/addHotel")
    public String addHotelPost(@ModelAttribute("hotel") Hotel hotel){
        hotelService.create(hotel);
        return "new-hotel";
    }

    @GetMapping("/addRoom")
    public String addRoom(Model model){
        model.addAttribute("room", new Room());
//        if (hotelService.getAllHotels().isEmpty()){
//            return "redirect:new-hotel";
//        }
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "add-room";
    }

    @PostMapping("/addRoom")
    public String addRoomPost(@RequestParam("hotelName") String hotelName,
                              @ModelAttribute("room") Room room,
                              Model model){
        Hotel hotel = hotelService.getHotelByName(hotelName);
        room.setHotelinroom(hotel);
        roomService.create(room);
        return "hello-world";
    }

    @GetMapping("/addOrder/{userId}")
    public String addOrder(@PathVariable("userId") Long userId, Model model){
//        model.addAttribute("user", userService.readById(userId));
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("countries", hotelService.getAllCountries());
        model.addAttribute("order", new Order());
        return "new-order";
    }

    @PostMapping("/addOrder/{userId}")
    public String addOrerPost(@ModelAttribute("hotel") Hotel hotel,
                              @ModelAttribute("room") Room room,
                              @PathVariable("userId") Long id,
                              Model model){
        Order order = Order.builder().
                room(room).
                hotelinorder(hotel).
                owner(userService.readById(id)).
                build();
        orderService.create(order);
        return "hello-world";
    }

    @GetMapping("/order/{userId}")
    public String allUserOrders(@PathVariable Long userId, Model model){
        model.addAttribute("order", orderService.getAllOrdersByUserId(userId));
        return "orders";
    }
}




