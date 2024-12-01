package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.entity.dto.PermissionDTO;
import org.springframework.stereotype.Component;

@Component
public class PermissionUtil {

    public PermissionDTO mapPermissionToDTO(RolesPermission rolesPermission){
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setName(rolesPermission.getName());
        permissionDTO.setRoles(rolesPermission.getRoles());
        permissionDTO.setId(rolesPermission.getId());
        return permissionDTO;
    }
}
