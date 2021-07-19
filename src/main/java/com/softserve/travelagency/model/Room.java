package com.softserve.travelagency.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Table(name = "rooms")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    @Min(value = 1, message = "Room number can not be 0")
    @Max(value = 10000, message = "Max allowed room number is 10000")
    private int number;

    @Min(value = 1, message = "Price can not be 0")
    @Max(value = 100000, message = "Max allowed price is 100000$")
    @Column(name = "prise")
    private double prise;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelinroom;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Order> orders;

}