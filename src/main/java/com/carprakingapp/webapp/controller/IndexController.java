package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.services.BookingServices;
import com.mysql.cj.log.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Controller
//@PreAuthorize("hasAuthority('CUSTMR')")
public class IndexController {

    @GetMapping(value ={"/", "/HomePage", "/homepage.html"})
    public ModelAndView homepage() {
        ModelAndView response = new ModelAndView();
        response.setViewName("HomePage");
        return response;
    }

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView response = new ModelAndView();
        response.setViewName("index");
        return response;
    }

    @GetMapping(value = "/OtherPages/about")
    public ModelAndView about() {
        ModelAndView response = new ModelAndView();
        response.setViewName("OtherPages/about");
        return response;
    }

    @GetMapping(value = "/OtherPages/contactUs")
    public ModelAndView contactUs() {
        ModelAndView response = new ModelAndView();
        response.setViewName("OtherPages/contactUs");
        return response;
    }

    @GetMapping("/searchAvailability")
    public ModelAndView searchAvailability(@RequestParam String startParkingTime ,
                                           @RequestParam String endParkingTime
                                           )throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("Booking/availableSlots");

        //--------------Converting String to Date----------------------------------------------
        SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date start = sdfStart.parse(startParkingTime);

        SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date end = sdfEnd.parse(endParkingTime);

        //---------------Converting Date to localDateTime---------------------------------------
        BookingServices services = new BookingServices();
        LocalDateTime startParking = services.convertToLocalDateTimeViaInstant(start);
        LocalDateTime endParking = services.convertToLocalDateTimeViaInstant(end);

        long availableSlots = Math.round(Math.random()*20);

        response.addObject("availableSlots", availableSlots);
        response.addObject("startParking", startParking);
        response.addObject("endParking", endParking);

        return response;
    }

}
