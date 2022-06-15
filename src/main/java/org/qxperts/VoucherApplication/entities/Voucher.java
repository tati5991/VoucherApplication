package org.qxperts.VoucherApplication.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.qxperts.VoucherApplication.enums.Rules;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Voucher {
    @NotBlank(message = "Voucher code is mandatory")
    @Size(min = 4, max = 20,message = "Voucher must be minimum of 4 characters & maximum of 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message ="Only letters and numbers, _ and - are allowed. No spaces.")
    private String code;

    private LocalDate validFrom;
    private LocalDate validUntil;
    private Rules rule;
}
