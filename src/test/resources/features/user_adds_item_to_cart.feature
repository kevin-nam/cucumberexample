Feature: User adds items to his/her cart

  Scenario: User adds a known item to his/her cart
    Given I am using a Google Chrome Browser
    And I am on the Amazon front page
    When Navigating to a known product page
    And I press "Add to cart"
    Then The item should be successfully added to cart

  Scenario: User searches for an item and adds it to his/her cart
    Given I am using a Google Chrome Browser
    And I am on the Amazon front page
    When I search for "lamp"
    And Choosing the first product found in the results
    And I press "Add to cart"
    Then The item should be successfully added to cart

  Scenario: User searches for an item with size and adds it to his/her cart
    Given I am using a Google Chrome Browser
    And I am on the Amazon front page
    When I search for "boxing gloves"
    And Choosing the first product found in the results
    And Selecting the first size available
    And I press "Add to cart"
    Then The item should be successfully added to cart

