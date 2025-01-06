package kr.soulware.crudsoulware.refreshToken.controller;

import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.SuccessCode;
import kr.soulware.crudsoulware.exception.model.BadRequestException;
import kr.soulware.crudsoulware.refreshToken.controller.docs.RefreshTokenApiDocs;
import kr.soulware.crudsoulware.refreshToken.dto.TokenResponseDto;
import kr.soulware.crudsoulware.refreshToken.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/refresh")
@RestController
public class RefreshTokenController implements RefreshTokenApiDocs {

    private final RefreshTokenService refreshTokenService;

    @GetMapping
    public BaseResponse<TokenResponseDto> refresh(@RequestHeader(value = "Authorization-Refresh") String refreshToken) {
        final TokenResponseDto data = refreshTokenService.generateTokenByRefresh(getRefreshToken(refreshToken));
        return BaseResponse.success(SuccessCode.SIGN_IN_USER_SUCCESS, data);
    }

    private String getRefreshToken(String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            throw new BadRequestException(
                    ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION,
                    ErrorCode.TOKEN_SIGNATURE_INVALID_EXCEPTION.getMessage()
            );
        }
    }
}
