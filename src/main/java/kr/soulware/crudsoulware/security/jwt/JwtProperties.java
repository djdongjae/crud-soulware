package kr.soulware.crudsoulware.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class JwtProperties {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.signatureAlgorithm}")
    private SignatureAlgorithm signatureAlgorithm;

    @Value("${jwt.accessTokenExpired}")
    private Long accessTokenExpired;

    @Value("${jwt.refreshTokenExpired}")
    private Long refreshTokenExpired;
}
