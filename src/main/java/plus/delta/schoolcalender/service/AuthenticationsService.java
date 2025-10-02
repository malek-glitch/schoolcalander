package plus.delta.schoolcalender.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationsService {

    final private UserDetailsService userDetailsService;
    final private AuthenticationManager authenticationManager;

    private final Long TOKEN_EXPIRATION_MS = 1000L * 60 * 60 * 24;

//    @Value("${jwt.secret}")
    private final String TOKEN_SECRET = "80d01f85e6169ecaaf3ba73f6293e002cae9085b8143943ac4f31077ceeed313";

    public UserDetails authenticate(String username, String password) {
        password = new BCryptPasswordEncoder().encode(password);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return userDetailsService.loadUserByUsername(username);
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .issuedAt(new java.util.Date(System.currentTimeMillis()))
                .expiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .compact();
    }

    public UserDetails validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.loadUserByUsername(username);
    }

    private String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }

    private Key getSigningKey() {
        byte[] keyBytes = TOKEN_SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
//
}
