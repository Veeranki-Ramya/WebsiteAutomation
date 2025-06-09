
@tag
Feature: Purchasing product from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on Ecommerce website


  @Regression
  Scenario Outline: Positive type of submitting the order
    Given logged in with username <name> and password <password>
    When adding product <productname> to cart
    And checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." confirmation message is displayed on confirmation page

    Examples: 
      | name              | password | productname |
      | phani12@gmail.com | Phani@13 | ZARA COAT 3 |
  
