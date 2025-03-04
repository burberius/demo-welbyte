# Welbyte Eligibility Service

## Requirements
* Java JDK 23
* Docker

## Build
Execute the following command to build the executable jar:
```
./gradlew build
```

With the following command we build the docker image:
```
docker build -t welbyte .
```

## Run
You can start the service with the following command:
```
docker run -d -p 8080:8080 --name welbyte welbyte
```

## Check
With the following commands you can find a eligible user, by employee:
```
curl --request GET \
  --url 'http://localhost:8080/eligibility?employee_code=abc&member_status=employee&employee_date_of_birth=1979-01-09&employee_id=44000100' \
  --header 'authorization: Bearer 1234567890'
```
and by dependent:
```
curl --request GET \
  --url 'http://localhost:8080/eligibility?employee_code=abc&member_status=dependent&employee_date_of_birth=1979-01-09&employee_first_name=Walter&employee_last_name=Jacobson' \
  --header 'authorization: Bearer 1234567890'
```

## Remarks
* The API Authentication key is configurated via _application.properties_: apitoken=1234567890
* That can't be overwritten via Docker environment yet
* There is also directory with a collection for the Bruno tool to play around with the API