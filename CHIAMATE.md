# Chiamate

## 1. Register new user

POST <http://localhost:8080/api/auth/register>
Content-Type: application/json

{
  "username": "newuser",
  "email": "<newuser@example.com>",
  "password": "password123"
}

## 2. Login with test user

POST <http://localhost:8080/api/auth/login>
Content-Type: application/json

{
  "username": "testuser",
  "password": "password123"
}

## 3. Access public endpoint (no auth required)

GET <http://localhost:8080/api/test/public>

## 4. Access protected endpoint (requires valid access token)

GET <http://localhost:8080/api/test/protected>
Authorization: Bearer YOUR_ACCESS_TOKEN_HERE

## 5. Get user info (requires valid access token)

GET <http://localhost:8080/api/test/user>
Authorization: Bearer YOUR_ACCESS_TOKEN_HERE

## 6. Refresh access token using refresh token

POST <http://localhost:8080/api/auth/refresh>
Content-Type: application/json

{
  "refreshToken": "YOUR_REFRESH_TOKEN_HERE"
}

## 7. Logout (invalidates refresh token)

POST <http://localhost:8080/api/auth/logout>
Authorization: Bearer YOUR_ACCESS_TOKEN_HERE

## Example cURL commands

### Register

curl -X POST <http://localhost:8080/api/auth/register> \
  -H "Content-Type: application/json" \
  -d '{"username":"newuser","email":"<newuser@example.com>","password":"password123"}'

### Login

curl -X POST <http://localhost:8080/api/auth/login> \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","password":"password123"}'

### Access protected endpoint

curl -X GET <http://localhost:8080/api/test/protected> \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN"

### Refresh token

curl -X POST <http://localhost:8080/api/auth/refresh> \
  -H "Content-Type: application/json" \
  -d '{"refreshToken":"YOUR_REFRESH_TOKEN"}'
  