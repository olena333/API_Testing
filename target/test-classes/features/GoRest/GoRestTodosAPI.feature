Feature: Feature: GoRest todos api test cases including post, put, get and delete methods

  Background: GoRest todos api authentication token is valid

  Scenario Outline: Test the process flow of Todos API
    Given Todos api is working and returning 200 status code
    When User adds new todo with <user_id>, <title>, <due_on> and <status>
    And System creates new todo
    Then System should clean up new todo data
    Then System should verify that test data todos is created successfully

    Examples:
      |user_id | title                           |   due_on                           | status   |
      |8       | "Amplitudo absconditus 31....." |  "2021-12-06T00:00:00.000+05:30"   | "pending"|
      |12      | "Amplitudo absconditus 32....." |  "2021-12-06T00:00:00.000+05:30"   | "pending"|
      |13      | "Amplitudo absconditus 32....." |  "2021-12-06T00:00:00.000+05:30"   | "pending"|