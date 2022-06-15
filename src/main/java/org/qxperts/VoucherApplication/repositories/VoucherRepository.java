package org.qxperts.VoucherApplication.repositories;

import org.qxperts.VoucherApplication.entities.Voucher;
import org.qxperts.VoucherApplication.enums.Messages;
import org.qxperts.VoucherApplication.exceptions.VoucherAlreadyExists;
import org.qxperts.VoucherApplication.exceptions.VoucherNotFound;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VoucherRepository {
    private List<Voucher> vouchers = new ArrayList<>();

    public Voucher save(Voucher newVoucher) throws VoucherAlreadyExists {
        try {
            find(newVoucher.getCode());
        } catch (VoucherNotFound e) {
            vouchers.add(newVoucher);
            return newVoucher;
        }
        throw new VoucherAlreadyExists(Messages.VOUCHER_ALREADY_EXISTS.getMessageText());
    }

    public Voucher find(String voucherCode) throws VoucherNotFound {
        return vouchers.stream()
                .filter(voucher -> voucher.getCode().equals(voucherCode))
                .findAny()
                .orElseThrow(() -> new VoucherNotFound(Messages.VOUCHER_NOT_FOUND.getMessageText()));
    }
}
