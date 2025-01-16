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

    List<Booking> findByLevelId(Integer levelId);

    @Query(value = "select pm.payment_method from payment_method pm, bookings b, users u " +
                    "where pm.payment_id = b.payment_method_id " +
                    "and b.user_id= u.id " +
                    "and u.id=:userId;" , nativeQuery = true)
    List<String> paymentMethods(Integer userId);

    @Query(value = "select pl.level_code from parking_levels pl, bookings b, users u " +
                    "where pl.level_id = b.level_id " +
                    "and b.user_id = u.id " +
                    "and u.id= :userId;",nativeQuery = true)
    List<String> levelCodes(Integer userId);

}
