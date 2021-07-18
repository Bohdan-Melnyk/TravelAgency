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
            model.addAttribute("numberError", bindingResult.getFieldError("number"));
            model.addAttribute("priceError", bindingResult.getFieldError("prise"));
            return "add-room";
        }
    }

    @GetMapping("/getHotels/{userId}")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String getHotels(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "get-hotels";
    }

    @GetMapping("/getRooms/{userId}/{hotelId}")
    @PreAuthorize("hasAuthority('developers:admin')")
    public String getHotelRooms(@PathVariable("userId") Long userId,@PathVariable("hotelId") Long hotelId, Model model) {
        model.addAttribute("rooms", roomService.getRoomsByHotelId(hotelId));
        model.addAttribute("userId", userId);
        return "new-order";
    }

    @GetMapping("/checkRooms/{userId}/{hotelId}")
    public String checkRooms(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                             @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                             @PathVariable("hotelId") Long hotelId,
                             @PathVariable("userId") Long userId,
                             Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("rooms", roomService.getAvailableRoomsInHotelAtCertainDate(arrivalDate, departureDate, hotelId));
        return "new-order";
    }

    @GetMapping("/addOrder/{userId}/{hotelId}")
    public String addOrder(@PathVariable("hotelId") Long hotelId, Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "new-order";
    }

    @PostMapping("/addOrder/{userId}/{hotelId}")
    public String addOrderPost(@RequestParam("arrivalDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate arrivalDate,
                               @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
                               @RequestParam("roomId") Long roomId,
                               @PathVariable("userId") Long id,
                               @PathVariable("hotelId") Long hotelId,
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
    @PreAuthorize("hasAuthority('developers:admin')")
    public String allUserOrders(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByUserId(userId));
        model.addAttribute("user", userService.readById(userId));
        return "orders";
    }
}




