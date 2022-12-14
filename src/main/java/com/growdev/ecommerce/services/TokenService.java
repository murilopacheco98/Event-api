package com.growdev.ecommerce.services;

import com.growdev.ecommerce.config.semAdapter.TokenReponse;
import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.entities.UserEntity;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    JwtEncoder jwtEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public TokenReponse generateToken(String email, String senha) {
        Instant instantNow = Instant.now();
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário não encontrado." + email);
        }
        if (!passwordEncoder.matches(senha, user.getPassword())) {
            throw new ResourceNotFoundException("Email e/ou senha errados.");
        }
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(instantNow)
                .expiresAt(instantNow.plus(1, ChronoUnit.HOURS))
                .subject(user.getEmail())
                .claim("scope", scope)
                .build();
        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        ZonedDateTime expirationDate = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getExpiresAt().atZone(ZoneId.systemDefault());
        ZonedDateTime issuedDate = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getIssuedAt().atZone(ZoneId.systemDefault());;
        String timeToken = String.valueOf(expirationDate.compareTo(issuedDate)) + ":00 hours";

        String[] splitExpirationDate = String.valueOf(expirationDate).split("T");
        String[] splitExpiration = splitExpirationDate[1].split("\\.");
        String messageExpirationDate = splitExpirationDate[0] + " " + splitExpiration;

        UserDTO userDTO = new UserDTO(user);
        return new TokenReponse(userDTO, token, timeToken, messageExpirationDate);
    }
}
