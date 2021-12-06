Feature: GoRest user api test cases including post, put, get and delete methods

  Background: GoRest user api authentication token is valid

  Scenario Outline: Test the process flow of User API
    Given User api is working and returning 200 status code
    When User adds new user with <name>, <email>,<gender> and <status>
    And System creates new user
    Then System should clean up new user data
    And System should verify that test data user is removed successfully

    Examples:
    |    name    |    email    |    gender    |    status    |
    |"TestUserQ5" |"QAUs23weer02@demo.com"|"Female"|   "active"   |
    |"TestUserQ6" |"QAgregerUs232er01@demo.com"|"Female"|   "active"   |
    |"TestUserQ7" |"QAUsergere123r03@demo.com"|"Female"|   "active"   |

    Scenario Outline: Process flow to update user information
      Given User api is working and returning 200 status code
      When User wants to add new user with <name>, <email>, <gender> and <status>
      Then User wants to update the <updatedEmail> in user details
      And System can verify the new user is added succesfully
      Then System should clean up new user data
      And System should verify that test data user is removed successfully

      Examples:
      |name       |email                |gender   |status   |updatedEmail         |
      |"TestUserQ"|"test789@demo.com"   |"male"   |"active" |"updated789@demo.com |
