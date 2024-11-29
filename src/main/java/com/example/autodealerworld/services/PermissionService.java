package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.entity.dto.PermissionDTO;
import com.example.autodealerworld.repository.PermissionRepository;
import com.example.autodealerworld.util.PermissionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionUtil permissionUtil;

    public PermissionDTO createPermission(String name) {
        if (permissionRepository.existsByName(name)) {
            throw new IllegalArgumentException("Permission with this name already exists");
        }
        RolesPermission permission = new RolesPermission();
        permission.setName(name);
        return permissionUtil.mapPermissionToDTO(permissionRepository.save(permission));
    }

    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionUtil::mapPermissionToDTO)
                .toList();
    }
}
