from fastapi.routing import APIRouter
from fastapi.responses import JSONResponse


from models.LLMGenerationRequest import LLMGenerationRequest
from ollama_utils import OllamaLLMService

router = APIRouter()

# LLM Generation Request
ollama_agent = OllamaLLMService()


@router.post("/generate")
async def generate_llm_response(request : LLMGenerationRequest):

    # Add the system prompt 
    ollama_agent.set_system_prompt(request.systemPrompt)

    # Generate the response
    ollama_agent_response = ollama_agent.generate_response(request.prompt)

    if ollama_agent_response.get("isGenerated"):
        return JSONResponse(
            status_code = 201, 
            content = ollama_agent_response
        )
    else:
        return JSONResponse(
            status_code = 500,
            content = ollama_agent_response
        )
