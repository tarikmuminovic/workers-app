# Workers App

## Description

Workers app is Spring boot app that provides shift management for workers.

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