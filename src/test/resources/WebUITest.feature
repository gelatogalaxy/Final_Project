Feature: Web UI Testing on https://www.demoblaze.com/

  Scenario: (Negative test) Login using only password
    Given user is on homepage
    When user clicks log in on the menu
    And user input password with "password"
    When user clicks log in button
    Then user sees error "Please fill out Username and Password."

  Scenario: Login using valid username and password
    Given user is on homepage
    When user clicks log in on the menu
    And user input username with "Erien"
    And user input password with "password"
    When user clicks log in button
    Then user sees "Welcome Erien" on home page

  Scenario: User can access Samsung galaxy s6 page
    Given user is on homepage
    And user is logged in as Erien
    When user clicks on product name Samsung galaxy s6
    Then user sees Add to cart button on Samsung Galaxy S6 product page

  Scenario: User can add product to cart
    Given user is on Samsung galaxy s6 product page
    And user is logged in as Erien
    When user clicks on Add to cart button
    Then user sees message "Product added."

  Scenario: User can checkout Samsung galaxy s6
    Given user is on cart page
    And user is logged in as Erien
    When user clicks on Place order button
    And user input details:

      | Name        | Erien             |
      | Country     | Indonesia         |
      | City        | Jakarta           |
      | Credit card | 1234567890        |
      | Month       | 09                |
      | Year        | 2026              |

    And user clicks Purchase button
    Then user sees message "Thank you for your purchase!"
    And user sees "Amount: 360 USD" and "Card Number: 1234567890"


