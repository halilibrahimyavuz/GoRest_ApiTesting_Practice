Feature: Api testing for CRUID operators

  Background: url
    Given User given api url "https://gorest.co.in/"

  Scenario Outline: POST-Create a new user
    Given User set api endpoint "public/v2/users"
    And User create new user with request body "<Name>" , "<Gender>" ,"<Email>" ,"<Status>"
    Then Validate the status code 201
    And Validate the userId is not null
    And Validate the user name is "<Name>"
    And Validate the user gender is "<Gender>"
    And Validate the user email is "<Email>"
    And Validate the user status is "<Status>"



    Examples:    #düzenleme için ctrl+alt+L

      | Name  | Gender | Email           | Status |
      | Sare Betul2  | female | sare222@gmail.com  | active |


