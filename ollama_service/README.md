## Ollama_Services
**Note:** _This is a Python repository and requires that Python be installed appropriately._

This is a service that is used to generate responses from a locally deployed LLMs.

## Setup
There is a one time setup that can be done by using the following command:

```bash
pip install -r requirements.txt
```
This will install all the required modules needed to run the code.

Also, the code requires `Ollama`, which is an open source library for running LLMs locally. 

If using macOS, it is recommended to use homebrew and install ollama. 

## Running the Ollama Service
To run the Ollama service, do the following:
```bash
ollama serve
ollama run <model name>
```
This will fetch and start the ollama server locally. 

After setting up the Ollama server, you can run the Python part by the following:
```bash
uvicorn app:app --reload
```

This wlll start the server on port 8000, and API calls can be easily called over this. The server uses FastAPI for asynchronous and scalable operations.
