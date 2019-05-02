# Vault and Spring Boot with PostgreSQL

This project contains a Spring Boot sample to Hashicorp Vault integration and a docker-compose.yml that describes an infrastructure with PostgreSQL and Vault Server to support it. The main purpose is to allow the application to access security credentials to the PostgreSQL database while Vault manages the security rotation on it. Also, the Spring Boot application reads basic credentials from Hashicorp and map the values inside a property class, load and print values after application starts.

## Runing Containers
On terminal, run docker-compose with docker-compose.yml inside vault-postgresql-docker folder:

```bash
cd vault-postgresql-docker
docker-compose up
```

Create the database and table that will be used in this example by Spring Data:
```bash
docker-compose exec postgres-compose psql -U postgres -c "CREATE DATABASE dbtest";
docker-compose exec postgres-compose psql -U postgres dbtest -c "CREATE TABLE foo(id SERIAL PRIMARY KEY);"
```

Also, create simple credentials that will be readded by application on Hashicorp Vault. This values will be printed when application starts:
```bash
docker-compose exec vault-compose vault write secret/spring-vault-sample myrepo.password=gitpass myrepo.username=gituser
``` 

Now, lets configure and create a database engine with credential rotation:
```bash
docker-compose exec vault-compose vault secrets enable database
docker-compose exec vault-compose vault write  database/config/dbtest plugin_name=postgresql-database-plugin allowed_roles="myrole" connection_url="postgresql://postgres:postgres@postgres-compose:5432/dbtest?sslmode=disable"
```

## Build and Run

Inside springboot-project/spring-vault folder, use maven to package the application (do this with all configured containers running). Then, just execute the jar file:

```bash
mvn package
cd target
java -jar spring-vault 0.0.1-SNAPSHOT
```
After this, the application will run a Unit Test to insert and select on table public.foo, previously created, using Hashicorp Vault credentials. The source is inside [this file](https://github.com/pedrovitorlima/vault-springboot-postgres/blob/master/springboot-project/spring-vault/src/test/java/br/pedro/springboot/springvault/repository/FooRepositoryTest.java). Also, myrepo.password and myrepo.username will be printed on console output with configured values. These values are mapped in [this file](https://github.com/pedrovitorlima/vault-springboot-postgres/blob/master/springboot-project/spring-vault/src/main/java/br/pedro/springboot/springvault/domain/Secrets.java).
