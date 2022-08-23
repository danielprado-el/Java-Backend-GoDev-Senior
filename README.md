# Java Backend GoDev Senior :rocket:
## Bem vindo!! :wave:

Este repositório contém um desafio que consiste em implementar um cadastro de produtos e serviços, possibilitando a emissão de pedidos de compra destes produtos e serviços. :books:

## Foram utilizadas as seguintes tecnologias:
• PostgreSQL
• Java
• JPA
• API REST

## Execução da Aplicação
Para a execução da aplicação é necessário ter o banco de dados PostgreSQL instalado, mas caso prefira, é possível utilizar outro banco de dados como o MySQL ou SQL Server, basta alterar a configuração do `pom` para importar a dependência do Driver do banco desejado. É necessário que veja as configurações do banco de dados do arquivo `application.properties` e altere as três primeiras linhas(`nome do banco de dados, nome de usúario e senha`) de acordo com a configuração do seu PostgreSQL.

## Endpoints
### CRUD Item
Para `CADASTRAR` um item no banco de dados:
`POST` http://localhost:8080/api/items
{
description: string,
value: double,
type: char ('P' OU 'S')
}

Para `BUSCAR` todos os itens no banco de dados:
`GET` http://localhost:8080/api/items 

Para `BUSCAR` um item no banco de dados pelo ID:
`GET` http://localhost:8080/api/items/{id}

Para `DELETAR`  um item no banco de dados pelo ID:
`DELETE`http://localhost:8080/api/items/{id}

Para `ATUALIZAR`  um item no banco de dados pelo ID:
`PUT`http://localhost:8080/api/items/{id}
{
description: string,
value: double,
type: char
}

### CRUD Pedido
Para `CADASTRAR` um pedido no banco de dados:
`POST` http://localhost:8080/api/orders
{
number: integer,
date: timestamp,
percentualDiscount: double
}

Para `BUSCAR` todos os pedidos no banco de dados:
`GET` http://localhost:8080/api/orders

Para `BUSCAR` um pedido no banco de dados pelo ID:
`GET` http://localhost:8080/api/orders/{id}

Para `DELETAR`  um pedido no banco de dados pelo ID:
`DELETE`http://localhost:8080/api/orders/{id}

Para `ATUALIZAR`  um pedido no banco de dados pelo ID:
`PUT`http://localhost:8080/api/orders/{id}
{
number: integer,
date: timestamp,
percentualDiscount: double,
totalValue: double
}

Para `CONCLUIR`um pedido no banco de dados pelo ID:
`POST` http://localhost:8080/api/orders/{id}/close
{
order: UUID, // id do pedido
percentualDiscount: double
}

### CRUD Itens do pedido
Para `CADASTRAR` um item no pedido no banco de dados pelo ID:
`POST` http://localhost:8080/api/orders/{id}/items
{
itemId: UUID,
quantity: double
}

Para `BUSCAR` todos os itens do pedido no banco de dados pelo ID:
`GET` http://localhost:8080/api/orders/{orderId}/items

Para `BUSCAR` um item do pedido no banco de dados pelo ID:
`GET` http://localhost:8080/api/orders/{orderId}/items/{orderItemId}

Para `DELETAR`  um item do pedido no banco de dados pelo ID:
`DELETE`http://localhost:8080/api/orders/{orderId}/items/{orderItemId}

Para `ATUALIZAR`  um item do pedido no banco de dados pelo ID:
`PUT`http://localhost:8080/api/orders/{orderId}/items/{orderItemId}
{
itemId: UUID, //id do item
quantity: double
}
