package com.carprakingapp.webapp.database.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="user_role_links")
public class UserRoleLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_link_id")
    private Integer userRoleLinkId;


    //-----------One User-Role can have Many UserRoleLink-------------

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_role_id", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private UserRole userRole;

    @Column(name = "user_role_id")
    private Integer userRoleId;

    //---------------------------------------------------------------

    //--------------------One User can have Many UserRoleLinks---------

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    //------------------------------------------------------------






}
