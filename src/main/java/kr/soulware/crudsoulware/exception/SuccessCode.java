package kr.soulware.crudsoulware.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK
     */
    GET_POST_SUCCESS(HttpStatus.OK, "성공적 게시글을 조회했습니다"),
    UPDATE_POST_SUCCESS(HttpStatus.OK, "성공적으로 게시글을 수정했습니다"),


    /**
     * 201 CREATED
     */
    CREATE_POST_SUCCESS(HttpStatus.OK, "성공적으로 게시글을 생성했습니다"),

    /**
     * 204 NO_CONTENT
     */
    DELETE_POST_SUCCESS(HttpStatus.OK, "성공적으로 게시글을 삭제했습니다")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
