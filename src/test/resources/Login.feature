Feature: Login scenarios

  @Login @UnhappyPath
  Scenario Outline: Login with invalid username
    Given I enter user name "<username>"
    And I enter the password "<password>"
    When I login
    Then I get the error message "<error_message>"
    Examples:
      | username | password | error_message                                                |
      | invalid  | invalid  | ZZZUsername and password do not match any user in this service. |

  @Login @HappyPath
  Scenario Outline: Login with valid credentials
    Given I enter user name "<username>"
    And I enter the password "<password>"
    When I login
    Then I get the page title "<page_title>"
    Examples:
      | username      | password     | page_title |
      | standard_user | secret_sauce | PRODUCTS   |