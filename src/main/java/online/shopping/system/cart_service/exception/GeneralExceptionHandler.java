package online.shopping.system.cart_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    //500 for CustomerNotFoundException
    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<ErrorDetails> handleBusinessException (Exception ex, HttpServletRequest request) throws Exception {
        //cast the ex to BusinessException object
        BusinessException bex = (BusinessException) ex;

        // get the error code and error message from the BusinessException object
        String errorCode = bex.getErrorCode();
        String errorMessage = bex.getMessage();

        ErrorDetails errorDetails = ErrorDetails.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errorMessage(errorMessage)
                .errorCode(errorCode)
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
