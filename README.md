# Comprehensive Spring Web API Project with Kotlin

## Overview

This project is a multi-component application built with Spring Boot and Kotlin. It includes two servers: one for handling API requests and another for communicating with a Large Language Model (LLM) API. The project also includes a web client for interfacing with the API, and an SQL relational database for storing information. The project leverages the Siren library for representing HTTP responses and their errors.
The main objective is to transform textual descriptions into Entity-Relation Diagrams through the use of LLM.

## Features

- RESTful API endpoints with Siren representations
- CRUD operations with SQL relational model
- Authentication and authorization
- Communication with an LLM API
- Web client interface
- Comprehensive error handling

## Components

1. **API Server**: Handles authentication and CRUD operations, communicates with the LLM server.
2. **LLM Server**: Communicates with the LLM API and provides responses.
3. **Web Client**: A frontend interface that interacts with the API server.
4. **SQL Database**: Stores application data.

## Requirements

- JDK 11 or higher
- Gradle 6.0 or higher
- TypeScript (for the web client)
- PostgreSQL or another SQL database

## Getting Started

### Clone the repository

Build and Run the Servers
API Server

Navigate to the API server directory and build the project:

cd code/jvm
./gradlew build
./gradlew bootRun

LLM Server

Navigate to the LLM server directory and build the project:

cd code/ExternalAiServer
./gradlew build
./gradlew bootRun

Setup the Web Client

Navigate to the web client directory, install dependencies, and start the client:

cd code/js
npm install
npm run start

The web client will start on http://localhost:9000.

Database Setup

Ensure your PostgreSQL (or other SQL database) is running and create the required database and tables. Update the application.yml configuration file with your database credentials.