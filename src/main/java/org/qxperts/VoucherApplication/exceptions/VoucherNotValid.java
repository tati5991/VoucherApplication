package org.qxperts.VoucherApplication.exceptions;

import org.springframework.http.HttpStatus;

public class VoucherNotValid extends ValidationExceptionErrors {
    public VoucherNotValid(String message) {
        super(message);
    }
}
