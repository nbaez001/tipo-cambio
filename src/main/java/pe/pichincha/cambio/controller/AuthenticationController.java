package pe.pichincha.cambio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import pe.pichincha.cambio.config.security.JWTUtil;
import pe.pichincha.cambio.config.security.PBKDF2Encoder;
import pe.pichincha.cambio.dto.security.AuthRequest;
import pe.pichincha.cambio.dto.security.AuthResponse;
import pe.pichincha.cambio.service.UserService;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private JWTUtil jwtUtil;
    private PBKDF2Encoder passwordEncoder;
    private UserService userService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
            .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
            .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
