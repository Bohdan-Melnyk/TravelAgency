package com.softserve.travelagency.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @Column(name = "prise")
    private double prise;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotelinroom;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Order> orders;

}