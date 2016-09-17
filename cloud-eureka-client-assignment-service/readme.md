# cloud-eureka-client-assignment-service

This is a service that will register with Eureka, to be used by clients to obtain information about assignments.

There is no database, simply a controller that returns random assignments, which are retrieved from the configuration service.
- see the other service for a 'proper' database app

To create the service:
- add @EnableDiscoveryClient to the application class
- configure application.yml and bootstrap.yml
- ensure there's an application.yml in test/resources to disable Eureka during tests