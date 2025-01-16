package com.carprakingapp.webapp.services;


import com.carprakingapp.webapp.database.dao.BookingDAO;
import com.carprakingapp.webapp.database.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


public class BookingServices {

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public Integer durationCalculation(LocalDateTime start, LocalDateTime end) {

        if(start.isBefore(end)) {
            Duration duration = Duration.between(start, end);
            long totalHours = duration.toHours();

            return ((int) totalHours);
        }else{
            return 0;
        }
    }

    public double totalPriceCalculation(Integer duration) {

        double tax = 8.25;
        double totalPrice = 0;

        if(duration == 1){
           totalPrice =  (5+(5*tax));
        }else if (duration == 2){
            totalPrice = (9+(9*tax));
        }else if (duration == 8){
            totalPrice = (25+(25*tax));
        }else if (duration == 24){
            totalPrice = (45+(45*tax));
        }else if (duration > 24){
            totalPrice = (200+(200*tax));
        }
        return totalPrice;
    }

    public boolean isDateBetween(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }
}
