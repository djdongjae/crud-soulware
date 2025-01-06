package kr.soulware.crudsoulware.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import kr.soulware.crudsoulware.refreshToken.dto.TokenResponseDto;
import kr.soulware.crudsoulware.refreshToken.entity.RefreshToken;
import kr.soulware.crudsoulware.refreshToken.repository.RefreshTokenRepository;
import kr.soulware.crudsoulware.util.DateConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    private Key getSigningKey() {
        final byte[] keyBytes = jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public LocalDateTime extractExpiration(String token) {
        return DateConvertor.toLocalDateTime(extractClaim(token, Claims::getExpiration));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    public TokenResponseDto generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        String accessToken = generateToken(claims, username, jwtProperties.getAccessTokenExpired());
        String refreshToken = generateToken(claims, username, jwtProperties.getRefreshTokenExpired());
        refreshTokenRepository.save(generateRefreshToken(refreshToken, username));
        return TokenResponseDto.of(accessToken, refreshToken);
    }

    private RefreshToken generateRefreshToken(String refreshToken, String username) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .username(username)
                .build();
    }

    private String generateToken(Map<String, Object> claims, String subject, Long expiryTime) {
        LocalDateTime expiryDate = LocalDateTime.now().plusSeconds(expiryTime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(DateConvertor.toDate(LocalDateTime.now()))
                .setExpiration(DateConvertor.toDate(expiryDate))
                .signWith(getSigningKey(), jwtProperties.getSignatureAlgorithm())
                .compact();
    }

    public boolean validateAccessToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public boolean validateRefreshToken(String token) {
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUsername(extractUsername(token));
        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());
    }

    public Long getTokenExpirationDate() {
        return jwtProperties.getAccessTokenExpired();
    }
}
