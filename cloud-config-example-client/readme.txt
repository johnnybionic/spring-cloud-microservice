This simple client uses the cloud config server directly, i.e.
no service discovery etc.

To run:

http://localhost:7999/words

although there should be a log statement with the settings at startup, e.g.

Settings are: [Rabbit's Foot|horseshoe] [daredevil] [hello]

The app also demonstrates using @ConfigurationProperties to reduce the number of @Value annotations,
which when used with Lombok @Setter really does reduce the number of lines required.

The main controller is annotated with @RefreshScope so that configuration changes are picked up
when /refresh is called.