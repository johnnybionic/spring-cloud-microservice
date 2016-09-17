# cloud-eureka-client-people-service

This service registers with Eureka, to be used by clients to obtain information about users.
To simulate the Real World, and to prevent clients accessing the service directly, it starts with a random port
- can be set using a command line argument

The service follows the standard controller/service/repository pattern.

The service uses an H2 database for both testing and running normally (i.e. there is 'real' persistence).
At the moment, it creates four Person instances. There are two implementations of the service layer. 
- One returns a random Person, and is the default
- The other, selected by using the 'sequential' profile, returns the Person entities in order
