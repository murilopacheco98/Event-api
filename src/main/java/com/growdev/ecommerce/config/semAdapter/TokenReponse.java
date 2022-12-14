package com.growdev.ecommerce.config.semAdapter;

import com.growdev.ecommerce.dto.user.UserDTO;
import lombok.*;

import java.time.Instant;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TokenReponse {
    private UserDTO userDTO;
    private String token;
    private String timeToken;
    private String expirationDate;
}