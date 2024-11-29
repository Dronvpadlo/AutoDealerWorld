package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.entity.enums.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
public class RoleDTO {
    private Long id;

    private String name;

    private Set<RolesPermission> permissions = new HashSet<>();

}
