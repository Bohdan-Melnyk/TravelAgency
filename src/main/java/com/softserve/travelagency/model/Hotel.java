package com.softserve.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Invalid country name")
    private String country;

    @Column
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Invalid city name")
    private String city;

    @Size(min = 3, max = 120, message = "Invalid hotel name")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "hotelinroom", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotelinorder", cascade = CascadeType.ALL)
    private List<Order> orders;

}
