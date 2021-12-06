Feature: Posts
  Scenario: User send get posts request
    When User send get jsonPlaceHolder url request
    And User add posts resource after forward slash
    Then User get status code response 200