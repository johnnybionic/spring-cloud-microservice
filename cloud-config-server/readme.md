# configuration service

Provides centralised configuration, which is pulled from a GitHub repository.

The port for this service is 8001. 

To use, give the name of the application, the profile, and the file extension, e.g.

http://localhost:8001/cloud-config-example-client.properties

- testing

The tests use local files, when the profile is set to 'native' 

- actuator

The service has Spring Actuator enabled, the various endpoins can be seen in the log at startup.