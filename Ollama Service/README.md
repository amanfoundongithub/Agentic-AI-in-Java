## Ollama Services (Python)
**Note:** _This is a Python repository and requires that Python be installed appropriately._

This is the Python part that exposes an endpoint for the LLM to generate responses. 

## Initial Setup
This repository is entirely writtten in Python and requires at least version 3.8. You can install Python from [here](https://www.python.org/).

After setting up the Python, you can install all the requirements for this project by using the following commnad:

```bash
pip install -r requirements.txt
```
This will successfully install all the required modules.

The project uses `Ollama` for running the LLMs locally. In macOS, you might want to install it using homebrew.

## Running the Service
To run the service, we will first setup the LLMs to accept and generate responses locally by using the following commands:
```bash
ollama serve
ollama run <model name>
```
This will fetch and start the ollama server locally. For now, we are using `llama3.2` for the project. 

After the LLMs are configured to run, you can expose them using FastAPI endpoints as follows:

```bash
uvicorn app:app --reload
```

The server will be exposed at port `8000` and can be easily accessed using localhost. 

For the route, you might refer to the individual routers in the `/routes` directory. 

