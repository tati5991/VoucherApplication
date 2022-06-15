package org.qxperts.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.qxperts.action.VoucherApplyAction;
import org.qxperts.action.VoucherGenerateAction;
import org.qxperts.action.VoucherValidateAction;
import org.qxperts.pojo.CartDetails;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;

@RequiredArgsConstructor
public class ValidateVoucherSteps {
    private final ScenarioContext scenarioContext;

    private final VoucherValidateAction voucherValidateAction;

    private final VoucherGenerateAction voucherGenerateAction;

    @When("customer applies inexistent voucher to his cart")
    public void customerAppliesInexistentVoucherToHisCart() {
        Voucher voucher = voucherGenerateAction.generateInexistentVoucher();
        voucherValidateAction.validateVoucher(voucher);
    }
    @Given("expired voucher is created")
    public void expiredVoucherIsCreated() {
        Voucher voucher = voucherGenerateAction.generateExpiredVoucher();
        voucherGenerateAction.postCreateVoucher(voucher);
        scenarioContext.save(VoucherDataKeys.VOUCHER, voucher);
    }
    @When("customer applies expired voucher to his cart")
    public void applyExpiredVoucher() {
        Voucher voucher = scenarioContext.getData(VoucherDataKeys.VOUCHER);
        voucherValidateAction.validateVoucher(voucher);
    }

    @Then("error message {string} is displayed")
    public void errorMessageIsDisplayed(String expectedMessage) {
        Response response = scenarioContext.getData(VoucherDataKeys.RESPONSE);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String actualMessage = jsonPathEvaluator.get("code");
        Assertions.assertEquals(expectedMessage,actualMessage);
    }
}
