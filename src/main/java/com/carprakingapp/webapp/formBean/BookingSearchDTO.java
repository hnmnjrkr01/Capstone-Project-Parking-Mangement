package com.carprakingapp.webapp.formBean;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
public class BookingSearchDTO {

    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @NotEmpty(message = "Please provide Start Parking Date and Time.")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    @NotEmpty(message = "Please provide End Parking Date and Time.")
    private LocalDateTime endDateTime;
}
