
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

  Scenario: as a User i want to check if the Ranger Service is up
    Given ranger service is up and running
    When a user calls the ranger Service
    Then the status code is 200

  Scenario: as a User i want to check that location xxx doesn't exits
    Given ranger service is up and running
    When a user tries to get location "xxx"
    Then the status code is 404

  Scenario: as a User i want to make a register on a location that doesn't exits
    Given location "xxx" doesn't exits
    When a user makes a register on location "xxx"
    Then xxxxxxxxxxx
    And payload is empty
