from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

# LLM Router 
from routes.llm_router import router as llm_router


##########################################################################################

# Initialize the FastAPI application
app = FastAPI(title = "Ollama API Service", version = "1.0.0")

# CORS Middleware to avoid CORS issues if any
app.add_middleware(
    CORSMiddleware,
    allow_origins = ["*"],
    allow_credentials = True,
    allow_methods = ["*"],
    allow_headers = ["*"],
)

# Add the individual routers
app.include_router(llm_router, prefix = "/api/llm", tags = ["llm"])

##########################################################################################
