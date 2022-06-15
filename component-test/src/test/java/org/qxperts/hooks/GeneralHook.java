package org.qxperts.hooks;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.qxperts.CucumberSpringContextConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@CucumberContextConfiguration
@ContextConfiguration(classes = {CucumberSpringContextConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class GeneralHook {

    @AfterAll
    public static void resetRestAssuredConfiguration() {
        RestAssured.reset();
    }

    @Before
    public void setUpApplicationTestBaseURI() {
        RestAssured.baseURI = "http://localhost:8080/voucher";
    }
}
