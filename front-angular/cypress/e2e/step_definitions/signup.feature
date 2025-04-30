Feature: Signup

Scenario: Submitting a valid signup form 
    Given I'm in the signup page
    When I enter "rodrigo" in the username field
    And I enter "rodrigo@gmail.com" in the email field
    And I enter "12345678" in the password field
    And I enter "12345678" in the confirmPassword field
    Then I click the submit button
    Then I should see "Conta criada com sucesso! Em instantes você será redirecionado para a página de login" success message
    And I should be redirected to the Login Page

Scenario: Submitting with two diferent password
    Given I'm in the signup page
    When I enter "rodrigo" in the username field
    And I enter "rodrigo@gmail.com" in the email field
    And I enter "12345678" in the password field
    And I enter "1234" in the confirmPassword field
    Then I click the submit button
    Then I should see "As duas senhas precisam ser iguais!" failed message
    And I should stay in the signup page

Scenario: Submitting with an already registered username
  Given I am on the signup page
  When I enter "rodrigo" in the username field
  And I enter "novoemail@gmail.com" in the email field
  And I enter "12345678" in the password field
  And I enter "12345678" in the confirmPassword field
  Then I click the submit button
  Then I should see "Nome de usuário já está cadastrado, por favor escolha outro." failed message
  And I should stay on the signup page

Scenario: Submitting with an already registered email
  Given I am on the signup page
  When I enter "novousuario" in the username field
  And I enter "rodrigo@gmail.com" in the email field
  And I enter "12345678" in the password field
  And I enter "12345678" in the confirmPassword field
  Then I click the submit button
  Then I should see "O e-mail informado já está cadastrado." failed message
  And I should stay on the signup page