package com.example.autodealerworld.controllers.car;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.RolesPermission;
import com.example.autodealerworld.entity.dto.PermissionDTO;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.services.PermissionService;
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
    public ResponseEntity<Role> createRole(Role role){
        return null;
    }

    @GetMapping("/role")
    public ResponseEntity<List<Role>> findRoles(){
        return null;
    }
}
