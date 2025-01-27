package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.database.dao.BookingDAO;
import com.carprakingapp.webapp.database.dao.ParkingLevelDAO;
import com.carprakingapp.webapp.database.dao.ParkingSpotDAO;
import com.carprakingapp.webapp.database.dao.PaymentMethodDAO;
import com.carprakingapp.webapp.database.entity.*;
import com.carprakingapp.webapp.formBean.BookingDTO;
import com.carprakingapp.webapp.formBean.SlotSearchDTO;
import com.carprakingapp.webapp.formBean.PaymentMethodDTO;
import com.carprakingapp.webapp.security.AuthenticatedUserService;
import com.carprakingapp.webapp.services.BookingServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/Booking")
public class BookingController {


    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private PaymentMethodDAO paymentMethodDAO;

    @Autowired
    private ParkingLevelDAO parkingLevelDAO;

    @Autowired
    private ParkingSpotDAO parkingSpotDAO;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    @GetMapping("/parkingBooking")
    public ModelAndView parkingBooking(@RequestParam String startParking,
                                       @RequestParam String endParking,
                                       @RequestParam String parkingSpotId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("Booking/parkingBooking");

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setStartDateTime(startParking);
        bookingDTO.setEndDateTime(endParking);
        bookingDTO.setParkingSpotId(Integer.parseInt(parkingSpotId));         //Extracting int value from String Object

        //-------------Get the ParkingSpot object------------------
        ParkingSpot parkingSpot = parkingSpotDAO.findByParkingSpotId(Integer.parseInt(parkingSpotId));
        bookingDTO.setLevelId(parkingSpot.getParkingLevelId());
        bookingDTO.setParkingSpotName(parkingSpot.getParkingSpotName());



        //--------------Converting String to Date----------------------------------------------
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date startParkingDate = formatter.parse(bookingDTO.getStartDateTime());
        Date endParkingDate = formatter.parse(bookingDTO.getEndDateTime());

        BookingServices services = new BookingServices();

        //---------------Converting Date to localDateTime---------------------------------------
        LocalDateTime startDateTime = services.convertToLocalDateTimeViaInstant(startParkingDate);
        LocalDateTime endDateTime = services.convertToLocalDateTimeViaInstant(endParkingDate);

        //--------------Calculating Duaration and Total-Price to save in Booking Entity------------
        Integer duration = services.durationCalculation(startDateTime, endDateTime);
        Double totalPrice = services.totalPriceCalculation(duration);

        bookingDTO.setTotalPrice(totalPrice);
        //-----------------------------------------------------------------------------------------



        User loggedUser = authenticatedUserService.loadCurrentUser();
        response.addObject("loggedUser", loggedUser);
        response.addObject("bookingDTO", bookingDTO);

        return response;
    }

    @PostMapping("/bookNewParking")
    public ModelAndView bookNewParking(@Valid BookingDTO bookingDTO, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();


        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO();

        User loggedUser = authenticatedUserService.loadCurrentUser();
        response.addObject("loggedUser", loggedUser);

        bookingDTO.setUserId(loggedUser.getId());
        paymentMethodDTO.setUserId(loggedUser.getId());

        if (bindingResult.hasErrors()) {

            response.addObject("bindingResult", bindingResult);
            response.addObject("bookingDTO", bookingDTO);
            response.setViewName("Booking/parkingBooking");

        } else {

            BookingServices services = new BookingServices();

            //--------------Converting String to Date----------------------------------------------
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date startParking = formatter.parse(bookingDTO.getStartDateTime());
            Date endParking = formatter.parse(bookingDTO.getEndDateTime());

            if (startParking.after(endParking)) {
                bindingResult.rejectValue("startDateTime", "error",
                        "Start-Parking-Date cannot be later than End-Parking-Date");
                response.addObject("bindingResult", bindingResult);
                response.addObject("bookingDTO", bookingDTO);
                response.setViewName("Booking/parkingBooking");

            }else {

                //---------------Converting Date to localDateTime---------------------------------------
                LocalDateTime startDateTime = services.convertToLocalDateTimeViaInstant(startParking);
                LocalDateTime endDateTime = services.convertToLocalDateTimeViaInstant(endParking);

                paymentMethodDTO.setDateOfPayment(endDateTime);

                //--------------Calculating Duaration and Total-Price to save in Booking Entity------------

                Integer duration = services.durationCalculation(startDateTime, endDateTime);
                Double totalPrice = services.totalPriceCalculation(duration);

                //--------------------Creating Payment-Method entity first-----------------------------------

                PaymentMethod payment = new PaymentMethod();
                payment.setUserId(paymentMethodDTO.getUserId());
                payment.setPaymentMethod(bookingDTO.getPaymentMethod());
                payment.setDateOfPayment(paymentMethodDTO.getDateOfPayment());
                payment.setUser(loggedUser);

                paymentMethodDAO.save(payment);

                //-----------------Now will create Booking-Entity with payment-id-----------------------------

                Booking booking = new Booking();
                booking.setUserId(payment.getUserId());
                booking.setPaymentMethodId(payment.getPaymentId());
               booking.setParkingSpotId(bookingDTO.getParkingSpotId());
                booking.setStartDateTime(startDateTime);
                booking.setEndDateTime(endDateTime);
                booking.setDuration(duration);
                booking.setTotalPrice(totalPrice);
                booking.setBookingDate(LocalDateTime.now());
                booking.setLicensePlateNumber(bookingDTO.getLicensePlateNumber());

                bookingDAO.save(booking);

                response.setViewName("redirect:/User/userDashboard");

            }
        }

        return response;
    }

    @GetMapping("/availableSlots")
    public ModelAndView availableSlots(@Valid SlotSearchDTO slotSearchDTO,
                                       BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        User loggedUser = authenticatedUserService.loadCurrentUser();
        response.addObject("loggedUser", loggedUser);


        if (bindingResult.hasErrors()) {
            response.addObject("bindingResult", bindingResult);
            response.addObject("searchDTO", slotSearchDTO);

            List<Booking> booking = bookingDAO.findByUserId(loggedUser.getId());
            response.addObject("booking", booking);

            response.setViewName("User/userDashboard");
        } else {

            response.setViewName("Booking/availableSlots");

            BookingServices services = new BookingServices();
            ParkingLevel parkingLevel = parkingLevelDAO.findByLevelId(slotSearchDTO.getLevelId());
            int maxOccupancy = parkingLevel.getMaximumCapacity();
            int busySlotsCounter = 0;
            int availableSlots = maxOccupancy;

            //--------------Converting String to Date----------------------------------------------
            SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date start = sdfStart.parse(slotSearchDTO.getStartParkingTime());

            SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date end = sdfEnd.parse(slotSearchDTO.getEndParkingTime());

            //---------------Converting Date to localDateTime---------------------------------------
            LocalDateTime startParking = services.convertToLocalDateTimeViaInstant(start);
            LocalDateTime endParking = services.convertToLocalDateTimeViaInstant(end);


            //---------------Get all the booked spots for the Level-----------------------------------
            List<Booking> bookingsList = bookingDAO.findByLevelId(slotSearchDTO.getLevelId());
            //----get all the active spots under a level -------
            List<ParkingSpot> allParkingSpots = parkingSpotDAO.findByparkingLevelIdAndDisable(slotSearchDTO.getLevelId(), false);

            if (!bookingsList.isEmpty()) {

                //-----------------filter out expired bookings--------------------------------
                //bookingsList.removeIf(n -> (n.getEndDateTime().isBefore(LocalDateTime.now())));


                for (Booking booking : bookingsList) {

                    if (services.isDateBetween(booking.getStartDateTime(), startParking, endParking)
                            || services.isDateBetween(booking.getEndDateTime(), startParking, endParking)
                            || (booking.getStartDateTime().isBefore(startParking) && booking.getEndDateTime().isAfter(endParking))    ) {

                        allParkingSpots.removeIf(p -> (p.getParkingSpotId() == booking.getParkingSpotId()));
                        busySlotsCounter++;
                    }

                }
                availableSlots = maxOccupancy - busySlotsCounter;
            }

            response.addObject("availableSlots", availableSlots);
            response.addObject("startParking", startParking);
            response.addObject("endParking", endParking);
            response.addObject("levelCode", parkingLevel.getLevelCode());
            response.addObject("allParkingSpots", allParkingSpots);


        }

        return response;

    }


}
