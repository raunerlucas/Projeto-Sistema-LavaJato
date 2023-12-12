# Projeto-Sistema-LavaJato

Ola meu nome é Rauner!
Este Projeto foi feito com trabaho final da materia de Prgoramação pra Web 1, 
em minha faculdade. 

## Um Pouco sobre o projeto 

Este projeto tem como abjetivo mostra que aprendi lidadar com algun padrões de projeto
como o DAO, MVC, entre outras coisa, para que fique um pouco mais claro algumas da
exigencias do projeto são:  

* Utilize o padrão de arquitetura MVC;
* Utilize páginas JSP para fazer a View;
* Não utilize scriptless nem expressão nas páginas JSP;
* Utilize o padrão de projeto DAO;
* Utilize banco de dados relacional para fazer a persistência dos dados;
* Usuários logados devem estar na sessão. 

Seguindo essa exigencia a regras de negocio e o projeto eu fiz basedo em meu intendimento,

Para o esse projeto Java EE, foi ultilizado Glassfish, Maven e PostgreSQL como banco de dados.

### Banco de dados
O Script que usei foi o este [DataProj => scriptBanco.txt](https://github.com/raunerlucas/Projeto-Sistema-LavaJato/blob/main/DataProj/scriptBanco.txt)


O qual segue essa modelagem:
```mermaid
erDiagram
  Empresa ||--o{ Endereco : Possui
  Empresa {
      int id
      string nomeFicticio
      string telefone
      string CNPJ
      string email
      int id_endereco
  }
  Funcionario ||--o{ Endereco : Reside
  Funcionario {
      int id
      string nome
      string Sobrenome
      string CPF
      int id_endereco
      string telefone
      string login
      string senha
      boolean admin
      string email
  }
  Cliente ||--o{ Endereco : Reside
  Cliente {
      int id
      int id_endereco
      string nome
      string Sobrenome
      string telefone
      string cpf
      string login
      string senha
      string email
  }
  Endereco {
      int id
      int cep
      string logradouro
      int numero
      string complemento
      string bairro
      string Cidade
      string Estado
  }
  Empresa ||--o{ OrdemServico : Presta
  Funcionario ||--o{ OrdemServico : Realiza
  Cliente ||--o{ OrdemServico : Solicita
  OrdemServico {
      int id
      int id_funcionario
      int id_cliente
      int id_Empresa
      int id_Veiculo
      int numOS
      string status
      string observacao
      boolean entregar
      array servicosOrdem
      date dataEmissao
      date previsaoTermino
      float ValorTotal
  }
  OrdemServico ||--o{ Servico : Inclui
  Servico {
      int id
      string descricao
      float preco
  }
  OrdemServico ||--o{ Veiculo : Possui
  Veiculo {
      int id
      string Placa
      string Modelo
      string Cor
      string Tipo
      
  }
```

### Diagram de Classes

O diagrama que ultilizei como base foi este [DataProj => Diagrama.png](https://github.com/raunerlucas/Projeto-Sistema-LavaJato/blob/main/DataProj/diagrama.png)

<img src="/DataProj/diagrama.png" alt="Imagem"  width="700" height="500">

### Imagens do sistema

Aqui deixo alguma imagens do Projeto

video: ...

<img src="/DataProj/Captura de tela 2023-12-12 095733.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 095859.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100046.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100122.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100148.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100234.png" alt="Imagem" width="900" height="300">


    