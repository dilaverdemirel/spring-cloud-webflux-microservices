# Spring WebFlux Microservices Demo

Main purpose, demonstrate the reactive microservices approach with Spring WebFlux Functional Programming Tecnique.
The Application send an email with  order detail to all user when after create,update or delete an order .

If you want to build and run the project, required Java 8 and Docker.

# Services

  - UserService
  - OrderService
  - EmailService
  - Gateway
  - ServiceRegistry

![N](https://github.com/dilaverdemirel/spring-cloud-webflux-microservices/tree/master/src/main/documents/diagram.png)

### Build and Run and Monitor
Before run the project you need a Gmail account. And than you must edit docker-compose.yml file. You must replace parameters below in docker-compose.yml file;
-Dspring.mail.username=
-Dspring.mail.password=
```sh
build-and-run.cmd
docker-compose ps
```
You can view the application status on service registry with address http://localhost:8761.

### Destroy
```sh
docker-compose down
```

### Sample Requests

There is sample requests;

* Create User :
```sh
curl -X POST \
  http://localhost:7000/user/api/users \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: a7bf35f9-b7f4-4297-829a-288790741df9' \
  -H 'cache-control: no-cache' \
  -d '{
	"nameTitle" : "Dilaver Demirel",
	"email" : "dilaverdemirel@gmail.com"
}'
```
* Get a User :
```sh
curl -X GET \
  http://localhost:7000/user/users/5c3a060cb0e987084c8e6b1d \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: bd20dc22-9677-4342-90a7-88a5e6c886d7' \
  -H 'cache-control: no-cache' \
  -d '{
	"nameTitle" : "Dilaver Demirel1",
	"email" : "dilaverdemirel@gmail.com"
}'
```

* Create Order:
```sh
curl -X POST \
  http://localhost:7000/order/api/orders \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 564be39b-3a75-4009-a23c-fb39425a293f' \
  -H 'cache-control: no-cache' \
  -d '{
	"customerName" : "Dilaver Demirel",
	"productName" : "Car",
	"deliveryAddress" : "Istanbul",
	"amount" : "100",
	"status" : "NEW"
}'
```
* Delete Order:
```sh
curl -X DELETE \
  http://localhost:7000/order/api/orders/5c3a6499f264de00071d5bc0 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ef99c572-2516-40e0-939c-1e9f72a0a6f3' \
  -H 'cache-control: no-cache'
```
* Update Order:
```sh
curl -X PUT \
  http://localhost:7000/order/api/orders/5c3a535fb0e98729aca0417c \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: d8217dd4-5550-42a0-a779-4876a8e6e4f3' \
  -H 'cache-control: no-cache' \
  -d '{
    "id": "5c3a535fb0e98729aca0417c",
    "customerName": "Dilaver Demirel12",
    "productName": "Car",
    "deliveryAddress": "Istanbul",
    "amount": 100,
    "status": "NEW"
}'
```
* Get a Order:
```sh
curl -X GET \
  http://localhost:7000/order/api/orders/5c3a6499f264de00071d5bc0 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 77a28f6b-c5f5-41f4-8c58-76c754ccd774' \
  -H 'cache-control: no-cache'
```

