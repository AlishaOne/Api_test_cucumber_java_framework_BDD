@post
Feature: create a latop

  Scenario: create a laptop with json data
    Given latop json data BrandName as "Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth" LaptopName as "Mac Pro" and header accept json
    When I do post request
    Then response body whould be correct as BrandName is"Apple" feature as "8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth" LaptopName as "Mac Pro"
    And response status code should be right as "200"

  Scenario: create a laptop with xml data
    Given latop xml data as "xml data" and header accept xml
    When I do post request
    Then response body whould be correct as xml "Dell xml"
    And response status code should be right as "200"

  Scenario: create a laptop with json data as transform
    Given latop json data as "Dell,8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth,Latitude" and header accept json
    When I do post request
    Then response body whould be correct as "Dell,8GB RAM,2TB Hard Drive,Touch Pad,Bluetooth,Latitude"
    And response status code should be right as "200"
