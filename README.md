# Projeto-Sistema-LavaJato
PW1 projeto

erDiagram

  Company {
    int id
    varchar nomeFicticio
    varchar CNPJ
    varchar email
    int id_endereco
  }

  Employee {
    int id
    varchar nome
    varchar Sobrenome
    varchar cpf
    int id_endereco
    varchar login
    varchar senha
    bool admin
    varchar email
  }

  Customer {
    int id
    int id_endereco
    varchar nome
    varchar Sobrenome
    varchar cpf
    varchar login
    varchar senha
    varchar email
  }

  Address {
    int id
    int cep
    varchar logradouro
    int numero
    varchar complemento
    varchar bairro
    varchar Cidade
    varchar Estado
  }

  Order {
    int id
    int id_funcionario
    int id_cliente
    int id_Empresa
    int numOS
    varchar status
    bool entregar
    json servicosOrdem
    date dataEmissao
    date previsaoTermino
    float ValorTotal
  }

  Service {
    int id
    varchar descricao
    float preco
  }
