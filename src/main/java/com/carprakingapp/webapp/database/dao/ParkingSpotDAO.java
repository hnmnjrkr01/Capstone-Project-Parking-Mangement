package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.ParkingLevel;
import com.carprakingapp.webapp.database.entity.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSpotDAO extends JpaRepository<ParkingSpot, Long> {

    List<ParkingSpot> findByParkingLevelId(Integer parkingLevelId);
}
