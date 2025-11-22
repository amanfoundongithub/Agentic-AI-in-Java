# Agentic AI Implementation (Java)

This is the agentic part of the project. 
The whole agent has been written by using native tools 
of Java Spring Boot. Feel free to go through this README.md to get a hang
of how to get started with the project. 

## System Requirements
Ensure that you have the following installed in your system
before you start the project:
- JDK 23 (The project is configured to run on the same)
- An IDE (preferably `Intellij IDEA`)

## Initial Setup

### Non-Java Setup
Before you start to work on this project, you need to have the following things configured
in your machine:
- **MongoDB**: Install the `MongoDB Compass` from [here](https://www.mongodb.com/docs/compass/install/). Use the 
`mongod` command to start the MongoDB daemon that will run in the background.

- **Redis**: Install the `Redis` client from [here](https://redis.io/docs/latest/operate/oss_and_stack/install/archive/install-redis/). Redis is
needed to cache all the jobs' results into a fast retrieval tool. 


### Java Setup
Sync the project with Gradle, ensuring all the modules are synced up-to-date. This can be easily
done by any well-known IDEs (preferably Intellij). 

After syncing up, you need to go to the `/src/main/resources/` folder and create an `application-secret.yaml` file there.

(_The skeleton of the required file is already provided in a `.txt` file in the same directory. Populate the secret values as deemed 
necessary. You might need to create some more setup for the same._)


Spring requires that during execution these variables be present, 
so ensure that you populate them before starting the program, so make sure to complete this step before
running the project. 

## Running The Project
**Important**: _Before running the agent, please ensure that you have
the Python service up and running. Instructions to run Python service are 
given [here](https://github.com/amanfoundongithub/Agentic-AI-in-Java/blob/main/Ollama%20Service/README.md)._

To run the agent, you can go to the `AgentApplication.java` and start debugging
the application.

When you start debugging, the agent will become active on the port `6900`. 

This project has `Swagger-UI` enabled, so you can play with the APIs using the following link, once the server
is up:
``` 
http://localhost:6900/swagger-ui/index.html
```

## LLMs Supported
The following LLMs are integrated as part of the project:
- `llama3.2`

## Tools Supported
The following tools are implemented as part of the project:
- `google_search` : Fetches results from Google
- `web_scrapper` : Scrapes a website to fetch text details 

