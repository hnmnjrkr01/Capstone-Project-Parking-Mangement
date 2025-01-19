package com.carprakingapp.webapp.formBean;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class ParkingSpotDTO {

    private Integer parkingSpotId;


    @Length(max = 3, message = "Parking Spot name is an alphabet followed with digits.")
    @NotEmpty(message = "Please provide a name for new parking spot.")
    private String parkingSpotName;

    @NotNull(message = "Please select parking level.")
    private Integer parkingLevelId;

}
