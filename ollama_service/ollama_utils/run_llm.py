import ollama 


class OllamaLLMService: 

    def __init__(self, system_prompt : str = ""):

        # Set the model 
        self.__model = "llama3.2" # Will update later 

        # System prompt setting
        self.__system_prompt = system_prompt
    
    def set_system_prompt(self, new_prompt : str):
        self.__system_prompt = new_prompt

    
    def generate_response(self, prompt: str) -> dict:
        
        response = dict() 

        try:
            # Get the Ollama Respone 
            ollama_response = ollama.chat(
                model = self.__model,
                messages = [
                    {
                        "role" : "system",
                        "content" : self.__system_prompt,
                    }, 
                    {
                        "role" : "user",
                        "content" : prompt,
                    }
                ]
            )

            # Extract relevant facts 
            response["isGenerated"] = True
            response["content"] = self.__parse_content(ollama_response) 
            

        except ollama.ResponseError as e:
            print(f"ERROR in Generating Response: {str(e)}")
            
            # Extract relevant facts
            response["isGenerated"] = False 

        finally:
            pass 
            
        return response

    def __parse_content(self, ollama_response : ollama.ChatResponse) -> dict:
        
        # Extract fields from ChatResponse manually
        message_obj = ollama_response.message
        content = message_obj.content if message_obj else ""

        # Parse the content 
        ollama_json = {
                "model": ollama_response.model,
                "created_at": ollama_response.created_at,
                "done": ollama_response.done,
                "done_reason": ollama_response.done_reason,
                "total_duration": ollama_response.total_duration,
                "load_duration": ollama_response.load_duration,
                "prompt_eval_count": ollama_response.prompt_eval_count,
                "prompt_eval_duration": ollama_response.prompt_eval_duration,
                "eval_count": ollama_response.eval_count,
                "eval_duration": ollama_response.eval_duration,
                "message": {
                    "role": message_obj.role if message_obj else None,
                    "content": message_obj.content if message_obj else None,
                },
            }

        return ollama_json




