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
    @NotBlank(message = "Invalid country name")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Invalid country name")
    private String country;

    @Column
    @NotBlank(message = "Invalid city name")
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$", message = "Invalid city name")
    private String city;

    @NotBlank(message = "Invalid name")
    @Size(min = 3, max = 120, message = "Invalid hotel name")
    @Pattern(regexp = "^[\\w.,():'& -]+$", message = "Invalid hotel name")
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "hotelinroom", cascade = CascadeType.ALL)
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotelinorder", cascade = CascadeType.ALL)
    private List<Order> orders;

}
