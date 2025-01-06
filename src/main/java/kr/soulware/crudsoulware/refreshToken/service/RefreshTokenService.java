package kr.soulware.crudsoulware.refreshToken.service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.model.BadRequestException;
import kr.soulware.crudsoulware.refreshToken.dto.TokenResponseDto;
import kr.soulware.crudsoulware.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final JwtProvider jwtProvider;

    public TokenResponseDto generateTokenByRefresh(String token) {
        try {
            String username = jwtProvider.extractUsername(token);
            return jwtProvider.generateToken(username);
        } catch (ExpiredJwtException e) {
            throw new BadRequestException(
                    ErrorCode.TOKEN_TIME_EXPIRED_EXCEPTION,
                    ErrorCode.TOKEN_TIME_EXPIRED_EXCEPTION.getMessage()
            );
        } catch (UsernameNotFoundException e) {
            throw new BadRequestException(
                    ErrorCode.INSUFFICIENT_AUTHENTICATION_EXCEPTION,
                    ErrorCode.INSUFFICIENT_AUTHENTICATION_EXCEPTION.getMessage()
            );
        } catch (SignatureException | MalformedJwtException e) {
            throw new BadRequestException(
                    ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION,
                    ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION.getMessage()
            );
        }
    }
}
