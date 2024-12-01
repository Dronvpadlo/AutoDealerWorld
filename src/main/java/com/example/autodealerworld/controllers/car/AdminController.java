package com.example.autodealerworld.controllers.car;

import com.example.autodealerworld.entity.dto.PermissionDTO;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.RoleDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.services.PermissionService;
import com.example.autodealerworld.services.RoleService;
import com.example.autodealerworld.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    private final PermissionService permissionService;

    private final RoleService roleService;

    @PostMapping("")
    public ResponseEntity<UserDTO> createManager(@RequestBody @Valid RegisterDTO registerDTO){
        return new ResponseEntity<>(userService.createManager(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/permission")
    public ResponseEntity<PermissionDTO> createPermission(@RequestParam String name){
        return new ResponseEntity<>(permissionService.createPermission(name), HttpStatus.CREATED);
    }

    @GetMapping("/permission")
    public ResponseEntity<List<PermissionDTO>> findPermissions(){
        return new ResponseEntity<>(permissionService.getAllPermissions(), HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<RoleDTO> createRole(@RequestParam String name){
        return new ResponseEntity<>(roleService.createRole(name), HttpStatus.CREATED);
    }

    @GetMapping("/role")
    public ResponseEntity<List<RoleDTO>> findRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @PostMapping("/role/{roleId}/permissions/{permissionId}")
    public ResponseEntity<RoleDTO> addPermissionToRole(@PathVariable Long roleId,
                                                       @PathVariable Long permissionId) {
        RoleDTO updatedRole = roleService.addPermissionToRole(roleId, permissionId);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }
}
