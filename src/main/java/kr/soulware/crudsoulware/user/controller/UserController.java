package kr.soulware.crudsoulware.user.controller;

import jakarta.validation.Valid;
import kr.soulware.crudsoulware.common.dto.BaseResponse;
import kr.soulware.crudsoulware.exception.SuccessCode;
import kr.soulware.crudsoulware.user.controller.docs.UserApiDocs;
import kr.soulware.crudsoulware.user.dto.request.SignInRequestDto;
import kr.soulware.crudsoulware.user.dto.request.SignUpRequestDto;
import kr.soulware.crudsoulware.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController implements UserApiDocs {

    private final UserService userService;

    @PostMapping
    public BaseResponse<Long> save(@RequestBody @Valid SignUpRequestDto requestDto) {
        final Long data = userService.save(requestDto);
        return BaseResponse.success(SuccessCode.CREATE_USER_SUCCESS, data);
    }

    @PostMapping("/sign-in")
    public BaseResponse<String> signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        final String data = userService.signIn(requestDto);
        return BaseResponse.success(SuccessCode.SIGN_IN_USER_SUCCESS, data);
    }
}
