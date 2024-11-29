package com.example.autodealerworld.controllers.car;

import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.entity.enums.RoleName;
import com.example.autodealerworld.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/manager/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> patchRoleAndProfileType(@PathVariable Long id, @RequestBody RoleName roleName, ProfileType profileType){
        return new ResponseEntity<>(userService.changeUserRoleOrProfileType(id, roleName, profileType), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);
    }
}