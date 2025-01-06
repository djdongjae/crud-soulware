package kr.soulware.crudsoulware.refreshToken.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.refreshToken.dto.TokenResponseDto;
import org.springframework.web.bind.annotation.RequestHeader;

@Tag(name = "리프레쉬 토큰", description = "리프레쉬 토큰 관련 API")
public interface RefreshTokenApiDocs {

    @Operation(summary = "리프레쉬 토큰으로 토큰 재발급 받기")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<TokenResponseDto> refresh(@RequestHeader(value = "Authorization-Refresh") String refreshToken);
}
