# cloud-eureka-client-people-service

This is one service that will register with Eureka, to be used by clients to obtain information about users.

At the moment, it creates four Person instances, and chooses one at random, returning either the instance, or the Person's full name.

The service uses an H2 database, and follows a standard controller/service/repository pattern.