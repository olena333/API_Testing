Feature: Photos
  Scenario: User send get photos request
    When User send get jsonPlaceHolder url request
    And User add photos resource after forward slash
    Then User get status code response 200