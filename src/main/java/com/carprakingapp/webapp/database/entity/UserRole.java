package com.carprakingapp.webapp.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name="user_roles")
public class UserRole {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private Integer userRoleId;

    @Column(name = "role_code", columnDefinition = "Char")
    private String roleCode;

    @Column(name = "role_description")
    private String roleDescription;

    public UserRole( String roleCode, String roleDescription) {
        this.roleCode = roleCode;
        this.roleDescription = roleDescription;
    }

    //-----------One User-Role can have Many UserRoleLink-------------

    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserRoleLink> userRoleLinks;

    //---------------------------------------------------------------


//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "user_role_link",
//            joinColumns = {
//                    @JoinColumn(name = "user_role_id", referencedColumnName = "user_role_id",
//                            nullable = false, updatable = false, insertable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "user_id", referencedColumnName = "id",
//                            nullable = false, updatable = false, insertable = false)})
//    private List<User> users;
}
