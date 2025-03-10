# Delivery App

Este projeto implementa um sistema de mensageria para um serviço de delivery, utilizando Spring Boot e RabbitMQ para comunicação assíncrona entre microsserviços.

## Tecnologias Utilizadas

- Java 17

- Spring Boot (Spring Data JPA & Spring AMQP)

- MySQL

- Maven

## Estrutura do Projeto

O sistema é composto por dois microsserviços principais:

### User-MS

Gerencia usuários e pedidos.

Publica mensagens de novos pedidos para o Eatery-MS via RabbitMQ.

### Eatery-MS

Recebe mensagens do User-MS e salva os pedidos no banco de dados.

Confirma o recebimento do pedido.


## Melhorias Futuras

Implementação de WebSocket para atualização em tempo real do status do pedido.

