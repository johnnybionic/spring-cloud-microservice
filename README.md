# spring-cloud-microservice

This suite of apps is based on what I learned from the "Microservices with Spring Cloud" course (on Udemy).

The suite was developed using a parent project in IntelliJ IDEA, with each app created as a module from within the IDE.
There is no parent POM in the top-level folder, although the apps appear to share many components. It goes against
what microservices are all about, as it would bind all the apps together.

There are two services, one returns a person, and the other an assignment. A client app consumes these two, and creates
a sentence (or 'pairing' as it's referred to in the projects)
 
These are the modules:

# cloud-config-server
A configuration server that serves config data pulled from this repository (config-data folder)

# cloud-config-example-client
Somewhat redundant, this was developed to test the config server. However, it does show how configuration data can
be refreshed at runtime, including that used in controllers. 

# cloud-eureka-server
What it says :)

# cloud-eureka-client-person-service
A service that registers with Eureka, and provides Person data - specifically, the name of the person to use in the sentence/pairing. This
module uses an H2 database with the standard controller/service/repository architecture. 

# cloud-eureka-client-assignment-service
A service that registers with Eureka, and provides Assignment data - specifically, the assignment to use in the sentence/pairing. This
module is simply a controller with hard-coded assignments.

# cloud-discovery-client
A client app that consumes the two services. Various techniques are used to consume the services, via several controllers, which all
produce the same result. See the module's readme for the techniques used. 

# cloud-zuul-gateway
A simple API gateway.


