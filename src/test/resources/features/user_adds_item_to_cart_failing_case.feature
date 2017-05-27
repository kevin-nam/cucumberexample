Feature: User adds items to his/her cart

  Scenario: User searches for an item and adds it to his/her cart
    Given I am using a Google Chrome Browser
    And I am on the Amazon front page
    When I search for "sadklcacd"
    And Choosing the first product found in the results
    And I press "Add to cart"
    Then The item should be successfully added to cart


