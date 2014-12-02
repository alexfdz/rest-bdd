Feature: A REST client queries for the customer page
  As a REST client
  I want to get a customer resource information

  Scenario: Get customer page without id returns method not allowed
    When requested get "/customers"
    Then the response status is 405

  Scenario: Get customer page with invalid id returns bad request
    When requested get "/customers/Aksdoie"
    Then the response status is 400

  Scenario: Get customer page with wrong id returns not found
    When requested get "/customers/9889"
    Then the response status is 404

  Scenario: Get customer for un known media types returns not acceptable
    When requested accept media type "text/plain" for get "/customers/1"
    Then the response status is 406

  Scenario: Get customer page with existing id returns ok
    When requested get "/customers/1"
    Then the response status is 200
    And the response string not is empty string

  Scenario Outline: Get customer for known media types returns expected content type
    When requested accept media type "<mediaType>" for get "/customers/1"
    Then the response status is 200
    And the response media type is "<mediaType>"

    Examples:
    |  mediaType     |
    |  application/json  |
    |  application/xml    |