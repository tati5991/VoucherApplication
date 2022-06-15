Feature: Marketing manager generates free shipping voucher
  # MM - abbreviation from marketing manager
  @Positive @GenerateVoucher
  Scenario: Marketing manager creates voucher for free shipping marketing campaign
    When MM generates voucher with details
      | Voucher code | FREESHIPPING2020 |
      | Valid From   | 2022-06-10       |
      | Valid Until  | 2022-06-12       |
      | Rule         | FREE_SHIPPING    |
    Then message 'Voucher is created!' is displayed

  @Negative
  Scenario: MM can't create voucher with voucher code that exists already
    #Given step - generate voucher, to ensure independence
    When MM generates voucher with details
      | Voucher code | FREESHIPPING2020 |
      | Valid From   | 2022-06-10       |
      | Valid Until  | 2022-06-12       |
      | Rule         | FREE_SHIPPING    |
    Then message 'Voucher already exists!' is displayed

  Scenario Outline: MM is notified when fail to create voucher and display the reason
    Given MM generates voucher with details
      | Voucher code | <Voucher_Code> |
      | Valid From   | <Valid_From>   |
      | Valid Until  | <Valid_Until>  |
      | Rule         | <Rule>         |
    Then error message '<Message>' is displayed
    Examples:
      | Voucher_Code                    | Valid_From | Valid_Until | Rule          | Message                                                            |
      | FREESHIPPINGG 124               | 2022-06-10 | 2022-06-12  | FREE_SHIPPING | Only letters and numbers, _ and - are allowed. No spaces.          |
      | FRE                             | 2022-06-10 | 2022-06-12  | FREE_SHIPPING | Voucher must be minimum of 4 characters & maximum of 20 characters |
      | FREESHIPPING1564561654654878725 | 2022-06-10 | 2022-06-12  | FREE_SHIPPING | Voucher must be minimum of 4 characters & maximum of 20 characters |
      |                                 | 2022-06-10 | 2022-06-12  | FREE_SHIPPING | Voucher code is mandatory                                          |
