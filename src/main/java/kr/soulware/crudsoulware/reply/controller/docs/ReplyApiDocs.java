package kr.soulware.crudsoulware.reply.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.reply.dto.request.ReplySaveRequestDto;
import kr.soulware.crudsoulware.security.UserDetailsImpl;

@Tag(name = "댓글", description = "댓글 관련 API")
public interface ReplyApiDocs {

    @Operation(summary = "새로운 댓글 작성")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<Long> save(ReplySaveRequestDto requestDto, Long postsId, UserDetailsImpl loginUser);
}
