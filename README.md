Service Name : my-retail
Jar Name: myRetail-1.0.0-SNAPSHOT

Technology stack:

Gradle
Java
SpringBoot
Spring MVC(REST)
Couchbase for No-Sql data store
Jackson(In built in Spring) and Google Gson
Logback(slf4j) for logging
Mockito for Junit
Swagger for REST Documentation
Postman to test the REST service


Make sure you have latest version of Gradle installed and PATH is configured.
Download the attached source code zip file and unzip it to your local folder.
Go to the directory where you unzipped the file i.e. where the build.gradle is there
Run gradle clean bootRun command
The web service should be up at http://localhost:6062/

If you want to run the jar file in debug mode:
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5015 -jar ./build/libs/myRetail-1.0.0-SNAPSHOT.jar

Swagger URL:
http://localhost:6062/swagger-ui.html#/

Couchbase Admin Url:

http://localhost:8091/index.html

GET Call:

 http://localhost:6062/api/products/13860429

Response: 
{
    "id": 13860429,
    "name": "SpongeBob SquarePants: SpongeBob's Frozen Face-off",
    "current_price": {
        "value": 18.5,
        "currency_code": "CAD"
    }
}



PUT Call:(http://localhost:6062/api/products/13860429)

Request:
{"id": 13860429, "name":"Test Product 2", "current_price":{"value":18.5,"currency_code":"CAD"}}
Response:
Product Saved/Updated Successfully!



Asumption:

1. Product Name always(both GET and PUT) will be fetched from the service http://redsky.target.com/
2. Sending productID with request body during PUT call. If the ID does not exist, it stores into the database.






