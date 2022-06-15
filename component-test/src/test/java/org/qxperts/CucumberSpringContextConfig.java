package org.qxperts;

import org.qxperts.action.VoucherApplyAction;
import org.qxperts.action.VoucherGenerateAction;
import org.qxperts.action.VoucherValidateAction;
import org.qxperts.utils.ScenarioContext;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class CucumberSpringContextConfig {

    @Bean
    public ScenarioContext scenarioContext() {
        return new ScenarioContext();
    }

    @Bean
    public VoucherGenerateAction voucherGenerateAction() {
        return new VoucherGenerateAction();
    }

    @Bean
    public VoucherApplyAction voucherApplyAction() {
        return new VoucherApplyAction();
    }

    @Bean
    public VoucherValidateAction voucherValidateAction() {
        return new VoucherValidateAction();
    }
}