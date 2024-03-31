# 🍲 Delivery Application Project 🚚

## 📌 Overview

The Delivery application 📦 is a comprehensive solution designed for restaurants 🍽️, delivery services 🛵, and consumers 🧑‍🤝‍🧑 in the food industry. It facilitates seamless interactions between these entities, enhancing the efficiency of food ordering, delivery, and consumption processes.

This repository serves as the main hub 🌐 for the Delivery application, which is built on a microservices architecture 🏗️. This architecture enables the modular development of services, each responsible for a specific piece of functionality. This approach enhances scalability, maintainability, and deployment efficiency.

## 🌐 Architecture Interaction

The API Gateway 🚪 plays a central role in the Delivery application's microservices architecture, facilitating seamless communication and providing a single entry point for all services. Key components it interacts with include:

- [Delivery Users Microservice](https://github.com/KyryloBulyk/delivery-users) 👤: Manages user data, registration, authentication, and user profile management. The API Gateway authenticates user credentials and forwards user-centric requests to this service.

- [Delivery Configuration](https://github.com/KyryloBulyk/delivery-configuration) ⚙️: Manages and centralizes configuration settings across the Delivery application's microservices. The API Gateway fetches its configuration from this service to dynamically adapt to changes.

- [Delivery Discovery](https://github.com/KyryloBulyk/delivery-discovery) 🔍: Enables service discovery within the microservices architecture, helping the API Gateway to locate and route requests to the available instances of microservices efficiently.

- [Delivery Product Microservice](https://github.com/KyryloBulyk/delivery-product-microservice) 📦: Manages product catalog and order processing, handling product information, categories, and user orders, integrating seamlessly with the rest of the Delivery application ecosystem.

Through these interactions, the Delivery API Gateway enhances the application's scalability, security, and manageability.

## ✨ Key Features

- **Microservices Architecture** 🏗️: The Delivery application is composed of several loosely coupled microservices, each responsible for distinct features such as user management, order processing, and delivery tracking. This design allows for independent development and scaling of services.

- **JWT Authentication** 🔐: The application implements JSON Web Tokens (JWT) for secure authentication and authorization. It utilizes both access and refresh tokens to manage user sessions and ensure secure access to resources.

- **Docker Compose Integration** 🐳: For easy deployment and local development, the project includes a Docker Compose file. This allows for the orchestration of multiple containers that represent the microservices, simplifying the setup and running of the entire application stack.

## 🚀 Running the Project Locally

To run the Delivery application locally using Docker Compose, follow these steps:

1. **Prerequisites** 📋: Ensure you have Docker 🐳 and Docker Compose installed on your system.

2. **Clone the Repository** 📥: Clone this repository to your local machine using Git:

   ```bash
   git clone https://github.com/yourusername/delivery-application.git
   ```
3. Navigate to the Project Directory 📁:

    ```bash
   cd delivery-application
   ```
4. Launch the Application 🌟:
    
    ``` bash
    docker-compose up
   ```
This command builds the Docker images for each microservice (if not already built) and starts the containers defined in the docker-compose.yml file.

5. Accessing the Application 🌍: 

Once the containers are up and running, you can access the API Gateway and other services as configured in the Docker Compose file and the individual service properties.

## 🤝 Contributing
Contributions are welcome to enhance the features, fix bugs, or improve the documentation of the Delivery API Gateway. Please refer to the contributing guidelines in the project's GitHub repository.
