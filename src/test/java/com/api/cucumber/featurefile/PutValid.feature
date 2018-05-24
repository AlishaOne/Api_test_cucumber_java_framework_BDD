@put
Feature: updata a latop with json or xml format data or from file

  Scenario: udata a laptop with json data
    Given latop json data BrandName as "Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad" LaptopName as "Mac Pro" and header accept json
    When I do post request
    Then response body whould be correct as BrandName is"Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad" LaptopName as "Mac Pro"
    And response post status code should be right as "200"
    Given latop json data for update BrandName as "Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth" LaptopName as "Mac Pro" and header accept json
    When I do put request
    Then response update body whould be correct as BrandName is"Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth" LaptopName as "Mac Pro"
    And response update status code should be right as "200"

  Scenario: update a laptop with xml data
    Given latop xml data as "Dell xml" and header accept xml
    When I do post request
    Then response body whould be correct as xml "Dell xml"
    And response post status code should be right as "200"
    Given latop xml updte data BrandName as "updatae" and header accept xml
    When I do put request
    Then response update body should be correct as xml "update"
    And response update status code should be right as "200"

  Scenario: udata a laptop with json format and pass values by file
    Given latop json data detail as in file "laptop.json"
    When I do post request
    Then response body whould be correct as in file "laptop.json"
    And response post status code should be right as "200"
    Given latop json data for update as in file "laptop_update.json"
    When I do put request
    Then response update body whould be correct as in file "laptop_update.json"
    And response update status code should be right as "200"

  Scenario: udata a laptop with json format and pass values by file2
    Given latop json data detail as in file "laptop.json"
    When I do post request
    Then response body whould be correct "BrandName" "Features.Feature" "Id" "LaptopName" as in file "laptop.json"
    And response post status code should be right as "200"
    Given latop json data for update as in file "laptop_update.json"
    When I do put request
    Then response update body whould be correct as in file "laptop_update.json"
    And response update status code should be right as "200"

  Scenario Outline: udata a laptop with json format and pass values by file3
    Given latop json data detail as in file "<request_file>"
    When I do post request
    Then response body whould be correct "BrandName" "Features.Feature" "Id" "LaptopName" as in file "<post_response_file>"
    And response post status code should be right as "200"
    Given latop json data for update as in file "<update_file>"
    When I do put request
    Then response update body whould be correct as in file "<update_response_file>"
    And response update status code should be right as "200"

    @put_file
    Examples: 
      | request_file | post_response_file | update_file        | update_response_file |
      | laptop.json  | laptop.json        | laptop_update.json | laptop_update.json   |
      | laptop.json  | laptop.json        | laptop_update.json | laptop_update.json   |
