#Feature: Inclusão de novo funcionário no OrangeHRM
#
#  Como um administrador do sistema
#  Quero adicionar um novo funcionário
#  Para que ele possa acessar o sistema com suas credenciais
#
#  @cadastro-funcionario
#  Scenario Outline: Cadastro completo de novo funcionário
#    Given o usuário está logado na plataforma OrangeHRM
#    And acessa o menu "PIM"
#    And clica na opção Add Employee
#    And faz o upload da foto com o caminho "src/test/resources/images/fotoFuncionario.jpg"
#    And preenche os campos com os valores "<firstName>", "<middleName>", "<lastName>"
#    And preenche o campo Employee ID com o valor "<employeeID>"
#    And ativa a opção "Create Login Details"
#    And informa as credenciais passando os valores: "<username>", "<password>", "<confirmPassword>".
#    And clica em "Save"
#    Then o sistema deve exibir uma mensagem de sucesso no rodapé
#    Examples:
#      | firstName | middleName | lastName | employeeID | username   | password  | confirmPassword |
#      | Shunma    | Nakazawa   | Ono      | 402563     | Shunma.Ono | Test@1234 | Test@1234       |