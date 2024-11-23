package com.example.autodealerworld.controllers.register;

import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> userRegister(@RequestBody @Valid RegisterDTO registerDTO){
        return new ResponseEntity<>(userService.userRegister(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/beseller")
    public ResponseEntity<UserDTO> becomeAsSeller(@RequestParam Long userId){
        return new ResponseEntity<>(userService.beAsSeller(userId), HttpStatus.OK);
    }

    @PostMapping("/buypremium")
    public ResponseEntity<UserDTO> buyPremium(@RequestParam Long userId){
        return new ResponseEntity<>(userService.buyPremium(userId), HttpStatus.OK);
    }
}
