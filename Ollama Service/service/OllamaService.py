import ollama 


class OllamaService:    
    def generate_response(self, 
                          prompt: str, 
                          system_prompt : str = "",
                          model : str = "llama3.2") -> dict:
        """
        Function to generate Ollama response 

        Args
        ---
        prompt (required) : The user prompt
        system_prompt     : The system prompt 
        model             : The model to be used, defaults to llama3.2 
        """
        
        response = dict() 

        try:
            # Get the Ollama Respone 
            ollama_response = ollama.chat(
                model = model,
                messages = [
                    {
                        "role" : "system",
                        "content" : system_prompt,
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
                "message": {
                    "role": message_obj.role if message_obj else None,
                    "content": message_obj.content if message_obj else None,
                },
            }

        return ollama_json




