# cloud-discovery-client

This app consumes the services, discovering them from Eureka.

There are several controllers, and they are developed in order of 'refinement', as the course progressed
and introduced new features
- the first uses DiscoveryClient directly
- the second uses LoadBalancerClient and Ribbon
- the third uses Feign clients
- the fourth uses services, which are annotated with Hystrix, one of which intentionally fails with a 404 to cause a fallback method to be invoked 