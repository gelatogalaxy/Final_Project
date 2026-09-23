@api
Feature: API Test on https://dummyapi.io/data/v1/ using hardcoded token

  Scenario: Create User
    Given the endpoint path is "/user/create"
    When I submit POST request with this body:
      """
      {
        "firstName": "Acker",
        "lastName": "TTT",
        "email": "acker@gmail.com"
      }
      """
    Then status code should be 200
    And response field "firstName" should be "Acker"
    And extract response field id into newUserID

  Scenario: Get User by ID
    Given the endpoint path is "user/" + newUserID
    When I submit GET request
    Then status code should be 200
    And response field "firstName" should be "Acker"

  Scenario: Update User
    Given the endpoint path is "user/" + newUserID
    When I submit PUT request with this body:
      """
      {
      "firstName": "Joanne"
      }
      """

    Then status code should be 200
    And response field "firstName" should be "Joanne"


  Scenario: Delete User
    Given the endpoint path is "user/" + newUserID
    When I submit DELETE request
    Then status code should be 200
    And response field "id" should be newUserID

  Scenario: (Negative Test) Get Already Deleted User
    Given the endpoint path is "user/" + newUserID
    When I submit GET request
    Then status code should be 404
    And response field "error" should be "RESOURCE_NOT_FOUND"

  Scenario: Get List of Tags
    Given the endpoint path is "tag/"
    When I submit GET request
    Then status code should be 200
    And the response field "data" contains "#API"