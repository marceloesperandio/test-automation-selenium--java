Feature: Login no sistema OrangeHRM

  @login @testes-funcionais @login-positivo
  Scenario Outline: Login com credenciais válidas
    Given o usuário acessa a página de login do OrangeHRM
    When o usuário informa o nome de usuário "<usuario>" e senha "<senha>"
    And clica no botão de login
    Then o sistema deve redirecionar para a dashboard
    Examples:
      | usuario | senha    |
      | Admin   | admin123 |
      | admin   | admin123 |
      | ADMIN   | admin123 |

  @login @testes-funcionais @login-negativo
  Scenario Outline: Login com credenciais inválidas
    Given o usuário acessa a página de login do OrangeHRM
    When o usuário informa o nome de usuário "<usuario>" e senha "<senha>"
    And clica no botão de login
    Then o sistema deve exibir uma mensagem de erro indicando falha no login.
    Examples:
      | usuario | senha     |
      | admin   | errado123 |
      | errado  | admin123  |