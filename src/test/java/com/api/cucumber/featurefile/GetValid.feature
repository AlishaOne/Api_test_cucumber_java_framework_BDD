Feature: Get request
  Get request return all data with json format,Get request return all data with xml format,Get request return xml format data with id,
  Get request return json format data with id

  Scenario: Get request return all data with json format
    Given accept json format
    When I do Get request
    Then response status code should be "200"

  Scenario: Get request return all data with xml format
    Given accept xml format
    When I do Get request
    Then response status code should be "200"
  #Scenario: Get request return json format data with id
    #Given accept json format
    #When I do Get request with id "660"
    #Then response status code should be "200" and response json body Id should be "660"
    #And response body should be json format as body start with "{" and end with "}"
#
  #Scenario: Get request return xml format data with id
    #Given accept xml format
    #When I do Get request with id "114"
    #Then response status code should be "200" and response xml body Id should be "114"
    #And response body should be xml format as body contains "<Laptop><BrandName>"
