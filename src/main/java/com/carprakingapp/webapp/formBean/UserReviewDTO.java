package com.carprakingapp.webapp.formBean;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserReviewDTO {

    private Integer userId;

    @NotEmpty(message = "Kindly provide your reviews and help us to enhance your next experience!")
    private String userReviews;

    @NotNull(message = "Kindly provide your rating out of 5.")
    private Integer userReviewRating;
}
