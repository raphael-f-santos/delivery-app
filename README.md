# Delivery App

Sistema de delivery baseado em microsserviços desenvolvido com **Spring Boot**, **RabbitMQ** e **Docker**.

O sistema simula o fluxo de pedidos entre usuários e restaurantes, utilizando comunicação assíncrona via mensageria para confirmação de pedidos através de um código.

[![](https://mermaid.ink/img/pako:eNptkM9SwjAQxl8lsyecKdD_lByc0eLBGRkR9GLLIZBQOtKESdtRpDyNBx_AR-DFTFsKwphLdr_s_vbLbmEuKAMMi5V4ny-JzNDDOORIHX8VM54F9cVQF92M7pEveJonTE5DXlfdkYzJzXASsCpoJ-m0fnhJmVRyrq6TOCazWZwNn4JWE10dXga3RtCqYSo-qWbQKkkH7a8z1G5fF6PHyTPqCkmZTIujmXNrZWHJv7RcAYaMpyRiCRoxGlNRHC2eG64Y9ZcaTJ0d4OalVvj7HxpHAlGG5oIvYpmQ_ff-62zCPzMac6BBJGMKOJM500CtPCFlCtuyKYRsyRIWAlYhJfIthJDvVM-a8FchkqZNijxaAl6QVaqyfE0VfRCTSJLkqErG1fZ8kfMMsOuaFQTwFj4A9-yO4ZmO4em6Y_UMQ4MNYMMwO7bTNw3LdFzPcvr2ToPPaqre6Tm645meZ1l923Vte_cLCcm-iw?type=png)](https://mermaid.live/edit#pako:eNptkM9SwjAQxl8lsyecKdD_lByc0eLBGRkR9GLLIZBQOtKESdtRpDyNBx_AR-DFTFsKwphLdr_s_vbLbmEuKAMMi5V4ny-JzNDDOORIHX8VM54F9cVQF92M7pEveJonTE5DXlfdkYzJzXASsCpoJ-m0fnhJmVRyrq6TOCazWZwNn4JWE10dXga3RtCqYSo-qWbQKkkH7a8z1G5fF6PHyTPqCkmZTIujmXNrZWHJv7RcAYaMpyRiCRoxGlNRHC2eG64Y9ZcaTJ0d4OalVvj7HxpHAlGG5oIvYpmQ_ff-62zCPzMac6BBJGMKOJM500CtPCFlCtuyKYRsyRIWAlYhJfIthJDvVM-a8FchkqZNijxaAl6QVaqyfE0VfRCTSJLkqErG1fZ8kfMMsOuaFQTwFj4A9-yO4ZmO4em6Y_UMQ4MNYMMwO7bTNw3LdFzPcvr2ToPPaqre6Tm645meZ1l923Vte_cLCcm-iw)

### Tecnologias Utilizadas

* [Java](https://dev.java/learn/)
* [Spring Boot / Spring Data JPA](https://docs.spring.io/spring-framework/reference/overview.html)
* [RabbitMQ](https://www.rabbitmq.com/docs)
* [Docker](https://docs.docker.com/)
* [MySQL](https://dev.mysql.com/doc/)

## Dependências e Versões Necessárias

    java: 17
    Spring: 3.4.3
    RabbitMQ: 3-management
    Docker: 29.2.1
    MySQL: 8

### Como Rodar o Projeto

Clonar o repositório
    
    git clone https://github.com/seuusuario/delivery-app.git
    
Entrar na pasta
    
    cd delivery-app
    
Subir os containers

    docker-compose up --build

Os seguintes serviços serão iniciados:

- delivery-app-user-ms
- delivery-app-eatery-ms
- rabbitmq
- mysql

## API Endpoints

### Criar Usuário

    POST /users/create-user

#### Request:
    
    {
        "name":"TESTE",
        "address":"Ap Teste, 444"
    }
    
#### Response:
    
    - "User created" -

    {
        "id": 1,
        "name": "TESTE",
        "address": "Ap Teste, 444"
    }

#### Banco de Dados

    users -> tb_users
    +----+---------------+-------+
    | id | address       | name  |
    +----+---------------+-------+
    |  1 | Ap Teste, 444 | TESTE |
    +----+---------------+-------+

### Criar Pedido

    POST /users/send-order

#### Request:

    { 
        "menu": "X_BACON", 
        "quantity": 2, 
        "user": 
        { 
            "id": 1,
            "name":"TESTE",
            "address":"Ap Teste, 444"
        } 
    }

#### Response:
    
    - "Order sent for processing" -

    {
        "id": 1,
        "menu": "X_BACON",
        "quantity": 2,
        "user": 
        {
            "id": 1,
            "name": "TESTE",
            "address": "Ap Teste, 444"
        }
    }

#### Banco de Dados

    eateries -> tb_orders
    +----+---------+----------+---------+---------+
    | id | menu    | quantity | status  | user_id |
    +----+---------+----------+---------+---------+
    |  1 | X_BACON |        2 | PENDING |       1 |
    +----+---------+----------+---------+---------+

    users -> code
    +----+--------+----------+---------+---------+
    | id | code   | order_id | status  | user_id |
    +----+--------+----------+---------+---------+
    |  1 | 804818 |        1 | PENDING |       1 |
    +----+--------+----------+---------+---------+

O status **"PENDING"** é alterado para **"CONFIRMED"** via eventos logo após confirmar a existência de um pedido e usuário vinculados

    users -> code
    +----+--------+----------+---------+---------+
    | id | code   | order_id | status  | user_id |
    +----+--------+----------+---------+---------+
    |  1 | 804818 |        1 |CONFIRMED|       1 |
    +----+--------+----------+---------+---------+

### Validar Entrega

    POST /orders/code

#### Request:

    {
        "userId": 1,
        "orderId": 1,
        "code": 804818
    }

#### Response:

    {
        "userId": 1,
        "orderId": 1,
        "code": 804818
    }

#### Banco de Dados

    orders -> tb_orders
    +----+---------+----------+-----------+---------+
    | id | menu    | quantity |  status   | user_id |
    +----+---------+----------+-----------+---------+
    |  1 | X_BACON |        2 | DELIVERED |       1 |
    +----+---------+----------+-----------+---------+

    users -> code
    +----+--------+----------+---------+---------+
    | id | code   | order_id | status  | user_id |
    +----+--------+----------+---------+---------+
    |  1 | 804818 |        1 |  USED   |       1 |
    +----+--------+----------+---------+---------+
    