@allTests
Feature: Validation of the e-shopping cart for "add products to wish list" and "add product to cart" journey

  Background:
	Given I navigate to the e-shopping cart home page

  Scenario: Validate that user is able to add four different products to wish list and add it to the cart
	Given I add "four" different products to my wish list
    When I view my wishlist table
    Then I find total four selected items in my wishlist
    When I search for lowest price product
    And I am able to add the lowest price item to my cart
    Then I am able to verify the item in my cart
