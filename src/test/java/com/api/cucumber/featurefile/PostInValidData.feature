Feature: create a latop with invalid data

  Scenario: create a laptop with empty data
    Given latop json data as empty body "empty body" and header accept json
    When I do post request
    Then response status code should be bad request "400"
