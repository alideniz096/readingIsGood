# Introduction
ReadingIsGood is an online books retail firm that operates only on the Internet. Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day.

At the beginning of the project, first of all, the desired features were examined and the database table structure was created accordingly. To be practical H2 Database had been selected for this project context.

The Spring Boot project was created for the development environment. The required dependencies has been added to the project and the preparation process of the development environment has been completed.

First of all, entity classes started to be written based on the created tables. Then, entity repositories were written accordingly. Request, Response and dto's have been created for controller and service implementations. During the service implementations, the validation was carried out by considering the edge cases. In addition, a meaningful display of error states was achieved with execption handling.

The requests were converted to dto and then entity to achieve the layered structure. An example flow would be: Customer Request $->$ Customer Request dto $->$ Customer Entity $->$ CRUD Operation $->$ Response dto $->$ Response.

Within the scope of the project requirements, the authentication process was carried out with spring boot web authentication. Swagger was implemented for ease access for the endpoints.

---

# Tech Stack
1. Java11
2. SpringBoot 2.5.9
3. H2 Database
4. Maven
5. IntelliJ Idea
6. Swagger

---

# System Requirements
1. The system shall allow to registration of the new user
2. The system shall allow placement of the orders by the user.
3. The system should provide authentication via Spring Boot Web Security.
4. The system should be able to follow the stocks of the books and update them according to the order
5. The system should be able to list the all orders of the user.
6. The system should display monthly statistics.

# Additional Details

In order to send a request with Swagger, the token must first be retrieved by calling the tokenApi/getToken service. Then the generated bearer token must be sent in the HTTP request header, otherwise a 403 Forbidden error will be received. In short,  the application cannot be accessed without being authenticated.

Postman collection is accessible in the postman folder within the project files. The application can be easily tested thanks to the postman collection.
