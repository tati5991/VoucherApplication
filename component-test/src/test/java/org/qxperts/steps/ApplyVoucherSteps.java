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
import org.qxperts.pojo.CartDetails;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;

@RequiredArgsConstructor
public class ApplyVoucherSteps {
    private final ScenarioContext scenarioContext;

    private final VoucherApplyAction voucherApplyAction;

    private final VoucherGenerateAction voucherGenerateAction;

    @Given("customer's cart has shipping cost {double} CU")
    public void createcustomerCart(double shippingTotal) {
        CartDetails cartDetails = new CartDetails();
        cartDetails.setVoucherApplied(false);
        cartDetails.setShippingTotal(shippingTotal);
        scenarioContext.save(VoucherDataKeys.CART_DETAILS, cartDetails);
    }
    @Given("voucher is applied")
    public void voucherIsApplied() {
        Response response = scenarioContext.getData(VoucherDataKeys.RESPONSE);
        JsonPath jsonPathEvaluator = response.jsonPath();
        boolean isVoucherApplied = jsonPathEvaluator.get("details.voucherApplied");
        Assertions.assertTrue(isVoucherApplied);
    }

    @When("customer try to apply voucher for the second time")
    public void customerHasBeenAlreadyAppliedAVoucher() {
        Voucher voucher = voucherGenerateAction.generateValidVoucher();
        voucherGenerateAction.postCreateVoucher(voucher);
        CartDetails cartDetails = new CartDetails();
        cartDetails.setVoucherApplied(true);
        cartDetails.setShippingTotal(0.00);
        voucherApplyAction.applyVoucher(voucher, cartDetails);
    }

    @When("customer applies the voucher to his cart")
    public void customerAppliesTheVoucherToHisCart() {
        Voucher voucher = scenarioContext.getData(VoucherDataKeys.VOUCHER);
        CartDetails cartDetails = scenarioContext.getData(VoucherDataKeys.CART_DETAILS);
        voucherApplyAction.applyVoucher(voucher, cartDetails);

    }

    @Then("message {string} is displayed")
    public void messageIsDisplayed(String expectedMessage) {
        Response response = scenarioContext.getData(VoucherDataKeys.RESPONSE);
        JsonPath jsonPathEvaluator = response.jsonPath();
        String actualMessage = jsonPathEvaluator.get("message");
        Assertions.assertEquals(expectedMessage,actualMessage);
    }



    @Then("shipping cost is {double} CU")
    public void shippingCostIsSetTo(double expectedShippingTotal) {
        Response response = scenarioContext.getData(VoucherDataKeys.RESPONSE);
        JsonPath jsonPathEvaluator = response.jsonPath();
        float actualShippingTotal = jsonPathEvaluator.get("details.shippingTotal");
        Assertions.assertEquals(actualShippingTotal, expectedShippingTotal);
    }

}
