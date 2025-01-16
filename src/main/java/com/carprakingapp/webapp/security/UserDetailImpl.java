package com.carprakingapp.webapp.security;



import com.carprakingapp.webapp.database.dao.UserDAO;
import com.carprakingapp.webapp.database.dao.UserRolesDAO;
import com.carprakingapp.webapp.database.entity.User;
import com.carprakingapp.webapp.database.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Slf4j
@Component
public class UserDetailImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRolesDAO userRoleDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean accountNonLocked = true;
        boolean credentialsNonExpired = true;

        //I need userId from userRoleLink create
        // that entity and DAO
        // then create a method in UreRole dao to fetch data from UerRoleLink table
        //using
//        List<UserRole> userRoles = userRoleDAO.findByUserRoleId(user.getId());
        List<UserRole> userRoles = userRoleDAO.findUserRoleByUserId(user.getId());



        // convert our user roles into spring granted authorities
        List<GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                accountIsEnabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                springRoles);
    }

    public List<GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        // first we create an empty list of spring granted authorities
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        // loop over our user roles from the database
        for (UserRole role : userRoles) {
            // create a new simple granted authority for each user role in the database
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleCode());
            authorities.add(authority);
        }



        return authorities;
    }


}












