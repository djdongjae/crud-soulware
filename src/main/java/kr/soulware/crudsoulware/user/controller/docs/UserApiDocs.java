package kr.soulware.crudsoulware.user.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.user.dto.request.SignInRequestDto;
import kr.soulware.crudsoulware.user.dto.request.SignUpRequestDto;

@Tag(name = "유저", description = "유저 관련 API")
public interface UserApiDocs {

    @Operation(summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<Long> save(SignUpRequestDto requestDto);

    @Operation(summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<String> signIn(SignInRequestDto requestDto);
}
