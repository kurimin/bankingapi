package sunhill.exception;

import org.springframework.http.HttpStatus;

public class BankingApiException extends RuntimeException {

    private HttpStatus errorStatus;

    public BankingApiException(HttpStatus errorStatus) {
        super();
        this.errorStatus = errorStatus;
    }

    public BankingApiException(HttpStatus errorStatus, String message) {
        super(message);
        this.errorStatus = errorStatus;
    }

    public BankingApiException(HttpStatus errorStatus, String message, Throwable cause) {
        super(message, cause);
        this.errorStatus = errorStatus;
    }

    public HttpStatus getErrorStatus() {
        return this.errorStatus;
    }
}

