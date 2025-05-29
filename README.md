# Documentação do Projeto - Sistema de Gestão de Casa de Poker

## Visão Geral
Este projeto implementa um sistema de gestão para uma casa de poker utilizando Java Spring Boot. O sistema permite o gerenciamento de jogadores, torneios e dealers, com funcionalidades para compra de fichas, inscrição em torneios e controle de reentradas.

## Estrutura do Projeto
O projeto segue a arquitetura MVC (Model-View-Controller) com as seguintes camadas:

- **Model**: Entidades JPA que representam os objetos de negócio
- **Repository**: Interfaces para acesso aos dados
- **Service**: Camada de serviço com regras de negócio
- **Controller**: Endpoints REST para acesso às funcionalidades

## Entidades Principais

### Player (Jogador)
Representa um jogador na casa de poker, com atributos como nome, email, valor em reais das fichas compradas e quantidade de fichas de poker.

### Tournament (Torneio)
Representa um torneio na casa de poker, com atributos como nome, valor da inscrição, premiação total e indicação se reentradas são permitidas.

### Dealer (Crupiê)
Representa um dealer na casa de poker, com atributos como nome e salário.

## Relacionamentos
- Um jogador pode participar de vários torneios (relacionamento muitos-para-muitos)
- Um torneio pode ter vários jogadores inscritos (relacionamento muitos-para-muitos)

## Endpoints REST

### Player (Jogador)
- `GET /player/{id}`: Busca um jogador pelo ID
- `POST /player`: Cria um novo jogador
- `PUT /player/{id}`: Atualiza um jogador existente
- `DELETE /player/{id}`: Exclui um jogador
- `GET /player`: Lista todos os jogadores (opcional: filtro por nome)

### Tournament (Torneio)
- `GET /tournament/{id}`: Busca um torneio pelo ID
- `POST /tournament`: Cria um novo torneio
- `PUT /tournament/{id}`: Atualiza um torneio existente
- `DELETE /tournament/{id}`: Exclui um torneio
- `GET /tournament/{id}/players`: Lista os jogadores inscritos em um torneio específico
- `POST /tournament/{tournamentId}/players/{playerId}`: Adiciona um jogador a um torneio
- `GET /tournament`: Lista todos os torneios

### Dealer (Crupiê)
- `GET /dealer/{id}`: Busca um dealer pelo ID
- `POST /dealer`: Cria um novo dealer
- `PUT /dealer/{id}`: Atualiza um dealer existente
- `DELETE /dealer/{id}`: Exclui um dealer
- `GET /dealer`: Lista todos os dealers

## Banco de Dados
O projeto utiliza um banco de dados H2 em memória para facilitar os testes. O script SQL para criação das tabelas e inserção de dados de exemplo está disponível em `src/main/resources/data.sql`.

## Como Executar o Projeto

### Requisitos
- Java 17 ou superior
- Maven

### Passos para Execução
1. Clone o repositório ou extraia o arquivo ZIP
2. Navegue até a pasta raiz do projeto
3. Execute o comando: `mvn spring-boot:run`
4. O servidor será iniciado na porta 8080

### Acessando o Banco de Dados H2
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:pokerdb
- Usuário: sa
- Senha: password

## Testes com Postman
Uma coleção do Postman está disponível no arquivo `postman_collection.json` na raiz do projeto. Importe esta coleção no Postman para testar todos os endpoints.

### Testes Principais (conforme avaliação)
1. GET /player/{id}: Verifica se a busca por um jogador por ID retorna o JSON correto
2. POST /player: Testa a criação de um novo jogador
3. PUT /player/{id}: Verifica se a atualização de um jogador funciona corretamente
4. DELETE /player/{id}: Confirma que a exclusão de um jogador ocorre corretamente
5. GET /tournament/{id}/players: Testa se os jogadores inscritos em um torneio específico são retornados corretamente
6. POST /tournament: Testa a criação de um novo torneio e verifica se a inscrição de jogadores funciona corretamente
