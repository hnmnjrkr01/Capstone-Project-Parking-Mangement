package com.carprakingapp.webapp.formBean;

import com.carprakingapp.webapp.validation.EmailUnique;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@ToString
public class UserEditDTO {
    private Integer id;


    @Pattern(regexp="^[A-Za-z0-9]+$",
             message = "Username can be only alpha-numeric.")
    @Length(min=5, max=10 , message = "Username length must be 5 to 10 characters.")
    @NotEmpty(message = "Username must be provided.")
    private String username;


    @Length(min=8, message = "Password minimum length is 8 characters.")
    @Length(max=15, message = "Password maximum length is 15 characters.")
    @NotEmpty(message = "Password must be provided.")
    private String password;


    @Length(min=8, message = "Password minimum length is 8 characters.")
    @Length(max=15, message = "Password maximum length is 15 characters.")
    @NotEmpty(message = "Confirm your password.")
    private String confirmPassword;

    @Pattern(regexp = "^[A-Za-z]+$",message = "Name can have alphabets only.")
    @NotEmpty(message = "Firstname must be provided.")
    private String firstname;

    @Pattern(regexp = "^[A-Za-z]+$",message = "Name can have alphabets only.")
    @NotEmpty(message = "Lastname must be provided.")
    private String lastname;

    @Pattern(regexp = "^\\d{3}[-\\s]?\\d{3}[-\\s]?\\d{4}$",
             message = "123-456-7810 Kindly provide in this format." )
    @NotBlank(message = "Phone number must be provided.")
    private String phone;


    @Email(message = "Provide correct email.")
    @NotEmpty(message = "Email must be provided.")
    private String email;

}
