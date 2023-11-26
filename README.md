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
