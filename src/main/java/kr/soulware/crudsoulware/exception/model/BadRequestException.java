package kr.soulware.crudsoulware.exception.model;

import kr.soulware.crudsoulware.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
