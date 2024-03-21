# Delivery API Gateway

## Overview
The Delivery API Gateway serves as the front door to the **Delivery** application, orchestrating interactions between clients and the suite of underlying microservices. It simplifies the client interface, provides security features like authentication and authorization, and routes requests to appropriate backend services.

## Architecture Interaction
The API Gateway plays a central role in the Delivery application's microservices architecture, facilitating seamless communication and providing a single entry point for all services. Key components it interacts with include:

- [Delivery Users Microservice](https://github.com/KyryloBulyk/delivery-users): Manages user data, registration, authentication, and user profile management. The API Gateway authenticates user credentials and forwards user-centric requests to this service.

- [Delivery Configuration](https://github.com/KyryloBulyk/delivery-configuration): Manages and centralizes configuration settings across the Delivery application's microservices. The API Gateway fetches its configuration from this service to dynamically adapt to changes.

- [Delivery Discovery](https://github.com/KyryloBulyk/delivery-discovery): Enables service discovery within the microservices architecture, helping the API Gateway to locate and route requests to the available instances of microservices efficiently.

Through these interactions, the Delivery API Gateway enhances the application's scalability, security, and manageability.

## Getting Started

### Prerequisites
Before setting up the Delivery API Gateway, ensure the following are installed and configured on your system:
- Java 11 or later.
- Maven, for dependency management and project lifecycle management.
- An API testing tool like Postman or cURL for sending requests.

### Configuration
The `application.properties` or `application.yml` file needs to be configured with the appropriate settings for service discovery, security policies, and routing configurations.

### Running the Application
Launching the Delivery API Gateway involves the following steps:

1. Navigate to the project's root directory in your terminal.
2. Run the application using Maven with the following command:

```bash
mvn spring-boot:run
```

## Contributing
Contributions are welcome to enhance the features, fix bugs, or improve the documentation of the Delivery API Gateway. Please refer to the contributing guidelines in the project's GitHub repository.
