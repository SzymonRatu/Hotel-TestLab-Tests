@Google
Feature: Google search

  @GoogleSearch
  Scenario Outline: user can search any keyword
    Given an open browser with google.com
    When a keyword <phrase> is entered in input field
    Then the first one should contain <phrase>
    And close browser
    Examples:
      | phrase   |
      | selenium |
      | youtube  |
      | discord  |

