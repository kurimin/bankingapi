package sunhill.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class BankingApiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BankingApiException.class)
    protected ResponseEntity<BankingApiError> handleException(BankingApiException ex, WebRequest request) {
        BankingApiError error = new BankingApiError();
        error.setDate(new Date());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<BankingApiError>(error, ex.getErrorStatus());
    }
}
