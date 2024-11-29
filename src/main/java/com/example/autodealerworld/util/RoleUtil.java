package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleUtil {

    public Role mapRoleToEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setName(roleDTO.getName());
        role.setPermissions(roleDTO.getPermissions());
        return role;
    }

    public RoleDTO mapROleToDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(roleDTO.getName());
        roleDTO.setPermissions(roleDTO.getPermissions());
        return roleDTO;
    }
}
