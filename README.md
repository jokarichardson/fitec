# WEB APIS
<p>Aplicação de Demonstração de WEB APIs</p>
<p>Copyright © 2023 Richardson Software Ltda.</p>

## Introdução

Essa aplicação tem o intuito de apresentar operações CRUD operadas pelas web apis de Produtos, Garantias e Vendas</b>.

## Tecnologias Utilizadas

* Java (JDK 11);
* Spring Boot;
* Maven;
* REST;
* H2 In-memory Database

## Execução Local

* Utilizando um terminal (Command Prompt do Windows, Git Bash ou terminal do Linux), acessar o diretório raiz da aplicação (fitec);

* **Execução via jar:**

    * Realizar o build do projeto:
      ```
       mvn package
      ```
    * Digitar o comando:
      ```
       mvn -pl orquestrador -am spring-boot:run
      ```

    * Acessar http://localhost:8080 no navegador de preferência;
    * Será apresentado o Swagger da aplicação

## Payloads para Criação de Dados

* **Produto:**
  ```
  {
    "id": "86fc554c-8c81-11ee-b9d1-0242ac120002",
    "nome": "Nome do Produto",
    "valor": 11.11,
    "estoque_maximo": 111,
    "estoque_minimo": 1,
    "saldo_estoque": 18,
    "fornecedor": "Fornecedor",
    "garantia": true
  }
  ```
* **Garantia:**
  ```
  {
    "id": "8fbf4482-8c81-11ee-b9d1-0242ac120002",
    "nome": "Garantia 1",
    "valor": 15.00,
    "prazo": 5
  }
  ```
* **Venda:**
  ```
  {
    "id": "d20f418b-593e-415b-9a90-f2f87d90d3d5",
    "itensVenda": [{
        "id": "e5ef7f30-0b3f-41f8-bd6d-7418dadd9f7d",
        "idVenda": "d20f418b-593e-415b-9a90-f2f87d90d3d5",
        "idProduto": "86fc554c-8c81-11ee-b9d1-0242ac120002",
        "quantidade": 10,
        "valorUnitario": 11.11,
        "valorTotal": 111.10,
        "idGarantia": "8fbf4482-8c81-11ee-b9d1-0242ac120002"
    }],
    "valorTotal": 111.10
  }
  ```
