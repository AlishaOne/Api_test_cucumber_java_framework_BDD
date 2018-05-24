@delete
Feature: delete a latop with a id

  Scenario Outline: delete a latop with a valid id
    Given latop data with detail as in file "<request_file>"
    When I do post request for creating data
    Then response body should be correct as in file "<post_response_file>"
    And response post status code should be right as in file "<post_response_status>"
    Given latop json data for delete as in file "<delete_file>"
    When I do delete request with id as"<id>"
    And response delete status code should be right as "<del_response_status>"
    When I do get request with same id as delete as "<id>"
    Then response get status code should be right as "<get_response_status>"

    Examples: 
      | request_file    | post_response_file | delete_file     | id          | post_response_status | del_response_status | get_response_status |
      | laptop.json     | laptop.json        | laptop.json     | get_a_value |                  200 |                 200 |                 404 |
      | laptop_res.json | laptop_res.json    | laptop_res.json | get_a_value |                  200 |                 200 |                 404 |

  Scenario Outline: delete a latop with a invalid id
    Given latop json data for delete as in file "<delete_file>"
    When I do delete request with id as"<id>"
    And response delete status code should be right as "<del_response_status>"
    When I do get request with same id as delete as "<id>"
    Then response get status code should be right as "<get_response_status>"

    @daily
    Examples: 
      | delete_file | id | del_response_status | get_response_status |
      | laptop.json |  0 |                 404 |                 404 |
      | laptop.json |  1 |                 404 |                 404 |

    @weekly
    Examples: 
      | delete_file | id | del_response_status | get_response_status |
      | laptop.json |  5 |                 404 |                 404 |
      | laptop.json |  6 |                 404 |                 404 |
