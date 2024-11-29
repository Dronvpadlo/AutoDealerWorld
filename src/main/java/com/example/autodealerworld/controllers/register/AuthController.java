package com.example.autodealerworld.controllers.register;

import com.example.autodealerworld.entity.dto.AuthRequestDTO;
import com.example.autodealerworld.entity.dto.AuthResponseDTO;
import com.example.autodealerworld.entity.dto.SignUpRequestDTO;
import com.example.autodealerworld.entity.dto.SignUpResponseDTO;
import com.example.autodealerworld.services.SecurityUserService;
import com.example.autodealerworld.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
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
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponceDTO = securityUserService.createUser(signUpRequestDTO);
        return new ResponseEntity<>(signUpResponceDTO, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponseDTO> signIn(@RequestBody @Valid AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (authentication.isAuthenticated()) {
            UserDetails user = securityUserService.loadUserByUsername(authRequestDTO.getUsername());
            String accessToken = jwtUtil.generateAccessToken(user);
            String refreshToken = jwtUtil.generateRefreshToken(user);
            return new ResponseEntity<>(AuthResponseDTO.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}