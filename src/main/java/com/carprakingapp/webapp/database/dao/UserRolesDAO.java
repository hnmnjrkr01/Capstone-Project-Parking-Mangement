package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRolesDAO extends JpaRepository<UserRole, Long> {

    UserRole findByUserRoleId(Integer id);

//    List<UserRole> findByUserRoleId(Integer userRoleId);

    @Query(value = "select ur.* " +
            "from user_roles ur, user_role_link url, users u " +
            "where ur.user_role_id=url.user_role_id " +
            "and url.user_id = u.id  and u.id= :userId   ;" , nativeQuery = true)
    List<UserRole> findUserRoleByUserId(@Param("userId") Integer userId);

}
