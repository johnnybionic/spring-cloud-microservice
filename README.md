# spring-cloud-microservice

This suite of apps is based on what I learned from the "Microservices with Spring Cloud" course (on Udemy).

The suite was developed using a parent project in IntelliJ IDEA, with each app created as a module from within the IDE.
There is no parent POM in the top-level folder, although the apps appear to share many components. It goes against
what microservices are all about, as it would bind all the apps together.

There are two services, one returns a person, and the other an assignment. A client app consumes these two, and creates
a sentence. 

