package com.carprakingapp.webapp.formBean;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlotSearchDTO {

    @NotEmpty(message = "Please provide Start parking date.")
    private String startParkingTime;

    @NotEmpty(message = "Please provide End parking date.")
    private String endParkingTime;

    @NotNull(message = "Please select floor to search spot.")
    private Integer levelId;
}
