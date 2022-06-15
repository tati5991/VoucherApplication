package org.qxperts.VoucherApplication.services.impl;

import org.qxperts.VoucherApplication.entities.CartDetails;
import org.qxperts.VoucherApplication.entities.Voucher;
import org.qxperts.VoucherApplication.enums.Messages;
import org.qxperts.VoucherApplication.enums.Rules;
import org.qxperts.VoucherApplication.exceptions.VoucherAlreadyApplied;
import org.qxperts.VoucherApplication.exceptions.VoucherAlreadyExists;
import org.qxperts.VoucherApplication.exceptions.VoucherNotFound;
import org.qxperts.VoucherApplication.exceptions.VoucherNotValid;
import org.qxperts.VoucherApplication.repositories.VoucherRepository;
import org.qxperts.VoucherApplication.services.VoucherGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VoucherGeneratorImpl implements VoucherGeneratorService {
    @Autowired
    private VoucherRepository voucherRepository;


    @Override
    public Voucher save(Voucher newVoucher) throws VoucherAlreadyExists {
        return voucherRepository.save(newVoucher);
    }

    @Override
    public void validateVoucher(String code) throws VoucherNotFound, VoucherNotValid {
        Voucher voucher = voucherIsFound(code);
        voucherIsValid(voucher);
    }

    @Override
    public CartDetails applyVoucher(String code, CartDetails cartDetails) throws VoucherAlreadyApplied, VoucherNotFound {
        Voucher voucher = voucherRepository.find(code);
        if (voucher.getRule().equals(Rules.FREE_SHIPPING)&&!cartDetails.isVoucherApplied()) {
            cartDetails.setShippingTotal(0.00);
            cartDetails.setVoucherApplied(true);
            return cartDetails;
        } else if(cartDetails.isVoucherApplied()){
            throw new VoucherAlreadyApplied(Messages.VOUCHER_ALREADY_APPLIED.getMessageText());
        }
        return cartDetails;
    }

    public Voucher voucherIsFound(String code) throws VoucherNotFound {
        return voucherRepository.find(code);
    }

    public void voucherIsValid(Voucher voucher) throws VoucherNotValid {
        //TODO must considerate user's date time
        LocalDate today = LocalDate.now();
        if (today.isAfter(voucher.getValidUntil())) {
            throw new VoucherNotValid(Messages.VOUCHER_IS_EXPIRED.getMessageText());
        } else if (today.isBefore(voucher.getValidFrom())) {
            throw new VoucherNotValid(Messages.VOUCHER_IS_NOT_ACTIVATED.getMessageText());
        }
    }

}
