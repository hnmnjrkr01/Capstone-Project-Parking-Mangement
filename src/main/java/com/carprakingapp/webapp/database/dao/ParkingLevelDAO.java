package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.ParkingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingLevelDAO extends JpaRepository<ParkingLevel, Long> {

    ParkingLevel findByLevelId(Integer levelId);

    ParkingLevel findByLevelCode(String levelCode);


}
