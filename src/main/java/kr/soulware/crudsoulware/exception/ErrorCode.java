package kr.soulware.crudsoulware.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 400 BAD REQUEST
     * */
    REQUEST_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
    VALIDATION_REQUEST_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_HEADER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 헤더값이 입력되지 않았습니다."),
    VALIDATION_REQUEST_PARAMETER_MISSING_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 파라미터값이 입력되지 않았습니다."),
    REQUEST_METHOD_VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "요청 메소드가 잘못됐습니다."),
    DUPLICATE_EMAIL_EXCEPTION(HttpStatus.BAD_REQUEST, "이미 사용중인 이메일입니다."),

    /**
     * 401 UNAUTHORIZED
     */
    TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "시간이 만료된 토큰입니다."),
    TOKEN_SIGNATURE_INVALID_EXCEPTION(HttpStatus.UNAUTHORIZED, "형식이 잘못된 토큰입니다."),
    AUTHORIZE_FAILED_EXCEPTION(HttpStatus.UNAUTHORIZED, "사용자 인증에 실패하였습니다."),
    INSUFFICIENT_AUTHENTICATION_EXCEPTION(HttpStatus.UNAUTHORIZED, "인증이 필요한 URI로 요청하였습니다."),
    INSUFFICIENT_AUTHENTICATION_TO_POSTS(HttpStatus.UNAUTHORIZED, "해당 글에 대한 접근 권한이 없습니다."),

    /**
     * 403 FORBIDDEN
     */
    FORBIDDEN_REQUEST_EXCEPTION(HttpStatus.FORBIDDEN, "접근 권한이 없는 요청입니다."),

    /**
     * 404 NOT FOUND
     * */
    NOT_FOUND_POST_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    /**
     * 500 INTERNAL SERVER ERROR
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
