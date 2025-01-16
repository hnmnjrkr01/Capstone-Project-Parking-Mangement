package com.carprakingapp.webapp.database.entity;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="booking_id")
    private Integer bookingId;

    //---------One User can have Many Bookings----------

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private User user;

    @Column(name="user_id")
    private Integer userId;
    //--------------------------------------------------


    //---------One Payment-Method can have Many Bookings-----------
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment_method_id", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private PaymentMethod paymentMethod;

    @Column(name="payment_method_id")
    private Integer paymentMethodId;
    //-------------------------------------------------------------


   // ---------One Parking-Level can have Many Bookings-------------
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "parking_spot_id", nullable = false)
    private ParkingSpot parkingSpot;

    @Column(name="parking_spot_id", insertable = false, updatable = false)
    private Integer parkingSpotId;
    //--------------------------------------------------------------


    @Column(name="total_price", columnDefinition = "DECIMAL")
    private Double totalPrice;

    @Column(name="start_date_time")
    private LocalDateTime startDateTime;

    @Column(name="end_date_time")
    private LocalDateTime endDateTime;

    @Column(name="duration")
    private Integer duration;

    @Column(name="license_plate_no")
    private String licensePlateNumber;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "booking_date")
    private LocalDateTime bookingDate;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", paymentMethodId=" + paymentMethodId +
                ", parkingSpotId=" + parkingSpotId +
                ", totalPrice=" + totalPrice +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", duration=" + duration +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                '}';
    }
}
