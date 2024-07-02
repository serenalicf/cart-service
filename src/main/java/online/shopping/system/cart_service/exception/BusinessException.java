package online.shopping.system.cart_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BusinessException extends Exception {
    private final String errorCode;

    public BusinessException(ExceptionCode exceptionCode, Object...arguments) {
        super(exceptionCode.getMessage(arguments));
        this.errorCode = exceptionCode.getErrorCode();
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

}
