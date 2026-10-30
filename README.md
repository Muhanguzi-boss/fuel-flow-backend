
# FuelFlow Backend

- Student: Muhanguzi Boss
- Student ID: 27810
- Project: FuelFlow (INSY 8322 Web Technology and Internet, mid-term)

Spring Boot, Spring Data JPA and PostgreSQL backend for a petrol station:
suppliers, attendants, pumps, fuel types, deliveries and tank offloads.

## How to run

1. Install Java 17 and PostgreSQL.
2. Create the database: `CREATE DATABASE fuel_flow_db;`
3. In `src/main/resources/application.properties` check the datasource url, username and password.
4. Start the API: `./mvnw spring-boot:run`
5. The API runs on http://localhost:8081 (tables are created automatically).
6. Import `fuel-flow-midterm.postman_collection.json` into Postman and run the requests.
