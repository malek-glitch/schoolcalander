package plus.delta.schoolcalender.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.delta.schoolcalender.model.DTO.LoginRequest;
import plus.delta.schoolcalender.model.DTO.LoginResponse;
import plus.delta.schoolcalender.service.AuthenticationsService;

import java.util.Date;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    final private AuthenticationManager authenticationManager;
    final private AuthenticationsService authService;
    final private Long TOKEN_EXPIRATION_MS = 1000L * 60 * 60 * 24; // 24h

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        UserDetails userDetails = authService.authenticate(
                request.username(),
                request.password()
        );
        String token = authService.generateToken(userDetails);
        LoginResponse response = LoginResponse.builder()
                .token(token)
                .expireIn(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MS))
                .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login2")
    public ResponseEntity<Authentication> login2(@RequestBody LoginRequest request) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(request.username(), request.password());
        Authentication response =
                this.authenticationManager.authenticate(authenticationRequest);
//        String token = authService.generateToken();
//        LoginResponse response = LoginResponse.builder()
//                .token(token)
//                .expireIn(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MS))
//                .build();
        return ResponseEntity.ok(response);
    }

}

