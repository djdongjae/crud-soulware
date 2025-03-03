package kr.soulware.crudsoulware.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.exception.SuccessCode;
import lombok.*;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BaseResponse<T> {
    private final int code;
    private final String message;
    private T data;

    public static BaseResponse success(SuccessCode successCode) {
        return new BaseResponse<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    public static <T> BaseResponse<T> success(SuccessCode successCode, T data) {
        return new BaseResponse<T>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode.getHttpStatusCode(), errorCode.getMessage());
    }

    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getHttpStatusCode(), message);
    }
}
