# Projeto Sistema LavaJato

Este projeto foi desenvolvido como trabalho final da disciplina de Programação para Web 1 na minha faculdade.

## Sobre o projeto 

O objetivo deste projeto é demonstrar o conhecimento adquirido na aplicação de padrões de projeto, como DAO e MVC. Os requisitos estabelecidos foram os seguintes:

* Utilização do padrão de arquitetura MVC;
* Implementação de páginas JSP para a camada de visualização;
* Proibição do uso de scriptless ou expressões nas páginas JSP;
* Adoção do padrão de projeto DAO para manipulação de dados;
* Utilização de banco de dados relacional para persistência dos dados;
* Gerenciamento de usuários logados por meio de sessões.

Em conformidade com essas exigências, as regras de negócio e o escopo do projeto foram definidos com base no meu entendimento.

Para este projeto Java EE, foram utilizados Glassfish, Maven e PostgreSQL como banco de dados. É importante observar que, embora tenha sido desenvolvido de maneira síncrona, essa abordagem foi solicitada pelo professor responsável.

## Banco de dados
O script utilizado para criar o banco de dados pode ser encontrado [aqui.](https://github.com/raunerlucas/Projeto-Sistema-LavaJato/blob/main/DataProj/scriptBanco.txt)


O modelo do banco de dados segue a seguinte estrutura:
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

## Diagrama de Classes

O diagrama de classes utilizado como referência está disponível [aqui](https://github.com/raunerlucas/Projeto-Sistema-LavaJato/blob/main/DataProj/diagrama.png)

<img src="/DataProj/diagrama.png" alt="Imagem"  width="700" height="500">

## Imagens do sistema

A seguir, algumas imagens do projeto:
[Assista o video aqui.](https://drive.google.com/file/d/1kxunj9MVoF_RZR-oIJ9ZNIqEqesnrdX7/view?usp=sharing)

<img src="/DataProj/Captura de tela 2023-12-12 095733.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 095859.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100046.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100122.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100148.png" alt="Imagem" width="900" height="300">
<img src="/DataProj/Captura de tela 2023-12-12 100234.png" alt="Imagem" width="900" height="300">


    