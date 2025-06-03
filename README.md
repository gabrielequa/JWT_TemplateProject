# JWT_TemplateProject

Un template per app in java spring boot che usano jwt.

## Come usare il template

1. Clona il repository
2. Esegui il comando `mvn clean install` per buildare il progetto
3. Esegui il comando `mvn spring-boot:run` per avviare l'applicazione
4. Accedi all'applicazione tramite il browser all'indirizzo `http://localhost:8080`
5. Per testare le funzionalità di autenticazione, puoi utilizzare Postman o un altro client HTTP con queste chiamate:

### 1. Registra un nuovo utente

POST <http://localhost:8080/api/auth/register>
Content-Type: application/json

{
  "username": "newuser",
  "email": "<newuser@example.com>",
  "password": "password123"
}

### 2. Login con un utente

POST <http://localhost:8080/api/auth/login>
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}

### 3. Accesso endpoint publico (non è necessaria autenticazione)

GET <http://localhost:8080/api/test/public>

### 4. Accesso endpoint protetto (è necessaria autenticazione)

GET <http://localhost:8080/api/test/protected>
Authorization: Bearer ACCESS_TOKEN_HERE

### 5. Restituisce info utente (richiede access token valido)

GET <http://localhost:8080/api/test/user>
Authorization: Bearer ACCESS_TOKEN_HERE

### 6. Restituisce le info di tutti gli utenti (richiede access token valido)

GET <http://localhost:8080/api/test/all-users>
Authorization: Bearer ACCESS_TOKEN_HERE

### 7. Refresh access token usando il refresh token

POST <http://localhost:8080/api/auth/refresh>
Content-Type: application/json

{
  "refreshToken": "REFRESH_TOKEN_HERE"
}

### 8. Logout (invalida il refresh token)

POST <http://localhost:8080/api/auth/logout>
Authorization: Bearer ACCESS_TOKEN_HERE

## Esempi chiamate cURL

### Registrazione

curl -X POST <http://localhost:8080/api/auth/register> \
  -H "Content-Type: application/json" \
  -d '{"username":"newuser","email":"<newuser@example.com>","password":"password123"}'

### Login

curl -X POST <http://localhost:8080/api/auth/login> \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'

### Accesso endpoint protetto

curl -X GET <http://localhost:8080/api/test/protected> \
  -H "Authorization: Bearer ACCESS_TOKEN"

### Refresh token

curl -X POST <http://localhost:8080/api/auth/refresh> \
  -H "Content-Type: application/json" \
  -d '{"refreshToken":"REFRESH_TOKEN"}'
  