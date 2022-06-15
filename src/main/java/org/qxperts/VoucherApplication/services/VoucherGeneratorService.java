package org.qxperts.VoucherApplication.services;

import org.qxperts.VoucherApplication.entities.CartDetails;
import org.qxperts.VoucherApplication.entities.Voucher;
import org.qxperts.VoucherApplication.exceptions.VoucherAlreadyApplied;
import org.qxperts.VoucherApplication.exceptions.VoucherAlreadyExists;
import org.qxperts.VoucherApplication.exceptions.VoucherNotFound;
import org.qxperts.VoucherApplication.exceptions.VoucherNotValid;

public interface VoucherGeneratorService {

    Voucher save(Voucher newVoucher) throws VoucherAlreadyExists;

    void validateVoucher(String code) throws VoucherNotFound, VoucherNotValid;

    CartDetails applyVoucher(String code, CartDetails cartDetails) throws VoucherAlreadyApplied, VoucherNotFound;
}
