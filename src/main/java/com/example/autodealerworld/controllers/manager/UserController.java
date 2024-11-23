package com.example.autodealerworld.controllers.manager;

import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.entity.enums.UserRole;
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
    public ResponseEntity<UserDTO> patchRoleAndProfileType(@PathVariable Long id, @RequestBody UserRole userRole, ProfileType profileType){
        return new ResponseEntity<>(userService.changeUserRoleOrProfileType(id, userRole, profileType), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> findAllUsers(){
        return new ResponseEntity<>(userService.findUsers(), HttpStatus.OK);
    }
}
