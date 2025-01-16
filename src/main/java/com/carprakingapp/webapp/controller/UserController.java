package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.database.dao.UserDAO;
import com.carprakingapp.webapp.database.entity.User;
import com.carprakingapp.webapp.formBean.UserEditDTO;
import com.carprakingapp.webapp.security.AuthenticatedUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/User")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private static String DUMMY_VALUE = "DUMMYVALUE";

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userSearch")
    public ModelAndView userSearch(){

        ModelAndView response = new ModelAndView();

        response.setViewName("User/userSearch");

        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/searchUserByLastname")
    public ModelAndView searchUserByLastname(@RequestParam(required = false) String lastname){
        ModelAndView response = new ModelAndView();

        response.setViewName("User/foundUsers");

        if(!lastname.isEmpty()) {
            response.addObject("lastname", lastname);
            List<User> userList = userDAO.findByLastname(lastname);
            response.addObject("userList", userList);

        }else{
            response.addObject("username", "null");
        }
        return response;
    }

    @GetMapping("/editUser")
    public ModelAndView editUser(){

        ModelAndView response = new ModelAndView();
        response.setViewName("User/editUser");

        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{userId}")
    public ModelAndView editUser(@PathVariable Integer userId){
        ModelAndView response = new ModelAndView();

        response.setViewName("User/editUser");

        if(userId != null) {

            User user = userDAO.findById(userId);
            UserEditDTO userEditDTO = new UserEditDTO();

            userEditDTO.setId(user.getId());
            userEditDTO.setUsername(this.DUMMY_VALUE);
            userEditDTO.setPassword(this.DUMMY_VALUE);
            userEditDTO.setConfirmPassword(this.DUMMY_VALUE);
            userEditDTO.setFirstname(user.getFirstname());
            userEditDTO.setLastname(user.getLastname());
            userEditDTO.setEmail(user.getEmail());
            userEditDTO.setPhone(user.getPhone());

            //once data is Edited in Jsp page set the data back in databasE
            response.addObject("userEditDTO", userEditDTO);


        }
        return response;
    }

    @PostMapping("/selfEditSubmit")
    public ModelAndView signupSubmit(@Valid UserEditDTO userEditDTO, BindingResult bindingResult, HttpSession session) {
        ModelAndView response = new ModelAndView();
        String encryptedPassword = null;

        response.setViewName("User/selfEdit");

        //----Checking the the password and confirm-password should match--------------------
        if(StringUtils.equals(userEditDTO.getPassword(), userEditDTO.getConfirmPassword())) {

            encryptedPassword = passwordEncoder.encode(userEditDTO.getPassword());

        }else{
            bindingResult.rejectValue("confirmPassword", "error", "passwords do not match");
        }
        //------------------------------Email cannot be same for 2 different Users----------------------------------------------------

        User tempUser = userDAO.findByEmail(userEditDTO.getEmail());
        if(tempUser != null && tempUser.getId() != userEditDTO.getId()) {
            bindingResult.rejectValue("email", "email","This email already exist.");
        }
        //-------------------------------------------------------------------------------------

        if(bindingResult.hasErrors()) {
            response.addObject("bindingResult", bindingResult);
            response.addObject("userEditDTO", userEditDTO);
        }else{
            User newUser = userDAO.findById(userEditDTO.getId());
            newUser.setPassword(encryptedPassword);
            newUser.setUsername(userEditDTO.getUsername());
            newUser.setFirstname(userEditDTO.getFirstname());
            newUser.setLastname(userEditDTO.getLastname());
            newUser.setPhone(userEditDTO.getPhone());
            newUser.setEmail(userEditDTO.getEmail());

            userDAO.save(newUser);

            authenticatedUserService.changeLoggedInUsername(session,userEditDTO.getUsername(),userEditDTO.getPassword());


            response.setViewName("redirect:/User/userDashboard");
            response.addObject("newUser", newUser);
        }

        return response;
    }

    @PostMapping("/editSubmit")
    public ModelAndView editSubmit(@Valid UserEditDTO userEditDTO, BindingResult bindingResult){
        ModelAndView response = new ModelAndView();

        response.setViewName("User/editUser");

        User tempUser = userDAO.findByEmail(userEditDTO.getEmail());
        if(tempUser != null && tempUser.getId() != userEditDTO.getId()) {
            bindingResult.rejectValue("email", "email","This email already exist.");
        }

        if(bindingResult.hasErrors()) {

            response.addObject("bindingResult", bindingResult);
            response.addObject("userEditDTO", userEditDTO);
        }else {

            User user = userDAO.findById(userEditDTO.getId());

            user.setFirstname(userEditDTO.getFirstname());
            user.setLastname(userEditDTO.getLastname());
            user.setEmail(userEditDTO.getEmail());
            user.setPhone(userEditDTO.getPhone());

            userDAO.save(user);
            response.addObject("userEditDTO", userEditDTO);
            response.setViewName("redirect:/User/foundUsers");




        }


        return response;
    }


    @GetMapping("/signUp")
    public ModelAndView loadSignIn(){

        ModelAndView response = new ModelAndView();
        response.setViewName("User/signUp");

        return response;


    }


    @GetMapping("/myself/{loggedUserId}")
    public ModelAndView edit(@PathVariable Integer loggedUserId){
        ModelAndView response = new ModelAndView();
        response.setViewName("User/selfEdit");
        User userEditDTO = userDAO.findById(loggedUserId);
        response.addObject("userEditDTO", userEditDTO);
        return response;
    }






}
