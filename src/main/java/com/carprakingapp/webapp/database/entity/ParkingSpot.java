package com.carprakingapp.webapp.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "parking_spots")
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parking_spot_id")
    private Integer parkingSpotId;

    //---------One Parking-Level can have Many Parking-Spots-------------
     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "parking_level_id", nullable = false, insertable = false, updatable = false)
     private ParkingLevel parkingLevel;

    @Column(name = "parking_level_id")
    private Integer parkingLevelId;
    //-------------------------------------------------------------------

    // ---------One Parking-Level can have One Bookings---------------------------------
    @OneToMany(mappedBy = "parkingSpot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookingsOfParkingSpot;
    //-----------------------------------------------------------------------------------



    @Column(name = "parking_spot_name")
    private String parkingSpotName;

    @Column(name = "disable",columnDefinition = "TINYINT")
    private Boolean disable;

}
