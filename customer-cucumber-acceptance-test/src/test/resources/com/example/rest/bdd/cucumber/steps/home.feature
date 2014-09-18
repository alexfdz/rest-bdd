Feature: A REST client queries for the home page
  As a REST client
  I want to get a customer resource information


 Scenario: GET query to home page
   When I get the home page
   Then the response status is 200