package org.qxperts.VoucherApplication.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ValidationMessage {
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object details;

    public ValidationMessage() {
    }

    public ValidationMessage(String message) {
        this.message = message;
    }

    public ValidationMessage(String message, Object details) {
        this.message = message;
        this.details = details;
    }
}
