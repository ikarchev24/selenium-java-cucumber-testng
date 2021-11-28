Feature: User wants to be able to leave a feedback for specific product

  Scenario: Verify user is able to write a review for a product and is able to see it after admin approves it
    Given the user opens specific product page
    When the user writes a review for that product
    And when an admin logins to the backend
    And the admin navigates to reviews page
    Then the admin sees the review for the product is present
    And when the admin approves the review
    And the user navigates back to the respective product page
    Then the user sees number of reviews incremented by one