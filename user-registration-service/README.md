# User Registration Service

This little project is a demo about use micro-service architecture 
style. This is a simulation about a complete application that need a 
functionality to user register.

## Design

The following image describe the abstract micro-service design for
**User Registration Service**

![user registration service design](https://github.com/jparanda/microservices/blob/master/images/user_registration_service.jpg?raw=true)

## About the design
* The user registration service have a post endpoint (post /users) in order to allow user register.
* The user registration service have a get endpoint (get /users) in order to retrieve all the users registered.
* The user should be register in a data base for example MySQL or Mongo DB.
    * If the use exists in the DB the service should return a response
      about it and **HTTP 400 Bad Request**.
    * The service should validate the email format, if it is incorrect,
      the service should return a response about it and **HTTP 400 Bad request**.
    * If the user is registered in the correct way the service should return **HTTP 201 created**.
* Once the user is register in the DB the service should send a notification through
  RabbitMQ.
* **Notification Service** is responsible to send the user notification
  about the user register.
* **Notification service** is a listener waiting for new message on the correct topic to send the user notification.
 
## API design
* The **POST** request /users example:
```
{ 
	"email":"juan.aranda.galvis@gmail.com",
	"user":"jparandag",
	"notification":"EMAIL"
}
```

* The **POST** response example:
```
{   
    "id":1,
    "email":"juan.aranda.galvis@gmail.com",
    "user":"jparandag"
}
```
* The GET response /users example :
```
{
    {   
        "id":1,
        "email":"juan.aranda.galvis@gmail.com",
        "user":"jparandag"
    },
    {
       "id":2,
       "email":"tellezcr@gmail.com",
       "user":"tellezcr"        
    },
    {
        "id":3,
        "email":"catalina.aranda@gmail.com",
        "user":"cmarandatellez"
    }
}
```
