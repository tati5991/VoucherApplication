package org.qxperts.action;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;

public class VoucherValidateAction {
    @Autowired
    ScenarioContext scenarioContext;
    public void validateVoucher(Voucher voucher) {
        Response response =given()
                .contentType(ContentType.JSON)
                .param("voucherCode", voucher.getCode())
                .when()
                .get("/validate")
                .then()
                .extract().response();
        scenarioContext.save(VoucherDataKeys.RESPONSE, response);
    }
}
