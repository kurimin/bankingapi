package sunhill.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import sunhill.model.CustomDateSerializer;

import java.util.Date;

public class BankingApiError {
    private Date date;
    private String message;

    public BankingApiError() {
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
