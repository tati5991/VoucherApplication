package org.qxperts.VoucherApplication.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetails {
    private boolean voucherApplied;
    private Double shippingTotal;
    //other cart details may be added
}
