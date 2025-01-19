package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.database.dao.ParkingLevelDAO;
import com.carprakingapp.webapp.database.dao.ParkingSpotDAO;
import com.carprakingapp.webapp.database.entity.ParkingLevel;
import com.carprakingapp.webapp.database.entity.ParkingSpot;
import com.carprakingapp.webapp.formBean.ParkingSpotDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/ParkingSpot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotDAO parkingSpotDAO;

    @Autowired
    private ParkingLevelDAO parkingLevelDAO;

    @GetMapping("/createParkingSpot")
    public ModelAndView createParkingSpot() {
        ModelAndView response = new ModelAndView("ParkingSpot/createParkingSpot");
        return response;
    }

    @PostMapping("/createParkingSpotSubmit")
    public ModelAndView createParkingSpotSubmit(@Valid ParkingSpotDTO parkingSpotDTO,
                                                BindingResult bindingResult, Model model) {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {

            response.addObject("bindingResult", bindingResult);
            response.addObject("parkingSpotDTO", parkingSpotDTO);
            response.setViewName("ParkingSpot/createParkingSpot");

        }else{
            ParkingLevel parkingLevel = parkingLevelDAO.findByLevelId(parkingSpotDTO.getParkingLevelId());
            ParkingSpot parkingSpot = new ParkingSpot();

            parkingSpot.setParkingLevelId(parkingLevel.getLevelId());
            parkingSpot.setParkingSpotName(parkingSpotDTO.getParkingSpotName());
            parkingSpot.setDisable(false);
            parkingSpotDAO.save(parkingSpot);

            //----------------Max Capacity Increased everytime a spot is created-------------------------
            int maxOcupancy=parkingLevel.getMaximumCapacity();
            maxOcupancy++;
            parkingLevel.setMaximumCapacity(maxOcupancy);
            parkingLevelDAO.save(parkingLevel);

            model.addAttribute("message", "Your parking spot has been created!");

            response.setViewName("ParkingSpot/createParkingSpot");
        }
        return response;
    }

    @GetMapping("/getAllParkingSpots")
    public ModelAndView getAllParkingSpots() {
        ModelAndView response = new ModelAndView();
        response.setViewName("ParkingSpot/allParkingSpots");
        List<ParkingSpot> parkingSpotList = parkingSpotDAO.findAll();
        List<ParkingLevel> parkingLevelList = parkingLevelDAO.findAll();

        response.addObject("parkingSpotList", parkingSpotList);
        response.addObject("parkingLevelList", parkingLevelList);

        return response;
    }

    @PostMapping("/deactivateParkingSpot")
    public ModelAndView activateParkingSpot(@RequestParam String parkingSpotId,Model model) {
        ModelAndView response = new ModelAndView();

        //---------------Deactivate the chosen parking-spot-----------------------------
        ParkingSpot parkingSpot = parkingSpotDAO.findByParkingSpotId(Integer.parseInt(parkingSpotId));

        if(parkingSpot.getDisable()) {
            parkingSpot.setDisable(false);
            parkingSpotDAO.save(parkingSpot);
            model.addAttribute("message", "Your parking spot has been Activated!");
        }else{
            parkingSpot.setDisable(true);
            parkingSpotDAO.save(parkingSpot);
            model.addAttribute("message", "Your parking spot has been Deactivated!");
        }
        //------------------------------------------------------------------------------

        response.setViewName("redirect:/ParkingSpot/getAllParkingSpots");
        return  response;

    }


}
