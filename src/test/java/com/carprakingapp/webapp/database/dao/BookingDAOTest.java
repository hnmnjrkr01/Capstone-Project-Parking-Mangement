package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.Booking;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class BookingDAOTest {

   @Autowired
    private BookingDAO bookingDAO;

    Booking booking;

    @Test
    @Order(1)
    public void CreateBooking() {

        booking = new Booking();

        booking.setUserId(8);
        booking.setPaymentMethodId(48);
        booking.setParkingSpotId(9);
        booking.setTotalPrice(100.00);
        booking.setStartDateTime(LocalDateTime.now());
        booking.setEndDateTime(LocalDateTime.now().plusDays(1));
        booking.setDuration(24);
        booking.setLicensePlateNumber("ABC1010");
        booking.setBookingDate(LocalDateTime.now());

        bookingDAO.save(booking);

        bookingDAO.delete(booking);
    }

    @ParameterizedTest
    @Order(2)
    @CsvSource({
            "46, 1, DFT456",
            "47, 2, RWH5127",
            "48, 4, LKJ5127"})
    public void FindBooking(Integer bookingId, Integer userId, String LicensePlateNumber) {

        Booking actualBooking = bookingDAO.findByBookingId(bookingId);

        Assertions.assertEquals(userId, actualBooking.getUserId());
        Assertions.assertEquals(LicensePlateNumber, actualBooking.getLicensePlateNumber());
//        Assertions.assertEquals(LocalDateTime.now(), actualBooking.getStartDateTime());

    }





}
