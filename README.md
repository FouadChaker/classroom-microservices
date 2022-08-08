# classroom-microservices
microservices system for classroom management using Spring boot and Spring cloud.

# architecture
Depending on the subject proposed, we will choose the following main microservices:
- *course microservice*: offers a REST API to list modules.
- *teacher microservice*: offers a REST API to list teachers.
- *Config Service*: Configuration service, whose role is to centralize the configuration files of the different microservices in a single place.
- *Proxy Service*: Gateway responsible for routing a request to one of the instances of a service, so as to automatically manage the load distribution (ZUUL).
- *Discovery Service*: Service allowing the registration of instances of services in order to be discovered by other services (Eureka).
- *Front-end microservice*: allows you to display web pages, and represents a client for other REST APIs
