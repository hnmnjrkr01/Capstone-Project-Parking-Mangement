package com.carprakingapp.webapp.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="parking_levels")
public class ParkingLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id")
    private Integer levelId;

    //---------One Parking-Level can have Many Bookings-------------

    @OneToMany(mappedBy = "parkingLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Booking> bookings;

    //--------------------------------------------------------------

    //---------One Parking-Level can have Many Parking-Spots-------------

    @OneToMany(mappedBy = "parkingLevel", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ParkingSpot> prakingSpots;

    //---------------------------------------------------------------

    @Column(name = "level_code", columnDefinition = "Char")
    private String levelCode;

    @Column(name = "max_capacity")
    private int MaximumCapacity;

    @Override
    public String toString() {
        return "ParkingLevel{" +
                "levelId=" + levelId +
                ", levelCode=" + levelCode +
                ", MaximumCapacity=" + MaximumCapacity +
                '}';
    }
}
