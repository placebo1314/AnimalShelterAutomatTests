Feature: Test Logout feature
  Try to logOut

  Scenario : Can you logOut as user?
    Given Logged in as user
    When Click on the LogOut button
    Then Logged out

  Scenario : Can you logOut as Admin?
    Given Logged in as Admin
    When Click on the LogOut button
    Then Logged out
