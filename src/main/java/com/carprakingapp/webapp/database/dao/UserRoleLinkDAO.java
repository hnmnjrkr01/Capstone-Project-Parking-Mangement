package com.carprakingapp.webapp.database.dao;

import com.carprakingapp.webapp.database.entity.User;
import com.carprakingapp.webapp.database.entity.UserRole;
import com.carprakingapp.webapp.database.entity.UserRoleLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRoleLinkDAO extends JpaRepository<UserRoleLink, Long> {

    User findUserByUserId(Integer userId);

    UserRole findUserRoleByUserRoleId(Integer userRoleId);
}
