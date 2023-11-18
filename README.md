# Projeto-Sistema-LavaJato
PW1 projeto

# Diagrama do banco
```mermaid
erDiagram
  Empresa {
    id INT
    nomeFicticio VARCHAR
    CNPJ VARCHAR
    email VARCHAR
    id_endereco INT
  }
  
  Funcionario {
    id INT
    nome VARCHAR
    Sobrenome VARCHAR
    cpf VARCHAR
    id_endereco INT
    login VARCHAR
    senha VARCHAR
    admin BOOLEAN
    email VARCHAR
  }
  
  Cliente {
    id INT
    id_endereco INT
    nome VARCHAR
    Sobrenome VARCHAR
    cpf VARCHAR
    login VARCHAR
    senha VARCHAR
    email VARCHAR
  }
  
  Endereco {
    id INT
    cep INT
    logradouro VARCHAR
    numero INT
    complemento VARCHAR
    bairro VARCHAR
    Cidade VARCHAR
    Estado VARCHAR
  }
  
  OrdemServico {
    id INT
    id_funcionario INT
    id_cliente INT
    id_Empresa INT
    numOS INT
    status VARCHAR
    entregar BOOLEAN
    dataEmissao DATE
    previsaoTermino DATE
    ValorTotal DECIMAL
  }
  
  Servico {
    id INT
    descricao VARCHAR
    preco DECIMAL
  }

  Empresa ||--o{ Endereco : Possui
  Funcionario ||--o{ Endereco : Reside
  Cliente ||--o{ Endereco : Reside
  Empresa ||--o{ OrdemServico : Presta
  Funcionario ||--o{ OrdemServico : Realiza
  Cliente ||--o{ OrdemServico : Solicita
  OrdemServico ||--o{ Servico : Inclui
  Servico ||--o{ OrdemServico : Está incluso em

```
