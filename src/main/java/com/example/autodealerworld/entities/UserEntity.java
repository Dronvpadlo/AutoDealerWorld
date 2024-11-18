package com.example.autodealerworld.entities;

import org.springframework.stereotype.Component;

import javax.management.relation.Role;

@Component
public class UserEntity {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private ProfileType profileType;
}
