# Closet Collective

Closet Collective is an online clothing marketplace that allows you to buy, sell, and swap clothes with other users. You can browse through a variety of categories, such as tops, bottoms, dresses, accessories, etc., and find the perfect outfit for any occasion. You can also create your own listings and sell or swap your clothes with other users. The app uses a robust tech stack including Java, Spring Boot, MongoDB, Thymeleaf, HTML, Bootstrap, Maven, and is hosted on Heroku.

## Demo

You can view the deployed version of the app [here].

## Installation

To run the app locally, you need to have [Java], [Maven], and [MongoDB] installed on your machine. Then, follow these steps:

1. Clone this repository to your local machine.
2. Navigate to the project directory and run `mvn clean install` to build the project.
3. Run `mvn spring-boot:run` to start the application.
4. Open [http://localhost:8080] in your browser to view the app.

## Features

- User registration and login using [Spring Security].
- User profile page where you can edit your personal information, view your listings, and manage your orders.
- Browse page where you can explore different categories and see the listings of other users.
- Search functionality that allows you to filter the listings by keyword, price range, size, color, or condition.
- Listing page where you can see the details of a specific listing, such as title, description, price, photos, seller information, etc.
- Buy functionality that allows you to purchase a listing using [Stripe].
- Swap functionality that allows you to offer a swap with another user by selecting one of your own listings.
- Chat functionality that allows you to communicate with other users in real-time using [WebSocket].
- Rating and review functionality that allows you to rate and review other users after completing a transaction.

## Technologies

- Java - A general-purpose programming language that is widely used for developing web applications.
- Spring Boot - A framework that simplifies the development of stand-alone, production-grade Spring based applications.
- MongoDB - A cross-platform document-oriented database that stores data in JSON-like format.
- Thymeleaf - A modern server-side Java template engine that enables natural templating techniques.
- HTML - A markup language that defines the structure of web pages.
- Bootstrap - A popular front-end framework that provides a set of reusable components for creating responsive and mobile-first web pages.
- Maven - A software project management and comprehension tool that manages dependencies and builds.
- Heroku - A cloud platform that enables developers to build, run, and operate applications entirely in the cloud.
