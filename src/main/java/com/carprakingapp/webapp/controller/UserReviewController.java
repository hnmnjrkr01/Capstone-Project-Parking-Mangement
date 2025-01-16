package com.carprakingapp.webapp.controller;


import com.carprakingapp.webapp.database.dao.UserReviewDAO;
import com.carprakingapp.webapp.database.entity.User;
import com.carprakingapp.webapp.database.entity.UserReview;
import com.carprakingapp.webapp.formBean.UserReviewDTO;
import com.carprakingapp.webapp.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/UserReview")
public class UserReviewController {

    private static final Logger LOG = LoggerFactory.getLogger(UserReviewController.class);

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private UserReviewDAO reviewDAO;


    @PostMapping("/review")
    public ModelAndView userReview(@Valid UserReviewDTO userReviewDTO, BindingResult bindingResult, Model model) {

        ModelAndView response = new ModelAndView();
        response.setViewName("OtherPages/contactUs");
        LOG.info("****************Welcome to  UserReviewController******************");

        User loggedUser = authenticatedUserService.loadCurrentUser();
        response.addObject("loggedUser", loggedUser);

        userReviewDTO.setUserId(loggedUser.getId());

        if(bindingResult.hasErrors()) {

            response.addObject("bindingResult", bindingResult);
            response.addObject("userReviewDTO", userReviewDTO);
        }else{

            UserReview review = new UserReview();

            review.setUserId(userReviewDTO.getUserId());
            review.setUserReviews(userReviewDTO.getUserReviews());
            review.setUserReviewRating(userReviewDTO.getUserReviewRating());

            reviewDAO.save(review);

            model.addAttribute("message", "Thank you for submitting your review!");

        }
        return response;
    }
}
