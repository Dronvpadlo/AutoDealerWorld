package com.example.autodealerworld.controllers.register;

import com.example.autodealerworld.entity.dto.*;
import com.example.autodealerworld.services.SecurityUserService;
import com.example.autodealerworld.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final SecurityUserService securityUserService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        UserDTO userDTO = securityUserService.createUser(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            UserDetails user = securityUserService.loadUserByUsername(authRequestDTO.getUsername());
            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);

            return ResponseEntity.ok(AuthResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}