package org.qxperts.pojo;

import lombok.Data;
@Data
public class Voucher {
    private String code;
    private String validFrom;
    private String validUntil;
    private String rule;
}
