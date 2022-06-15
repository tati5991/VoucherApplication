package org.qxperts.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.qxperts.action.VoucherGenerateAction;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;

import java.util.Map;

@RequiredArgsConstructor
public class GenerateVouchersSteps {
    private final ScenarioContext scenarioContext;
    private final VoucherGenerateAction voucherGenerateAction;
    @Given("valid voucher is created")
    public void validVoucherIsGenerated() {
        Voucher voucher = voucherGenerateAction.generateValidVoucher();
        voucherGenerateAction.postCreateVoucher(voucher);
        scenarioContext.save(VoucherDataKeys.VOUCHER, voucher);
    }

    @When("MM generates voucher with details")
    public void mmGeneratesVoucherWithDetails(Map<String, String> voucherDetails) {
        Voucher voucher = new Voucher();
        voucher.setCode(voucherDetails.get("Voucher code"));
        voucher.setValidFrom(voucherDetails.get("Valid From"));
        voucher.setValidUntil(voucherDetails.get("Valid Until"));
        voucher.setRule(voucherDetails.get("Rule"));
        voucherGenerateAction.postCreateVoucher(voucher);
    }
}
