package kr.soulware.crudsoulware.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    @Size(min = 1, max = 20, message = "이름은 한 글자 이상 20글자 이하로 입력하세요.")
    private String name;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식이 잘못되었습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[+=%_!@#$^&*?]).{8,20}$", message = "8~20 길이의 알파벳과 숫자, 특수문자만 사용할 수 있습니다.")
    private String password;
}
