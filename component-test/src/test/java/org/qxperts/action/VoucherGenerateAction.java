package org.qxperts.action;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifmif.common.regex.Generex;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.qxperts.pojo.CartDetails;
import org.qxperts.pojo.Voucher;
import org.qxperts.utils.ScenarioContext;
import org.qxperts.utils.VoucherDataKeys;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

@Slf4j
public class VoucherGenerateAction {
    @Autowired
    ScenarioContext scenarioContext;

    public void postCreateVoucher(Voucher voucher) {
        String requestBody = generateJson(voucher);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/create")
                .then().assertThat()
                .extract().response();

        scenarioContext.save(VoucherDataKeys.RESPONSE, response);
    }

    public Voucher generateValidVoucher() {
        Voucher voucher = new Voucher();
        voucher.setCode(generateCode());
        voucher.setValidFrom("2022-06-10");
        voucher.setValidUntil("2022-06-10");
        voucher.setRule("FREE_SHIPPING");
        return voucher;
    }

    public Voucher generateInexistentVoucher() {
        Voucher voucher = new Voucher();
        voucher.setCode("INEXISTENT");
        voucher.setValidFrom("2022-06-10");
        voucher.setValidUntil("2022-06-30");
        voucher.setRule("FREE_SHIPPING");
        return voucher;
    }

    public Voucher generateExpiredVoucher() {
        Voucher voucher = new Voucher();
        voucher.setCode(generateCode());
        voucher.setValidFrom("2022-06-10");
        voucher.setValidUntil("2022-06-10");
        voucher.setRule("FREE_SHIPPING");
        return voucher;
    }

    public String generateJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    private String generateCode() {
        Generex generex = new Generex("[a-zA-Z0-9_]{10}");
        return generex.random();
    }
}
