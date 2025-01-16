package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    User findById(Integer id);

    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findByFirstname(String firstname);

    List<User> findByLastname(String lastname);

    User findByEmailIgnoreCase(String username);


}
