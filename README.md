# spring-cloud-microservice

This suite of apps is based on what I learned from the "Microservices with Spring Cloud" course (on Udemy).

There are two services, one returns a person, and the other an assignment. A client app consumes these two, and creates
a sentence (or 'pairing' as it's referred to in the projects)
 
![Alt text](cloud.jpg?raw=true "Overview")

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

Development
-----------

The suite was developed using a parent project in IntelliJ IDEA, with each app created as a module from within the IDE.
The parent POM was added late in the development, and is there just to get Jenkins to pull the entire suite at once. There are no versions or JARs specified 
in the POM - it goes against microservices are all about, as it would bind all the apps together.

***** extra bit of text added to see if local Jenkins picks up a GitHub commit *****

