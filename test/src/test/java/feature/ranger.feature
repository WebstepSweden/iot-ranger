
Feature: Ranger
  Scenario: User calls web service to get list on all locations
    Given ranger service is up and running
    When a user retrieves List of location
    Then the status code is 200

  @CleanUpCreateLocation
  Scenario: as a User i want to create a location called orvar
    Given ranger service is up and running
    When a user creates a location called "orvar"
    Then the status code is 200
    And location "orvar" is created

