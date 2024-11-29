package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PermissionDTO {

    private Long id;

    private String name;

    private Set<Role> roles = new HashSet<>();
}
