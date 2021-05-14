# Spring Boot Microservices with Spring Security CQRS and Event Sourcing

Once you start all the modules, make base64 encoding of `springbankClient:springbankSecret` - it will be `c3ByaW5nYmFua0NsaWVudDpzcHJpbmdiYW5rU2VjcmV0`

- Make the request to see if token is generating - 

```curl
curl --location --request POST 'http://localhost:8084/oauth/token' \
--header 'Authorization: Basic c3ByaW5nYmFua0NsaWVudDpzcHJpbmdiYW5rU2VjcmV0' \
--form 'grant_type="password"' \
--form 'username="johnd"' \
--form 'password="P@ssw0rd1"'
```

Response

```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjA5OTM1NzUsInVzZXJfbmFtZSI6ImpvaG5kIiwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiLCJXUklURV9QUklWSUxFR0UiXSwianRpIjoiZjIwNGJjZjktM2M1YS00MDhjLTkwYmUtNDA3OGEwYTEzYTgxIiwiY2xpZW50X2lkIjoic3ByaW5nYmFua0NsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.IC8n7cJUj95W39UCrJBk69i4rJt8RbBz1y7Ux5wyx0g",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqb2huZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiJmMjA0YmNmOS0zYzVhLTQwOGMtOTBiZS00MDc4YTBhMTNhODEiLCJleHAiOjE2MjA5OTQ3NzUsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIiwiV1JJVEVfUFJJVklMRUdFIl0sImp0aSI6IjAzY2NkOTJlLTBkZTEtNGU1Zi05N2QwLWUxOGMyNzUxMmNkMiIsImNsaWVudF9pZCI6InNwcmluZ2JhbmtDbGllbnQifQ.H-amn2YxW_bfKUphMu133dnHUlkf2QKETW80XpV5Mt8",
    "expires_in": 299,
    "scope": "read write",
    "jti": "f204bcf9-3c5a-408c-90be-4078a0a13a81"
}
```
