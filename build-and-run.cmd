mvnw.cmd clean^
    & mvnw.cmd -pl service-registry clean package docker:build^
    & mvnw.cmd -pl gateway clean package docker:build^
    & mvnw.cmd -pl email-service clean package docker:build^
    & mvnw.cmd -pl order-service clean package docker:build^
    & mvnw.cmd -pl user-service clean package docker:build^
    & docker-compose up -d