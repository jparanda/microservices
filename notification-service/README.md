# Notification Service

This little project is a demo about use micro-service architecture 
style. This is a simulation about a complete application that need a 
notification service.

## Design

The following image is a high-level design about this service and the interaction with other components :

![notification service](https://github.com/jparanda/microservices/blob/master/images/notification_service.jpg?raw=true)

* This service takes the message from rabbitMQ and sends a notification to the new user notifying that their account 
was created. 
* In this case, the user registered an email, so the notification service will use it, but in the future, 
the user could have another way to receive notification for example a mobile phone. 