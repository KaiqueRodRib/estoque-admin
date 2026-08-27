# Estoque Admin

API para gerenciamento de estoque desenvolvida com Java e Spring Boot.

O projeto permite realizar o gerenciamento de categorias, fornecedores e itens de estoque, mantendo os dados armazenados em um banco de dados PostgreSQL.

## Tecnologias utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven
* Lombok
* Git e GitHub
* Postman

## Funcionalidades

Atualmente, a API possui operações de CRUD para as seguintes entidades:

### Categorias

* Criar categoria
* Listar categorias
* Buscar categoria por ID
* Atualizar categoria
* Excluir categoria

### Fornecedores

* Criar fornecedor
* Listar fornecedores
* Buscar fornecedor por ID
* Atualizar fornecedor
* Excluir fornecedor

### Itens de estoque

* Criar item
* Listar itens
* Buscar item por ID
* Atualizar item
* Excluir item

Também foram implementados os relacionamentos entre:

* Item de estoque e categoria
* Item de estoque e fornecedor

## Estrutura do projeto

O projeto está organizado em camadas:

```text
controller
service
repository
entity
```

* **Controller:** responsável por receber as requisições HTTP.
* **Service:** responsável pelas regras de negócio.
* **Repository:** responsável pela comunicação com o banco de dados.
* **Entity:** representa as entidades da aplicação.

## Entidades

### Categoria

* ID
* Nome

### Fornecedor

* ID
* Nome
* CNPJ
* E-mail
* Telefone

### Item de estoque

* ID
* Nome
* Descrição
* Unidade de medida
* Quantidade
* Estoque mínimo
* Custo unitário
* Categoria
* Fornecedor

## Endpoints

### Categorias

| Método | Endpoint           | Descrição               |
| ------ | ------------------ | ----------------------- |
| POST   | `/categorias`      | Criar categoria         |
| GET    | `/categorias`      | Listar categorias       |
| GET    | `/categorias/{id}` | Buscar categoria por ID |
| PUT    | `/categorias/{id}` | Atualizar categoria     |
| DELETE | `/categorias/{id}` | Excluir categoria       |

### Fornecedores

| Método | Endpoint             | Descrição                |
| ------ | -------------------- | ------------------------ |
| POST   | `/fornecedores`      | Criar fornecedor         |
| GET    | `/fornecedores`      | Listar fornecedores      |
| GET    | `/fornecedores/{id}` | Buscar fornecedor por ID |
| PUT    | `/fornecedores/{id}` | Atualizar fornecedor     |
| DELETE | `/fornecedores/{id}` | Excluir fornecedor       |

### Itens de estoque

| Método | Endpoint             | Descrição          |
| ------ | -------------------- | ------------------ |
| POST   | `/item-estoque`      | Criar item         |
| GET    | `/item-estoque`      | Listar itens       |
| GET    | `/item-estoque/{id}` | Buscar item por ID |
| PUT    | `/item-estoque/{id}` | Atualizar item     |
| DELETE | `/item-estoque/{id}` | Excluir item       |

## Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/KaiqueRodRib/estoque-admin.git
```

### 2. Entre na pasta do projeto

```bash
cd estoque-admin
```

### 3. Configure o PostgreSQL

Crie um banco de dados:

```sql
CREATE DATABASE estoque_admin;
```

Depois, configure as informações de conexão no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/estoque_admin
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
```

Substitua `SEU_USUARIO` e `SUA_SENHA` pelas informações do seu PostgreSQL.

### 4. Execute o projeto

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou execute diretamente pela sua IDE.

A aplicação será iniciada em:

```text
http://localhost:8080
```

## Próximos passos

Algumas funcionalidades planejadas para o projeto:

* [x] CRUD de categorias
* [x] CRUD de fornecedores
* [x] CRUD de itens de estoque
* [x] Relacionamento entre itens, categorias e fornecedores
* [ ] Controle de lotes
* [ ] Controle de validade
* [ ] Alerta de estoque mínimo
* [ ] Alerta de produtos próximos do vencimento
* [ ] Geração de QR Code
* [ ] Controle de receitas e ingredientes
* [ ] Autenticação e autorização de usuários
* [ ] Documentação com Swagger/OpenAPI
* [ ] Testes automatizados
* [ ] Docker
* [ ] Deploy da aplicação

## Sobre o projeto

O Estoque Admin está sendo desenvolvido como um projeto prático para aplicar e aprofundar conhecimentos em desenvolvimento backend com Java.

Durante o desenvolvimento do projeto, são trabalhados conceitos como APIs REST, Spring Boot, persistência de dados, relacionamentos entre entidades, PostgreSQL e organização de aplicações em camadas.

O objetivo é continuar evoluindo o projeto e adicionar funcionalidades mais complexas conforme novos conhecimentos forem sendo adquiridos.

## Autor

**Kaique Rodrigues**

GitHub: https://github.com/KaiqueRodRib

LinkedIn: https://www.linkedin.com/in/kaique-rodrigues-a51627143
