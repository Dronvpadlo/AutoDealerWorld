package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.dto.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleUtil {

    public RoleDTO mapRoleToDTO(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        roleDTO.setPermissions(role.getPermissions());
        roleDTO.setId(role.getId());
        return roleDTO;
    }
}
