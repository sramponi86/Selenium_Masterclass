Feature: Shopping automation
  Scenario: Testing the authentication
    Given I go to the website
    When I click on the signIn button
    And I specify my credential and click login
    Then I can login into the website