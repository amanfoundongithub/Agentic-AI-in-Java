from pydantic import BaseModel


class LLMGenerationRequest(BaseModel):

    # System prompt
    systemPrompt: str 

    # User prompt 
    prompt: str 