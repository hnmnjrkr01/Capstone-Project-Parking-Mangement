package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.Booking;
import com.carprakingapp.webapp.database.entity.PaymentMethodEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<Booking, Long> {

    Booking findByBookingId(Integer bookingId);

    List<Booking> findByUserId(Integer userId);

    Booking findBylicensePlateNumber(String licensePlateNumber);

    List<Booking> findByStartDateTime(LocalDateTime startDateTime);

    List<Booking> findByParkingSpotId(Integer parkingSpotId);

    @Query(value = "select pm.payment_method from payment_method pm, bookings b, users u " +
                    "where pm.payment_id = b.payment_method_id " +
                    "and b.user_id= u.id " +
                    "and u.id=:userId;" , nativeQuery = true)
    List<String> paymentMethods(Integer userId);

    //Read all the active bookings for a LevelId
    @Query(value =  " select b.* from parking_levels pl, bookings b, parking_spots ps " +
                    " where b.parking_spot_id = ps.parking_spot_id " +
                    " and ps.parking_level_id = pl.level_id " +
                    " and pl.level_id= :levelId " +
                    " and b.end_date_time > CURDATE();" ,nativeQuery = true)
      List<Booking> findByLevelId(Integer levelId);

}
