Agentic AI Implementation (Java)
---
This is the agentic part of the project. 
The whole agent has been written by using native tools 
of Java. Feel free to go through the instructions to run the agent
locally.

## System Requirements
Ensure that you have the following installed in your system
before you start the project:
- JDK 23 (The project is configured to run on the same)
- An IDE (preferably `Intellij IDEA`)

## Initial Setup
To setup the project, you need to create an `application-secret.yaml` in the `src/main/resources/` folder. 
There is a `.txt` file provided. Copy that into the yaml file and populate the secret credentials
in it (API Keys and stuff).

Spring requires that during execution these variables be present, 
so ensure that you populate them before starting the program.

Other than that, there is no other setup required for the project. The IDE that
you chose (Eclipse or Intellij) will sync your gradle project themselves
and install the required dependencies.

Ensure that you sync the repositories before starting the Java portion.

## Running The Agent
**Important**: _Before running the agent, please ensure that you have
the Python service up and running. Instructions to run Python service are 
given [here](https://github.com/amanfoundongithub/Agentic-AI-in-Java/blob/main/Ollama%20Service/README.md)._

To run the agent, you can go to the `AgentApplication.java` and start debugging
the application.

When you start debugging, the agent will become active on the port 5200. 

Any queries to the agent can be sent to the following URL:
```
http://localhost:5200/api/agent/run
```
This will be a `POST` request with a body of the following type:
```json
{
  "prompt": "Summarize the results of Bihar elections 2025. Tell how many seats each party won!",
  "systemPrompt": "You are an election expert",
  "model": "llama3.2"
}
```
The agent provides a response as follows:
```json 
{
    "generated": true,
    "text": "BJP: 89 Seats\nJD(U): 85 Seats\nRJD: 25 Seats\nLJP: 19 Seats\nINC: 6 Seats\nAIMIM: 5 Seats\nHAMS: 5 Seats"
}
```

## Further Improvements
For now, the whole ecosystem adopts only one tool (namely `Google Search`) and 
supports only one LLM (LLAMA 3.2) but the code has been written 
with principles that any extension can be made to include more tools or
LLMs in future without changing the core ReACT logic.

Moreover, no test cases have been written for the agent, which does seems troubling since
we never really tested on the edge cases for the agent. 
