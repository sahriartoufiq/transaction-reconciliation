# Transaction Reconciliation App

The concept behind the project is to perform a financial reconciliation between two different sets of data.

There are two mainly services in this application, one is the web part built on react.js and the other one is back-end service built on spring-boot framework.

### Please visit https://reconciliation-app.vercel.app/ to check the live version.

## Steps to run this application

run

`docker-compose up`

or run

`docker-compose up --build`

N.B> port 3000 and 8081 need to be free to run this application

### Now visit http://127.0.0.1:3000 to check the local version of the app

### Run the below command to check the local api

```
curl -L -X POST 'localhost:8081/api/transactions/compare' \
-F 'clientReport=@"/path/to-file/PaymentologyMarkoffFile20140113.csv"' \
-F 'orgReport=@"/path/to-file/ClientMarkoffFile20140113.csv"'
```
