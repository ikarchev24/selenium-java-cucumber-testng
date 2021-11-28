Feature: Users wants to create gift voucher

  Scenario: User can create gift voucher
    Given the user is logged as admin
    And the user navigates to gift vouchers menu and deletes any existing vouchers
    When the user creates new voucher
    Then the voucher is successfully created