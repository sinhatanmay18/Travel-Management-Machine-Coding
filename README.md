# Travel-Management-Machine-Coding

## System Overview

The system provides a robust platform where travel packages can be created and managed, destinations within those packages can be defined, and specific activities at each destination can be outlined. It supports different types of passengers (Standard, Gold, Premium) with various benefits, including activity sign-ups according to their membership level.

## Features

• Travel Package Management: Create and manage travel packages with unique itineraries. <br>

• Destination and Activity Management: Assign destinations to travel packages and specify activities available at each destination, including details like cost and capacity. <br>

• Passenger Enrollment: Enroll passengers in travel packages and manage their activity sign-ups, with support for different passenger types (Standard, Gold, Premium). <br>

• Capacity Management: Ensure activities do not exceed their capacity, automatically preventing overbooking. <br>

• Itinerary and Enrollment Reporting: Generate detailed reports on travel itineraries and passenger enrollments, including activities signed up for by each passenger. <br>

## High Level Design

![HLD - Travel Management](https://github.com/sinhatanmay18/Travel-Management-Machine-Coding/assets/76418883/e1ece378-5bcf-4895-b206-27fd6c9ec5e6)

## 1. Service Layer:

#### a. Travel Package Service: Manages operations related to travel packages, such as creation, updates, and querying of packages.
#### b. Destination Service: Handles the logistics of destinations within travel packages, including adding destinations, and managing activities available at each destination.
#### c. Activity Service: Responsible for the activities within each destination, including enrollment of passengers, capacity checks, and activity detail management.

## 2. Repository Layer

#### a. Travel Package Map: Acts as a storage for travel packages, which could be an in-memory database substitute to keep track of all the travel package instances.
#### b. Destination Map: Similar to the travel package map, it's a storage repository for all destinations, likely keyed by an identifier such as a name or ID.
#### c. Activity Map: Maintains a repository of activities, ensuring that each activity is associated with one destination only.


## 3. Models

#### a. Travel Package: Represents travel packages and contains attributes such as name, capacity, destinations, and passengers.
#### b. Destination: Represents a destination within a travel package and contains a list of activities available at that destination.
#### c. Activity: Represents an activity with attributes like name, description, cost, and capacity.

## 4. Passenger Types:

#### a. Standard Passenger: A passenger type with a balance attribute, where the cost of activities is deducted from the balance.
#### b. Gold Passenger: Similar to a standard passenger but receives a discount on activity costs.
#### c. Premium Passenger: Can sign up for activities without any cost.

## Low Level Design ( UML Diagram )

![UML Diagram - Travel Management](https://github.com/sinhatanmay18/Travel-Management-Machine-Coding/assets/76418883/f831bb08-c1fc-4f68-b9d4-8eda72cc2d5d)

## Getting Started:
    1. Java JDK 8 or higher
    2. Maven (for dependency management and building the project)

## Clone the Repository:
    git clone https://github.com/yourusername/travel-management-system.git

## Build the Project
    mvn clean install

