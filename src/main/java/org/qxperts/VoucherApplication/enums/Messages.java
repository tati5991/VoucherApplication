package org.qxperts.VoucherApplication.enums;

public enum Messages {
    VOUCHER_CREATED("Voucher is created!"),
    VOUCHER_NOT_FOUND("Voucher is not found!"),
    VOUCHER_ALREADY_EXISTS("Voucher already exists!"),
    VOUCHER_IS_EXPIRED("Voucher is expired!"),
    VOUCHER_IS_NOT_ACTIVATED("Voucher is not activated!"),
    VOUCHER_IS_VALID("Voucher is valid!"),

    VOUCHER_IS_APPLIED("Free shipping voucher is successfully applied!"),
    VOUCHER_ALREADY_APPLIED("Free shipping voucher has been already applied!");

    private String messageText;

    Messages(String messageText) {
        this.messageText=messageText;
    }

    public String getMessageText() {
        return messageText;
    }
}
