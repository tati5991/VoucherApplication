package org.qxperts.action;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.qxperts.pojo.CartDetails;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

public class VoucherApplyAction {
    @Autowired
    ScenarioContext scenarioContext;
    public void applyVoucher(Voucher voucher, CartDetails cartDetails) {
        Response response =given()
                .contentType(ContentType.JSON)
                .param("voucherCode", voucher.getCode())
                .body(cartDetails)
                .when()
                .put("/apply")
                .then()
                .extract().response();
        scenarioContext.save(VoucherDataKeys.RESPONSE, response);
    }
}
