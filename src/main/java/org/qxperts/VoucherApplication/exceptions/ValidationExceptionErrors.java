package org.qxperts.VoucherApplication.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ValidationExceptionErrors extends Exception {
    public ValidationExceptionErrors(String message) {
        super(message);
    }
}
