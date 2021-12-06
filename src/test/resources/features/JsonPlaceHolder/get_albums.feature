Feature: Albums
  Scenario: User send get albums request
    When User send get jsonPlaceHolder url request
    And User add albums resource after forward slash
    Then User get status code response 200