Feature: Test Register feature
  Try to register

  Scenario: Successfully register
    Given Open the main page
    And Go to the register page
    When Enter valid email, username and password
    And Press register button
    Then Register is successful

  Scenario: Register with invalid email
    Given Login page is opened
    And Go to the register page
    When Enter invalid email and valid username, password
    And Press register button
    Then Register is failed

  Scenario: Register when user already exists
    Given Login page is opened
    And Go to the register page
    When Enter an existing email, username and a password
    And Press register button
    Then Register is failed

  Scenario: Register without password
    Given Login page is opened
    And Go to the register page
    When Enter email and username
    And Press register button
    Then Register is failed

  Scenario: Register without username
    Given Login page is opened
    And Go to the register page
    When Enter email and password
    And Press register button
    Then Register is failed

  Scenario: Register without email
    Given Login page is opened
    And Go to the register page
    When Enter username and password
    And Press register button
    Then Register is failed

  Scenario: Register when only email already exists
    Given Login page is opened
    And Go to the register page
    When Enter an existing email, and valid username, password pair
    And Press register button
    Then Register is failed

  Scenario: Register when only username already exists
    Given Login page is opened
    And Go to the register page
    When Enter an existing username, and valid email, password pair
    And Press register button
    Then Register is successful

