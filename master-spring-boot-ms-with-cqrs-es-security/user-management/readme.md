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


As we know `janed` doesn't have WRITE_PRIVILEGE to register the user and if we try to do it , we should some error - 

Firstly get the fresh token

```curl
curl --location --request POST 'http://localhost:8084/oauth/token' \
--header 'Authorization: Basic c3ByaW5nYmFua0NsaWVudDpzcHJpbmdiYW5rU2VjcmV0' \
--form 'grant_type="password"' \
--form 'username="janed"' \
--form 'password="P@ssw0rd2"'
```

Response

```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjA5OTQ5NDUsInVzZXJfbmFtZSI6ImphbmVkIiwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiXSwianRpIjoiZDUwMmEwZjEtMzJmNi00MGY3LTg4YjUtNzU3M2RlOTVkZGE2IiwiY2xpZW50X2lkIjoic3ByaW5nYmFua0NsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.ptNx7w64ohxyr0ljU5IbZ61zV8-FoxmB3QPj5mieWoU",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJqYW5lZCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiJkNTAyYTBmMS0zMmY2LTQwZjctODhiNS03NTczZGU5NWRkYTYiLCJleHAiOjE2MjA5OTYxNDUsImF1dGhvcml0aWVzIjpbIlJFQURfUFJJVklMRUdFIl0sImp0aSI6IjJmYjc4Y2NiLTVhODEtNGZlMy05ZDM4LTI5NmVlMTVkMTQxZiIsImNsaWVudF9pZCI6InNwcmluZ2JhbmtDbGllbnQifQ.C73GBTlW8GdHYxbc3Qh17nvKMV64LrJPfOFlYi4AJTk",
    "expires_in": 299,
    "scope": "read write",
    "jti": "d502a0f1-32f6-40f7-88b5-7573de95dda6"
}
```

Use this token to make the POST request

```curl
curl --location --request POST 'http://localhost:8081/api/v1/registerUser' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjA5OTQ5NDUsInVzZXJfbmFtZSI6ImphbmVkIiwiYXV0aG9yaXRpZXMiOlsiUkVBRF9QUklWSUxFR0UiXSwianRpIjoiZDUwMmEwZjEtMzJmNi00MGY3LTg4YjUtNzU3M2RlOTVkZGE2IiwiY2xpZW50X2lkIjoic3ByaW5nYmFua0NsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.ptNx7w64ohxyr0ljU5IbZ61zV8-FoxmB3QPj5mieWoU' \
--header 'Content-Type: application/json' \
--data-raw '{
	"firstname" : "Jane",
	"lastmname" : "Doe",
	"emailAddress" : "jane@springbank.com",
	"account" : {
		"username" : "janed",
		"password" : "P@ssw0rd2",
		"roles" : [
			"READ_PRIVILEGE", "WRITE_PRIVILEGE"
		]
	}
}'
```

Response

```
{
    "error": "access_denied",
    "error_description": "Access is denied"
}
```
