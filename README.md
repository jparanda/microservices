# What are microservices?
Since the earliest days of Enterprise Java, the most common way of deploying an application has been to package all the application’s server-side components as a single war or ear file. This so-called monolithic architecture has a number of benefits. Monolithic applications are simple to develop since IDEs and other tools are oriented around developing a single application. They are also simple to deploy since you just have to deploy the one war/ear file on the appropriate container.

However, the monolithic approach becomes unwieldy for complex applications. A large monolithic application can be difficult for developers to understand and maintain. It is also an obstacle to frequent deployments. To deploy changes to one application component you have to build and deploy the entire monolith, which can be complex, risky, time consuming, require the coordination of many developers and result in long test cycles. A monolithic architecture can also make it difficult to trial and adopt new technologies and so you are often stuck with the technology choices that you made at the start of the project.

To avoid these problems, a growing number of organizations are using a microservice architecture. The application is functionally decomposed into a set of services. Each service has a narrow, focused set of responsibilities, and is, in some cases, quite small. For example, an application might consist of services such as the order management service, the customer management service etc.

Microservices have a number of benefits and drawbacks. A key benefit is that services are developed and deployed independently of one another. Another key benefit is that different services can use different technologies. Moreover, since each service is typically quite small, it’s practical to rewrite it using a different technology. As a result, microservices make it easier to trial and adopt new, emerging technologies. One major drawback of microservices is the additional complexity – development, and deployment – of distributed systems. For most large applications, however, the benefits outweigh the drawbacks. 

This definion was taked from [Chris Richardson's blog](https://plainoldobjects.com/2014/04/01/building-microservices-with-spring-boot-part1/)

You can learn more about microservices by visiting [microservices.io](https://microservices.io/index.html) 

This repository have several examples and personal demos about microservices architectural style
