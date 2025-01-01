package kr.soulware.crudsoulware.exception.model;

import kr.soulware.crudsoulware.exception.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
