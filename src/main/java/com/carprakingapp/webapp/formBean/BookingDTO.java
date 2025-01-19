package com.carprakingapp.webapp.formBean;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingDTO {

    private Integer userId;


    private Integer parkingSpotId;

    private Integer levelId;

    @NotEmpty(message = "Provide Parking Spot Name.")
    private String parkingSpotName;

    private Integer paymentId;

    private Double totalPrice;

    @NotEmpty(message = "Provide payment method. ")
    private String paymentMethod;

    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @NotEmpty(message = "Please provide booking start date and time.")
    private String startDateTime;

    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @NotEmpty(message = "Please provide booking start date and time.")
    private String endDateTime;

    @NotEmpty(message = "Please provide License Plate Number.")
    private String licensePlateNumber;
}
