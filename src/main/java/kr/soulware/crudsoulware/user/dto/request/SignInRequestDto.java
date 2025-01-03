package kr.soulware.crudsoulware.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequestDto {

    @NotBlank(message = "이메일을 입력하세요.")
    private String username;

    @NotBlank(message = "패스워드를 입력하세요.")
    private String password;
}
