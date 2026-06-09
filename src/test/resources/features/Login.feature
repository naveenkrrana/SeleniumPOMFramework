Feature: User Authentication
  As a customer of SauceDemo,
  I want to log into the application
  So that I can purchase merchandise.

  # Scenario Outline allows us to loop this test across multiple data sets
  Scenario Outline: Successful Login with Valid Credentials
    Given the user is on the SauceDemo login page
    # Bracketed variables map directly to the columns in the Examples table below
    When the user enters the username "<username>" and password "<password>"
    And the user clicks the login button
    Then the user should be redirected to the inventory page

    # Data sets that drive the scenario variables
    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | problem_user            | secret_sauce |
      | performance_glitch_user | secret_sauce |