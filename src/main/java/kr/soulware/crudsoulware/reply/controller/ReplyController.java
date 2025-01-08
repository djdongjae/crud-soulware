package kr.soulware.crudsoulware.reply.controller;

import jakarta.validation.Valid;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.exception.SuccessCode;
import kr.soulware.crudsoulware.reply.controller.docs.ReplyApiDocs;
import kr.soulware.crudsoulware.reply.dto.request.ReplySaveRequestDto;
import kr.soulware.crudsoulware.reply.service.ReplyService;
import kr.soulware.crudsoulware.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reply")
public class ReplyController implements ReplyApiDocs {

    private final ReplyService replyService;

    @PostMapping
    public BaseResponse<Long> save(
            @RequestBody @Valid ReplySaveRequestDto requestDto,
            @RequestParam Long postsId,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        final Long data = replyService.save(requestDto, postsId, loginUser);
        return BaseResponse.success(SuccessCode.CREATE_REPLY_SUCCESS, data);
    }
}
