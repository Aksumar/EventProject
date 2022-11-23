# EventProject

There are several endpoints: 

1. POST `http://localhost:8080/events/create` - to create an event
2. DELETE `http://localhost:8080/events/{id}` - to delete an event
3. GET `http://localhost:8080/events` - to get all events
4. GET `http://localhost:8080/events/{id}` - to get an event by id

### Some details

I used Mapstract for mappers generation.

H2 database is used in this project in in-memory mode. You can use link `http://localhost:8080/h2-console` to access H2 console. Login & password provided in `application.properties`


### How to build executable jar

1. in project root directory run
`./mvnw clean`
`./mvnw install`

2. in directory you can find `./target/EventProject-*.jar`

### How to build docker image

1. build executable jar (see prev. How to build executable jar)
2. in project root directory run
`docker build -t event-project .` 

### How to run docker container

1. build docker image (see prev. How to build docker image)
2. run command
`docker run -d -p 8080:8080 event-project`

### Examples

#### Create an event
 This is a POST request with body. Example body is provided below.
```
{
    "name": "new Event",
    "date": "2022-11-26",
    "city": "Berlin",
    "country": "Germany",
    "guests": [
        {
            "name": "Alla",
            "email" : "alla@qq.com"
        },
        {
            "name": "Peter", 
            "email" : "peter@qq.com",
            "phone" : "+491700000000"
        }
    ]
}
```
If there is no such country or city, 422 http code (`UNPROCESSABLE_ENTITY`) will be returned.

`Temperature` field in `Event` entity will be set to `null` if there is no information about temperature on date.

Event object must contain name, date, city and country fields. If any of these fields is empty, 422 http code (`UNPROCESSABLE_ENTITY`) will be returned .

Guest object must contain name and email fields. If any of these fields is empty, 422 http code (`UNPROCESSABLE_ENTITY`) will be returned .

#### Delete an event

This is a DELETE request. Example request is provided below.

`http://localhost:8080/events/1`, where 1 - is the is an id of the event, that we want to delete.

If there is no such an event in DB, 204 http code (`NO_CONTENT`) will be returned.

#### Get all events
This is a GET request. Example request is provided below.

`http://localhost:8080/events`

#### Get an event by id
This is a GET request. Example request is provided below.

`http://localhost:8080/events/1` - where 1 - is the is an id of the event, that we want to get.

As long as the event with this id is persisted in the database, it will be returned, otherwise, a 400 HTTP code (`BAD_REQUEST`) will be returned.

