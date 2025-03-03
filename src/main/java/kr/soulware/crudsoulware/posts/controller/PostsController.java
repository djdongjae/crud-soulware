package kr.soulware.crudsoulware.posts.controller;

import jakarta.validation.Valid;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.exception.SuccessCode;
import kr.soulware.crudsoulware.posts.controller.docs.PostsApiDocs;
import kr.soulware.crudsoulware.posts.dto.request.PostsSaveRequestDto;
import kr.soulware.crudsoulware.posts.dto.request.PostsUpdateRequestDto;
import kr.soulware.crudsoulware.posts.dto.response.PostResponseDto;
import kr.soulware.crudsoulware.posts.dto.response.PostsWithRepliesResponseDto;
import kr.soulware.crudsoulware.posts.service.PostsService;
import kr.soulware.crudsoulware.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostsController implements PostsApiDocs {

    private final PostsService postsService;

    @PostMapping
    public BaseResponse<Long> save(
            @RequestBody @Valid PostsSaveRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        final Long data = postsService.save(requestDto, loginUser);
        return BaseResponse.success(SuccessCode.CREATE_POST_SUCCESS, data);
    }

    @PatchMapping("/{id}")
    public BaseResponse<Long> update(
            Long id,
            @RequestBody @Valid PostsUpdateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        final Long data = postsService.update(id, requestDto, loginUser);
        return BaseResponse.success(SuccessCode.UPDATE_POST_SUCCESS, data);
    }

    @GetMapping("/{id}")
    public BaseResponse<PostResponseDto> find(@PathVariable Long id) {
        final PostResponseDto data = postsService.find(id);
        return BaseResponse.success(SuccessCode.GET_POST_SUCCESS, data);
    }

    @GetMapping
    public BaseResponse<List<PostResponseDto>> findAll() {
        final List<PostResponseDto> data = postsService.findAll();
        return BaseResponse.success(SuccessCode.GET_POST_SUCCESS, data);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Long> delete(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl loginUser
    ) {
        final Long data = postsService.delete(id, loginUser);
        return BaseResponse.success(SuccessCode.DELETE_POST_SUCCESS, data);
    }

    @GetMapping("/{id}/replies")
    public BaseResponse<PostsWithRepliesResponseDto> findWithReplies(@PathVariable Long id) {
        final PostsWithRepliesResponseDto data = postsService.findWithReplies(id);
        return BaseResponse.success(SuccessCode.GET_POST_SUCCESS, data);
    }
}
