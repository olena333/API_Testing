Feature: Todos
  Scenario: User send get todos request
    When User send get jsonPlaceHolder url request
    And User add todos resource after forward slash
    Then User get status code response 200