@regression
Feature: User Authentication - Hotel Reservation System

  As a registered user
  I want to log in to the Adactin Hotel Reservation System
  So that I can access the hotel booking dashboard

  Background:
    Given the user navigates to the Login page

  Scenario: Verify Login page title
    Then the page title should display "Adactin.com - Hotel Reservation System"

  @sanity
  Scenario: Successful login with valid credentials
    When the user enters a valid username and password
    And the user clicks the Login button
    Then the user should be redirected to the search hotel page