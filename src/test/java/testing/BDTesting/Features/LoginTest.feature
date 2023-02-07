Feature: Test Login feature
  Try to login

    Scenario Outline: Can you login as user?
      Given Login page is opened
      When User enter email: "<userEmail>" and password: "<password>"
      And Press login
      Then If the "<userEmail>" and the "<password>" are valid, user logged in, else not logged in

      Examples:
        | userEmail | password |
        | invalid   | valid    |
        | valid     | invalid  |
        | valid     | valid    |
        | invalid   | invalid  |
        | empty     | invalid  |
        | empty     | valid    |
        | valid     | empty    |
        | invalid   | empty    |
        | empty     | empty    |


  Scenario Outline: Can you login as admin?
    Given Login page is opened
    When An Admin enter email: "<adminEmail>" and password: "<password>"
    And Press login
    Then If the "<adminEmail>" and the "<password>" are valid, Admin logged in, else not logged in

    Examples:
      | adminEmail | password |
      | invalid   | valid    |
      | valid     | invalid  |
      | valid     | valid    |
      | invalid   | invalid  |
      | empty     | valid    |
      | empty     | invalid  |
      | valid     | empty    |
      | invalid   | empty    |
      | empty     | empty    |

