@Hotel
Feature: Hotel account creation

  @HotelCreatorSingle
  Scenario: User can create an account

    Given User is on hotel main page
    When User sign in
    And enter email that is not already taken on authentication page
    And enter name "John", surname "Doe" and password "qwerty123"
    Then User sees success message with text "Your account has been created."
    And close hotel browser


  @HotelCreatorMulti
  Scenario Outline: User can create an account

    Given User is on hotel main page
    When User sign in
    And enter email that is not already taken on authentication page
    And enter name "<name>", surname "<surname>" and password "<passwd>"
    Then User sees success message with text "Your account has been created."
    And close hotel browser
    Examples:
      | name   | surname  | passwd       |
      | Joel   | Doel     | qwerty123    |
      | Salmon | Cucumber | SuperSave123 |


  @HotelLogin
  Scenario Outline: Login in to the hotel-testlab

    Given I'm on hotel main page
    When I enter email address <email> and password <password>
    Then User sees name as <name>
    And close login browser
    Examples:
      | email                  | password  | name   |
      | Kokoshinho123@test.com | qwerty123 | John   |
      | Sajmon123@test.com     | qwerty123 | Sajmon |


    @HotelSearchRooms
    Scenario: Search any hotel room
      Given User is logged on "Kokoshinho123@test.com" with password "qwerty123"
      And is on hotel main page
      When User select a hotel and date
      Then User sees available hotel rooms
      And close search browser


      @HotelBookingRoom
      Scenario: Booking a room
        Given User is logged on "Kokoshinho123@test.com" with password "qwerty123"
        And User select a hotel and date on main page
        And User sees available hotel rooms
        When User add to cart room
        And Buy it
        Then User sees a successful alert "Your order on MyBooking is complete."
        And close search browser

      @HotelDeleteAddress
      Scenario: DeleteAddress
        Given User is logged on "Kokoshinho123@test.com" with password "qwerty123"
        When User delete a address
        Then User sees alert "No addresses are available"
        And close search browser


