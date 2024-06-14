package online.shopping.system.cart_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import online.shopping.system.cart_service.exception.constant.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //handle all exceptions
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
//
//        ErrorDetails errorDetails = ErrorDetails.builder()
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .timestamp(LocalDateTime.now())
//                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
//                .errorMessage(ErrorCode.CUSTOMER_NOT_FOUND.name())
//                .path(request.getContextPath())
//                .method("POST")
//                .build();
//
//        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //500 for CustomerNotFoundException
    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(Exception ex, HttpServletRequest request) throws Exception {

        ErrorDetails errorDetails = ErrorDetails.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .errorMessage(ex.getMessage())
                .errorCode(ErrorCode.CUSTOMER_NOT_FOUND.getCode())
                .path(request.getRequestURI())
                .method(request.getMethod())
                .build();

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
