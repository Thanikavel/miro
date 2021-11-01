@allTests
Feature: End to end validation for miro sticker creation using multiple user login

  Scenario Outline: validate miro board sticker creation for multiple users
    Given I navigate to the miro page
    When I login to the miro page with given credentials
      | userEmailId   | password   |
      | <userEmailId> | <password> |
    Then I create a new white miro board
    Then I create sticker widget
    And I share the miro board
    And I logout from the miro application
    When I navigate to shared url to the miro page with given credentials
      | secondUserEmailId   | secondUserPassword   |
      | <secondUserEmailId> | <secondUserPassword> |
    Then I verify if the sticker widget is present and "valid" validated

    @miroUserValidation @test1
    Examples:
      | userEmailId            | password   | secondUserEmailId      | secondUserPassword |
      | hakim121977@gmail.com  | danger2010 | thanikavel.b@gmail.com | danger2010         |


  Scenario Outline: validate miro board multiple sticker creation for multiple users
    Given I navigate to the miro page
    When I login to the miro page with given credentials
      | userEmailId   | password   |
      | <userEmailId> | <password> |
    Then I create a new white miro board
    Then I create sticker widget
    And I share the miro board
    And I logout from the miro application
    When I navigate to shared url to the miro page with given credentials
      | secondUserEmailId   | secondUserPassword   |
      | <secondUserEmailId> | <secondUserPassword> |
    And I create additional sticker widget
    Then I verify if the sticker widget is present and "invalid" validated

    @miroUserValidation @test2
    Examples:
      | userEmailId            | password   | secondUserEmailId      | secondUserPassword |
      | hakim121977@gmail.com  | danger2010 | thanikavel.b@gmail.com | danger2010         |