package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.entity.dto.RoleDTO;
import com.example.autodealerworld.repository.PermissionRepository;
import com.example.autodealerworld.repository.RoleRepository;
import com.example.autodealerworld.util.RoleUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleUtil roleUtil;

    private final PermissionRepository permissionRepository;
    public RoleDTO createRole(String name) {
        if (roleRepository.existsByName(name)) {
            throw new IllegalArgumentException("Role with this name already exists");
        }

        Role role = new Role();
        role.setName(name);
        return roleUtil.mapRoleToDTO(roleRepository.save(role));
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleUtil::mapRoleToDTO)
                .toList();
    }

    public RoleDTO addPermissionToRole(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        RolesPermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        role.getPermissions().add(permission);

        Role updatedRole = roleRepository.save(role);

        return roleUtil.mapRoleToDTO(updatedRole);
    }

    public Set<String> getPermissionsForRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        return role.getPermissions().stream()
                .map(RolesPermission::getName)
                .collect(Collectors.toSet());
    }
}
