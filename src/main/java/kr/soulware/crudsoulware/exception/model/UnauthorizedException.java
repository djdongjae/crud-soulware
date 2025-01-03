package kr.soulware.crudsoulware.exception.model;

import kr.soulware.crudsoulware.exception.ErrorCode;

public class UnauthorizedException extends CustomException{
    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
