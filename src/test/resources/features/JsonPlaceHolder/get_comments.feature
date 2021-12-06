Feature: Comments
  Scenario: User send get comments request
    When User send get jsonPlaceHolder url request
    And User add comments resource after forward slash
    Then User get status code response 200