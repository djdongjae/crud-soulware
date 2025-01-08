package kr.soulware.crudsoulware.reply.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplySaveRequestDto {

    @NotBlank(message = "내용을 입력하세요.")
    private String content;
}