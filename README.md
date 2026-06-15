# PetCare Platform Backend

Backend REST API for the PetCare project. It follows the same style as the Learning Center Platform guide: Spring Boot, Maven, MySQL, bounded contexts, REST controllers, OpenAPI/Swagger, JPA repositories and a DDD/CQRS-oriented folder structure.

## Main contexts

- `iam`: users, authentication and roles (`OWNER`, `CLINIC`, `MOBILE`).
- `petmanagement`: pets owned by pet owners.
- `clinicmanagement`: clinics, veterinarians, clinic services and patients.
- `appointmentmanagement`: appointments between owners, clinics and mobile providers.
- `medicalrecords`: medical history records.
- `mobile`: mobile professionals, mobile services, availability, requests and appointments.
- `serviceproviders`: unified search for clinics and mobile professionals.

## Requirements

- Java 21
- MySQL Server 8.x
- IntelliJ IDEA
- MySQL Workbench recommended

## Database

Default development database:

```properties
DATABASE_NAME=petcare_platform
DATABASE_USER=root
DATABASE_PASSWORD=123456789
```

The project uses:

```properties
spring.jpa.hibernate.ddl-auto=update
createDatabaseIfNotExist=true
```

So MySQL can create the database and tables automatically on the first successful run.

## Run

From the project root:

```powershell
$env:JAVA_HOME="C:\Users\Justinn\.jdks\temurin-21.0.11"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\mvnw spring-boot:run
```

The API runs on port `3000` by default to match the Angular frontend:

```txt
http://localhost:3000/swagger-ui.html
```

If you want to use port 8080, edit `src/main/resources/application-dev.properties`:

```properties
server.port=${PORT:8080}
```

## Test users

The backend seeds realistic test data when the database is empty:

| Role | Username | Password |
| --- | --- | --- |
| CLINIC | `clinic1` | `clinic123` |
| OWNER | `owner1` | `owner123` |
| MOBILE | `mobile1` | `mobile123` |

## Important endpoints

```txt
POST /api/v1/authentication/sign-in
POST /api/v1/authentication/sign-up
GET  /api/v1/users/me
GET  /api/v1/pets?ownerId=2
GET  /api/v1/appointments?ownerId=2
GET  /api/v1/appointments?clinicId=1
GET  /api/v1/service-providers?district=Miraflores
GET  /api/v1/mobile-professionals?userId=3
GET  /api/v1/mobile-services?mobileId=1
```

## Frontend connection

Your Angular frontend currently points to:

```ts
platformProviderApiBaseUrl: 'http://localhost:3000/api/v1'
```

So stop the old json-server before running this backend on port 3000.
