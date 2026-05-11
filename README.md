# 🔗 Java URL Shortener

Um encurtador de URLs simples construído com **Spring Boot**, **JPA** e **PostgreSQL**.

---

## 🚀 Sobre o projeto

Este projeto permite encurtar URLs longas e redirecionar usuários através de um código curto gerado automaticamente.

---

## 🧰 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven

---

## ⚙️ Como executar o projeto

### 1. Subir o banco de dados (Docker)

```bash
docker compose up -d
```

### 2. Configurar variáveis de ambiente

Crie um arquivo `.env` ou configure no `application.properties`:

```properties
POSTGRES_DB=url_shortener
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
spring.datasource.username=postgres
spring.datasource.password=postgres
```

### 3. Rodar a aplicação

```bash
./mvnw spring-boot:run
```

---

## 📌 Endpoints

### 🔹 Encurtar URL

**POST** `/shorten`

#### Request

```bash
curl --location 'http://localhost:8080/shorten' \
--header 'Content-Type: application/json' \
--data '{
    "originalUrl": "https://example.com"
}'
```

#### Response

```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

---

### 🔹 Redirecionar URL

**GET** `/{code}`

#### Request

```bash
curl --location 'http://localhost:8080/abc123'
```

#### Behavior

Redireciona automaticamente para a URL original (`302 Redirect`).

---

## 🧠 Como funciona

1. O usuário envia uma URL longa via `POST /shorten`
2. O sistema gera um código curto único
3. Salva no banco de dados
4. Retorna a URL encurtada
5. O acesso ao código redireciona para a URL original

---

## 📦 Exemplo de fluxo

```text
POST /shorten
→ https://google.com
→ abc123

GET /abc123
→ redireciona para https://google.com
```

---

## 📌 Melhorias futuras

- Expiração de links
- Validação de URL
- Cache com Redis
- Métricas de cliques
- Geração de QR Code

---

## 🧑‍💻 Autor

Projeto desenvolvido para estudo de **Spring Boot + APIs REST**.

