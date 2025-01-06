package kr.soulware.crudsoulware.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.soulware.crudsoulware.exception.ErrorCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static kr.soulware.crudsoulware.util.ServletErrorResponse.setErrorResponse;

@Slf4j
@Component
public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.error("JWT 토큰 만료: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.TOKEN_TIME_EXPIRED_EXCEPTION);
        } catch (UsernameNotFoundException e) {
            log.error("JWT 상 사용자 찾기 실패: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.AUTHORIZE_FAILED_EXCEPTION);
        } catch (SignatureException e) {
            log.error("JWT 토큰 서명 이상: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION);
        } catch (MalformedJwtException e) {
            log.error("JWT 토큰 형식 이상: {}", e.getMessage(), e);
            setErrorResponse(response, ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION);
        }
    }
}
