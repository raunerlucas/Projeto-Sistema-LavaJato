# Projeto-Sistema-LavaJato
PW1 projeto

# Diagrama do banco
```mermaid
erDiagram
  Empresa ||--o{ Endereco : Possui
  Empresa {
      int id
      string nomeFicticio
      string CNPJ
      string email
      int id_endereco
  }
  Funcionario ||--o{ Endereco : Reside
  Funcionario {
      int id
      string nome
      string Sobrenome
      string cpf
      int id_endereco
      string login
      string senha
      boolean admin
      string email
  }
```
