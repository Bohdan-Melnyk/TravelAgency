package com.softserve.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelinorder;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @NotNull
    @FutureOrPresent(message = "Invalid departure or arrival date")
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;

    @NotNull
    @Column(name = "departure_date")
    private LocalDate departureDate;

    @NotNull
    @Column(name = "order_date")
    private LocalDateTime orderDate;
}
