from pydantic import BaseModel


class LLMGenerationRequest(BaseModel):

    # System prompt
    system_prompt: str 

    # User prompt 
    user_prompt: str 