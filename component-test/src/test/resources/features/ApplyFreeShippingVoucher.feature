Feature: Customer applies Free Shipping voucher received previously

  # CU - abbreviation from currency units
  Scenario: Customer successfully applies free shipping voucher to his cart
    Given valid voucher is created
    * customer's cart has shipping cost 2.00 CU
    When customer applies the voucher to his cart
    Then message 'Free shipping voucher is successfully applied!' is displayed
    * shipping cost is 0.00 CU
    * voucher is applied

  Scenario: Customer can apply free shipping voucher only once to his cart
    When customer try to apply voucher for the second time
    Then message 'Free shipping voucher has been already applied!' is displayed