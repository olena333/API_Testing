Feature: GoRest Posts api testcases including POST, PUT, GET and DELETE methods

  Background: GoRest Posts api authentication is valid

  Scenario Outline: Test the process flow of Posts API
    Given  Posts api is working and returning 200 status code
    When   User wants to add new Posts with <user_id>,<title> and <body>
    And    System can verify the new posts is add successfully
    Then   System should cleanUp new posts data
    And    System should verify that test data posts is removed successfully
    Examples:
      |user_id | title         |   body                                     |
      |9      | "TestPost205" |  "Consectetur tergo carmen POST TEST205."  |


  Scenario Outline: process flow to update posts information
    Given  Posts api is working and returning 200 status code
    When   User wants to add new Posts with <user_id>,<title> and <body>
    Then   User should wants to update the <UpdatedTitle> in the user details
    And    System can verify the new posts is add successfully
    Then   System should cleanUp new posts data
    And    System should verify that test data posts is removed successfully

    Examples:
      |user_id | title         |   body                                     |UpdatedTitle  |
      |9      | "TestPost205" |  "Consectetur tergo carmen POST TEST123."  |"TestPost206" |