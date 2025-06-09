
@tag
Feature: Validating login
  I want to use this template for my feature file


  @Errorvalidation
  Scenario Outline: validating credentilas
   Given I landed on Ecommerce website
    When logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed
   
    Examples: 
      | name              | password | 
      | phani12@gmail.com | Phani@1  | 