package org.qxperts.VoucherApplication.exceptions;

import java.util.function.Supplier;

public class VoucherNotFound extends ValidationExceptionErrors{

    public VoucherNotFound(String message) {
        super(message);
    }

}
