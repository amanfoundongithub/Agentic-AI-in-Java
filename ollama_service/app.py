from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from ollama_utils import * 

# App Initialization
app = FastAPI(title = "Ollama API Service",
              version = "1.0.0")

# Add CORS Middleware for help 
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

