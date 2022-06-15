Feature: Free shipping voucher validation

  Scenario: Customer is informed if voucher is not found
    When customer applies inexistent voucher to his cart
    Then message 'Voucher is not found!' is displayed

  Scenario: customer is informed if voucher is expired
    Given expired voucher is created
    When customer applies expired voucher to his cart
    Then message 'Voucher is expired!' is displayed