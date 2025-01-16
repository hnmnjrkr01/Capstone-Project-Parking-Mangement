package com.carprakingapp.webapp.formBean;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ParkingLevelDTO {

    private Character levelCode;

    private int MaximumCapacity;
}
