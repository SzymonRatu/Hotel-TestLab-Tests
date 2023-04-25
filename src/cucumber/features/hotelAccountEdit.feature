@Hotel
  Feature: User account edit

    @edit
    Scenario: User adds first address to the account
      Given I'm on the hotel main page
      When I go to login page
      And I login using "Kokoshinho123@test.com" and "qwerty123"
      And I go to my addresses page
      And I can see there is no addresses
      And I add new address
      And I enter new address "First address", "Street", "12-329", "City" , "123456789"
      Then I can see new address
      And I verify created address "First address", "Street", "12-329", "City" , "123456789"
      And I remove the address
      And I can see the address was removed
      And I close the browser