# Master Spring Boot Microservices with CQRS & Event Sourcing

This project has been developed using Spting Boot + Axon Framework uses MongoDB to save the data.

Download the Axon Framework - https://axoniq.io/download

![image](https://user-images.githubusercontent.com/54174687/118264806-e51f4e80-b4d5-11eb-8f60-a59578f6a270.png)

You can hit the register user, update user and delete user endpoint now.

# Register User

```curl
curl --location --request POST 'http://localhost:8081/api/v1/registerUser' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user" : {
        "firstname" : "John",
        "lastmname" : "Doe",
        "emailAddress" : "john@springbank.com",
        "account" : {
            "username" : "johnd",
            "password" : "P@ssw0rd1",
            "roles" : [
                "READ_PRIVILEGE", "WRITE_PRIVILEGE"
            ]
        }
    }
}
```

```java
curl --location --request POST 'http://localhost:8081/api/v1/registerUser' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user" : {
        "firstname" : "Jane",
        "lastmname" : "Doe",
        "emailAddress" : "jane@springbank.com",
        "account" : {
            "username" : "janed",
            "password" : "P@ssw0rd2",
            "roles" : [
                "READ_PRIVILEGE"
            ]
        }
    }
}'
```


Like Wise Save Jane Doe and Mike Doe.

# Update User

```json
curl --location --request PUT 'http://localhost:8081/api/v1/updateUser/f032b3ce-7d5a-4b74-8ef2-7994433046a8' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user" : {
        "firstname" : "Mike",
        "lastmname" : "Doe",
        "emailAddress" : "mike.doe@springbank.com",
        "account" : {
            "username" : "miked",
            "password" : "P@ssw0rd2",
            "roles" : [
                "READ_PRIVILEGE"
            ]
        }
    }
}'
```


# user collection details

```json
/* 1 */
{
    "_id" : "3313eb37-1591-4f72-9353-7c3ab711fdd7",
    "firstname" : "John",
    "emailAddress" : "john@springbank.com",
    "account" : {
        "username" : "johnd",
        "password" : "$2a$12$iG9cmO8hiQ9H3fjQwXBMJe6r3Zu5IP3kVv53Lh7ClcKFVgbX.6lYi",
        "roles" : [ 
            "READ_PRIVILEGE", 
            "WRITE_PRIVILEGE"
        ]
    },
    "_class" : "com.springbank.user.core.models.User"
}

/* 2 */
{
    "_id" : "412ddcec-47ef-461c-b983-94664ad3cfc0",
    "firstname" : "Jane",
    "emailAddress" : "jane@springbank.com",
    "account" : {
        "username" : "janed",
        "password" : "$2a$12$nE6FdoTCyEuEP78rr6AGH.uFcnLi62xQmiG4k9.l.NHqOX/hQ5qH2",
        "roles" : [ 
            "READ_PRIVILEGE"
        ]
    },
    "_class" : "com.springbank.user.core.models.User"
}
```

db.getCollection('domainevents').find({})

```json
/* 1 */
{
    "_id" : ObjectId("609e5af038d3f16f7215bf2e"),
    "aggregateIdentifier" : "3313eb37-1591-4f72-9353-7c3ab711fdd7",
    "type" : "UserAggregate",
    "sequenceNumber" : NumberLong(0),
    "serializedPayload" : "<com.springbank.user.core.events.UserRegisteredEvent><id>3313eb37-1591-4f72-9353-7c3ab711fdd7</id><user><id>3313eb37-1591-4f72-9353-7c3ab711fdd7</id><firstname>John</firstname><emailAddress>john@springbank.com</emailAddress><account><username>johnd</username><password>$2a$12$iG9cmO8hiQ9H3fjQwXBMJe6r3Zu5IP3kVv53Lh7ClcKFVgbX.6lYi</password><roles><com.springbank.user.core.models.Role>READ_PRIVILEGE</com.springbank.user.core.models.Role><com.springbank.user.core.models.Role>WRITE_PRIVILEGE</com.springbank.user.core.models.Role></roles></account></user></com.springbank.user.core.events.UserRegisteredEvent>",
    "timestamp" : "2021-05-14T11:11:44.5019361Z",
    "payloadType" : "com.springbank.user.core.events.UserRegisteredEvent",
    "payloadRevision" : null,
    "serializedMetaData" : "<meta-data><entry><string>traceId</string><string>7896bfc9-9a17-4c39-9f74-ddb9f7f16aa0</string></entry><entry><string>correlationId</string><string>7896bfc9-9a17-4c39-9f74-ddb9f7f16aa0</string></entry></meta-data>",
    "eventIdentifier" : "44877542-6556-44b7-a281-66019b58447c"
}

/* 2 */
{
    "_id" : ObjectId("609e5b1538d3f16f7215bf2f"),
    "aggregateIdentifier" : "412ddcec-47ef-461c-b983-94664ad3cfc0",
    "type" : "UserAggregate",
    "sequenceNumber" : NumberLong(0),
    "serializedPayload" : "<com.springbank.user.core.events.UserRegisteredEvent><id>412ddcec-47ef-461c-b983-94664ad3cfc0</id><user><id>412ddcec-47ef-461c-b983-94664ad3cfc0</id><firstname>Jane</firstname><emailAddress>jane@springbank.com</emailAddress><account><username>janed</username><password>$2a$12$nE6FdoTCyEuEP78rr6AGH.uFcnLi62xQmiG4k9.l.NHqOX/hQ5qH2</password><roles><com.springbank.user.core.models.Role>READ_PRIVILEGE</com.springbank.user.core.models.Role></roles></account></user></com.springbank.user.core.events.UserRegisteredEvent>",
    "timestamp" : "2021-05-14T11:12:21.6503249Z",
    "payloadType" : "com.springbank.user.core.events.UserRegisteredEvent",
    "payloadRevision" : null,
    "serializedMetaData" : "<meta-data><entry><string>traceId</string><string>678c07d9-4491-4380-8315-00ff964d3786</string></entry><entry><string>correlationId</string><string>678c07d9-4491-4380-8315-00ff964d3786</string></entry></meta-data>",
    "eventIdentifier" : "8184f701-9862-4075-8565-c0a41b8253b6"
}

/* 3 */
{
    "_id" : ObjectId("609e5b3938d3f16f7215bf30"),
    "aggregateIdentifier" : "f032b3ce-7d5a-4b74-8ef2-7994433046a8",
    "type" : "UserAggregate",
    "sequenceNumber" : NumberLong(0),
    "serializedPayload" : "<com.springbank.user.core.events.UserRegisteredEvent><id>f032b3ce-7d5a-4b74-8ef2-7994433046a8</id><user><id>f032b3ce-7d5a-4b74-8ef2-7994433046a8</id><firstname>Mike</firstname><emailAddress>mike@springbank.com</emailAddress><account><username>miked</username><password>$2a$12$oo0RBpcpBNvhryI/h6fcKuusT0XVzTnUOwCOcXqjsANOJ7Tun//we</password><roles><com.springbank.user.core.models.Role>READ_PRIVILEGE</com.springbank.user.core.models.Role></roles></account></user></com.springbank.user.core.events.UserRegisteredEvent>",
    "timestamp" : "2021-05-14T11:12:57.8459092Z",
    "payloadType" : "com.springbank.user.core.events.UserRegisteredEvent",
    "payloadRevision" : null,
    "serializedMetaData" : "<meta-data><entry><string>traceId</string><string>64f34332-bbf7-4b12-b778-8da6ae29291f</string></entry><entry><string>correlationId</string><string>64f34332-bbf7-4b12-b778-8da6ae29291f</string></entry></meta-data>",
    "eventIdentifier" : "526d5090-17c2-4bec-9b98-4b8b8fc3ac70"
}

/* 4 */
{
    "_id" : ObjectId("609e5c6b38d3f16f7215bf32"),
    "aggregateIdentifier" : "f032b3ce-7d5a-4b74-8ef2-7994433046a8",
    "type" : "UserAggregate",
    "sequenceNumber" : NumberLong(2),
    "serializedPayload" : "<com.springbank.user.core.events.UserRemovedEvent><id>f032b3ce-7d5a-4b74-8ef2-7994433046a8</id></com.springbank.user.core.events.UserRemovedEvent>",
    "timestamp" : "2021-05-14T11:18:03.2305905Z",
    "payloadType" : "com.springbank.user.core.events.UserRemovedEvent",
    "payloadRevision" : null,
    "serializedMetaData" : "<meta-data><entry><string>traceId</string><string>0b0c4258-db17-4fa4-a533-92fd897b8752</string></entry><entry><string>correlationId</string><string>0b0c4258-db17-4fa4-a533-92fd897b8752</string></entry></meta-data>",
    "eventIdentifier" : "a181a2b5-8f1d-44c3-97e9-a6fa25ecf6c6"
}

/* 5 */
{
    "_id" : ObjectId("609e5bbe38d3f16f7215bf31"),
    "aggregateIdentifier" : "f032b3ce-7d5a-4b74-8ef2-7994433046a8",
    "type" : "UserAggregate",
    "sequenceNumber" : NumberLong(1),
    "serializedPayload" : "<com.springbank.user.core.events.UserUpdatedEvent><id>783bbb1f-c90c-4ae6-ab8d-f1fae2c318fa</id><user><id>f032b3ce-7d5a-4b74-8ef2-7994433046a8</id><firstname>Mike</firstname><emailAddress>mike.doe@springbank.com</emailAddress><account><username>miked</username><password>$2a$12$iVGJ7OSG5jKi2Xq5nCVXDuYBsap9Yzne81YmpNY71DiGMIXmmrJzi</password><roles><com.springbank.user.core.models.Role>READ_PRIVILEGE</com.springbank.user.core.models.Role></roles></account></user></com.springbank.user.core.events.UserUpdatedEvent>",
    "timestamp" : "2021-05-14T11:15:10.8145219Z",
    "payloadType" : "com.springbank.user.core.events.UserUpdatedEvent",
    "payloadRevision" : null,
    "serializedMetaData" : "<meta-data><entry><string>traceId</string><string>869d7f6f-6138-4db2-9bed-aa78cb2a2b87</string></entry><entry><string>correlationId</string><string>869d7f6f-6138-4db2-9bed-aa78cb2a2b87</string></entry></meta-data>",
    "eventIdentifier" : "aa5eddad-c64c-408a-aa8a-396ee7e3c047"
}
```

# Get Al Users

http://localhost:8082/api/v1/userLookup/

```json
{
    "message": null,
    "users": [
        {
            "id": "3313eb37-1591-4f72-9353-7c3ab711fdd7",
            "firstname": "John",
            "lastname": null,
            "emailAddress": "john@springbank.com",
            "account": {
                "username": "johnd",
                "password": "$2a$12$iG9cmO8hiQ9H3fjQwXBMJe6r3Zu5IP3kVv53Lh7ClcKFVgbX.6lYi",
                "roles": [
                    "READ_PRIVILEGE",
                    "WRITE_PRIVILEGE"
                ]
            }
        },
        {
            "id": "412ddcec-47ef-461c-b983-94664ad3cfc0",
            "firstname": "Jane",
            "lastname": null,
            "emailAddress": "jane@springbank.com",
            "account": {
                "username": "janed",
                "password": "$2a$12$nE6FdoTCyEuEP78rr6AGH.uFcnLi62xQmiG4k9.l.NHqOX/hQ5qH2",
                "roles": [
                    "READ_PRIVILEGE"
                ]
            }
        }
    ]
}
```

# Get User By Id

http://localhost:8082/api/v1/userLookup/byId/412ddcec-47ef-461c-b983-94664ad3cfc0

```json
{
    "message": null,
    "users": [
        {
            "id": "412ddcec-47ef-461c-b983-94664ad3cfc0",
            "firstname": "Jane",
            "lastname": null,
            "emailAddress": "jane@springbank.com",
            "account": {
                "username": "janed",
                "password": "$2a$12$nE6FdoTCyEuEP78rr6AGH.uFcnLi62xQmiG4k9.l.NHqOX/hQ5qH2",
                "roles": [
                    "READ_PRIVILEGE"
                ]
            }
        }
    ]
}
```
