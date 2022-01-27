# Workers App

## Description

Workers app is Spring boot app that provides shift management for workers.

## Features

- Mysql database with pma dockerized
- Usage of Hibernate ORM and JPA repositories
- exposed APIs by REST standard
- documented APIs with OpenApi spec
- Usage of design patterns
- Unit tests
- More...

### Prerequisites

To run/develop project on the local machine it is necessary to have:

```
1. java runtime environment 17
2. apache maven 3.6.3
3. docker 
```

### Running local environment

Docker compose with minimal infrastructure is located in <b>compose</b>
directory. To start docker containers you should run docker compose command from
project root by doing:

```
docker-compose -up -d 
```

To stop docker containers run:

```
docker-compose -down
```

After running docker containers (MySql and PMA) you can run your application
locally.