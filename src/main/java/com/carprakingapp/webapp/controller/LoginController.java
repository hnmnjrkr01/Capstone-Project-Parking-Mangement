package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.database.dao.*;
import com.carprakingapp.webapp.database.entity.*;
import com.carprakingapp.webapp.formBean.UserDTO;
import com.carprakingapp.webapp.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRolesDAO userRolesDao;

    @Autowired
    private UserRoleLinkDAO userRoleLinkDao;

    @Autowired
    private BookingDAO bookingDao;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;


    // this get mpapping is called by spring security when a secure resource is requested
    // needs to match the spring securtiy configuration  .loginPage method
    @GetMapping("/login/login")
    public ModelAndView theLoginPage() {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/loginPage");
        return response;
    }

    @PreAuthorize("hasAuthority('CUSTMR')")
    @GetMapping("/User/userDashboard")
    public ModelAndView userDashboard() {
        ModelAndView response = new ModelAndView();
        response.setViewName("User/userDashboard");

        User loggedUser = authenticatedUserService.loadCurrentUser();
        response.addObject("loggedUser", loggedUser);

        List<Booking> booking = bookingDao.findByUserId(loggedUser.getId());
        response.addObject("booking", booking);

        return response;
    }



    @PostMapping("/User/signupSubmit")
    public ModelAndView signupSubmit(@Valid UserDTO userDTO, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();
        String encryptedPassword = null;

        response.setViewName("User/signUp");

        //----Checking the the password and confirm-password should match--------------------
        if(StringUtils.equals(userDTO.getPassword(), userDTO.getConfirmPassword())) {

            encryptedPassword = passwordEncoder.encode(userDTO.getPassword());

        }else{
            bindingResult.rejectValue("confirmPassword", "error", "passwords do not match");
        }
        //----------------------------------------------------------------------------------


        if(bindingResult.hasErrors()) {
            response.addObject("bindingResult", bindingResult);
            response.addObject("userDTO", userDTO);
        }else{
            User newUser = userDao.findByUsername(userDTO.getUsername());
            if(newUser == null) {
                newUser = new User();
            }
            Integer customerRoleId = 3;

            newUser.setPassword(encryptedPassword);
            newUser.setUsername(userDTO.getUsername());
            newUser.setFirstname(userDTO.getFirstname());
            newUser.setLastname(userDTO.getLastname());
            newUser.setPhone(userDTO.getPhone());
            newUser.setEmail(userDTO.getEmail());

            userDao.save(newUser);

            //----------Creating new row in User-Role-Link table also-------------------------------
            UserRoleLink userRoleLink = new UserRoleLink();
            userRoleLink.setUserId(newUser.getId());
            userRoleLink.setUserRoleId(customerRoleId);
            userRoleLinkDao.save(userRoleLink);
            //------------------------------------------------------------------------------------

            authenticatedUserService.changeLoggedInUsername(session,userDTO.getUsername(),userDTO.getPassword());

            response.setViewName("User/userDashboard");
        }

        return response;
    }


}