package kr.soulware.crudsoulware.posts.controller.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.posts.dto.request.PostsSaveRequestDto;
import kr.soulware.crudsoulware.posts.dto.request.PostsUpdateRequestDto;
import kr.soulware.crudsoulware.posts.dto.response.PostResponseDto;
import kr.soulware.crudsoulware.posts.dto.response.PostsWithRepliesResponseDto;
import kr.soulware.crudsoulware.security.UserDetailsImpl;

import java.util.List;

@Tag(name = "게시글", description = "게시글 관련 API")
public interface PostsApiDocs {

    @Operation(summary = "새로운 게시글 작성")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<Long> save(PostsSaveRequestDto requestDto, UserDetailsImpl loginUser);

    @Operation(summary = "특정 게시글 수정")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<Long> update(Long id, PostsUpdateRequestDto requestDto, UserDetailsImpl loginUser);

    @Operation(summary = "특정 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<PostResponseDto> find(Long id);

    @Operation(summary = "전체 게시글 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<List<PostResponseDto>> findAll();

    @Operation(summary = "특정 게시글 삭제")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<Long> delete(Long id, UserDetailsImpl loginUser);

    @Operation(summary = "특정 게시글 댓글과 함께 조회")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "요청이 정상적으로 처리되었을 때"
            )
    })
    BaseResponse<PostsWithRepliesResponseDto> findWithReplies(Long id);
}
