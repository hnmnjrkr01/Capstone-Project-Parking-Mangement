package com.carprakingapp.webapp.formBean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParkingSpotDTO {


    @NotNull(message = "Please select a prking level.")
    private Integer parkingLevelId;

    @NotEmpty(message = "Please select an option for parking.")
    private String parkingSpotName;


    private Boolean parkingOccupancy;


}
