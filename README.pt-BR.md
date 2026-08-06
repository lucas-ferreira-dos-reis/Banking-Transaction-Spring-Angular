[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](README.pt-BR.md)

_Leia isto em outros idiomas: [English](README.md)_

---

# 🏦 Banking Transaction

Sistema web full-stack para agendamento de transferências financeiras com **cálculo automático de taxas dinâmicas** e **atualização de extrato em tempo real** via WebSockets.

---

## 📌 Visão Geral do Projeto

O objetivo do sistema é permitir que usuários agendem transferências financeiras informando conta de origem, conta de destino, valor e data de transferência. O sistema calcula a taxa cobrada com base na quantidade de dias de antecedência do agendamento.

### 🌟 Diferenciais Implementados

- **Atualização em Tempo Real (Pub/Sub):** Integração via WebSockets (STOMP/SockJS) que notifica todos os clientes conectados assim que uma nova transferência é registrada.
- **Arquitetura Strategy:** Lógica de taxas isolada para garantir facilidade de manutenção e extensibilidade (Open/Closed Principle).
- **Validação Fail-Fast:** Validações rigorosas no frontend e backend para garantir integridade dos dados e impedir contas idênticas ou datas inválidas.
- **Frontend Reativo Moderno:** Construído com Angular 22+, utilizando Signals, Standalone Components e a nova sintaxe de Control Flow (`@if`, `@for`).

---

## 🛠️ Tecnologias Utilizadas

### Backend

- **Java 11** & **Spring Boot 2.7.18** (É requisito do projeto ser feito usando Java 11)
- **Spring Data JPA** (Persistência de Dados)
- **H2 Database** (Banco de dados em memória)
- **Spring WebSocket / STOMP** (Notificações em tempo real)
- **JUnit 5 & MockMvc** (Testes unitários e de integração)
- **OpenAPI / Swagger UI** (Documentação interativa da API)

### Frontend

- **Angular 22+**
- **TypeScript**
- **Angular Material** (Componentes de UI e acessibilidade)
- **RxJS & Signals** (Gerenciamento de estado reativo)
- **SockJS / StompJS** (Cliente WebSocket)

---

## 📐 Decisões de Arquitetura

1. **Separação de Responsabilidades (Layered Architecture):**
   - `Controller`: Ponto de entrada REST, lidando com contratos HTTP e validações DTO.
   - `Service`: Centralização das regras de negócio e disparo de eventos WebSocket.
   - `Strategy/Domain`: Lógica isolada para o cálculo de taxas por faixas de dias.
   - `Repository`: Abstração de persistência via Spring Data JPA.

2. **Comunicação Orientada a Eventos:**
   - Em vez de depender do cliente fazer um novo _fetch_ HTTP após cada cadastro, o backend publica a nova transferência no tópico `/topic/transfers`. Isso permite um ecossistema reativo em tempo real para múltiplos usuários simultâneos.

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- **Java 11**
- **Node.js 18 ou superior**
- **npm**

---

### 1️⃣ Executando o Backend (Spring Boot)

1. Navegue até a pasta do backend:

```bash
cd backend
```

2. Execute a aplicação usando o Maven Wrapper:

- Linux/Mac (Terminal):

```bash
./mvnw spring-boot:run
```

- Windows (PowerShell):

```bash
.\mvnw spring-boot:run
```

- Windows (Prompt de Comando / CMD):

```bash
mvnw spring-boot:run
```

3. A API estará rodando em:

```
http://localhost:5000
```

4. Acesse a documentação Swagger UI em:

```
http://localhost:5000/api/swagger-ui/index.html
```

### 2️⃣ Executando o Frontend (Angular)

1. Em outro terminal, navegue até a pasta do frontend:

```bash
cd frontend
```

2. Instale as dependências:

```bash
npm install
```

3. Inicie o servidor de desenvolvimento:

```bash
ng serve
```

4. Acesse a aplicação no navegador em:

```
http://localhost:4200
```

## 🧪 Como Executar os Testes Unitários e de Integração

No diretório do backend, rode:

```bash
./mvnw test
```

Os testes cobrem:

- Validação do cálculo de taxas para todas as faixas de agendamento (0 dias, 1–10 dias, 11–20 dias, etc.), seguindo as regras definidas. A tabela pode ser consultada na página /transfers.
- Testes de integração de endpoints HTTP via MockMvc.
- Validações de restrições de payload (ex: transferência para a própria conta).

## 📄 Licença

Este projeto foi desenvolvido como parte de um teste técnico para avaliação de desenvolvimento Full-Stack.
