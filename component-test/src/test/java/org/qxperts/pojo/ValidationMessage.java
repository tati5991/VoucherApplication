package org.qxperts.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationMessage)) return false;
        ValidationMessage message1 = (ValidationMessage) o;
        return Objects.equals(message, message1.message) && Objects.equals(details, message1.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, details);
    }

    @Override
    public String toString() {
        return "ValidationMessage{" +
                "message='" + message + '\'' +
                ", details=" + details +
                '}';
    }
}
