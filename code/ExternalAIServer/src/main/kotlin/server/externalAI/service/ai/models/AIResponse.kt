package server.externalAI.service.ai.models

/**
 * A data class representing the response from an AI model.
 *
 * @see <a href="https://github.com/ollama/ollama">Ollama platform</a>
 * @see <a href="https://ollama.com/">Ollama models</a>
 *
 * @property model The name of the model that generated the response.
 * @property created_at The timestamp when the response was created.
 * @property response JSON string representing the entities and relations identified by the model.
 *                    The structure of the response is as follows:
 *                    {
 *                      "Entities": {
 *                        "EntityName1": ["Attribute1", "Attribute2", ...],
 *                        "EntityName2": ["Attribute1", "Attribute2", ...],
 *                        ...
 *                      },
 *                      "Relations": {
 *                        "EntityName1": {"RelatedEntityName1": "RelationType", ...},
 *                        "EntityName2": {"RelatedEntityName2": "RelationType", ...},
 *                        ...
 *                      }
 *                    }
 *                    Example:
 *                    {
 *                      "Entities": {
 *                        "Product": ["ProductID", "ProductName", "Price"],
 *                        "OrderItem": ["OrderItemID", "OrderID", "ProductID", "Quantity"]
 *                      },
 *                      "Relations": {
 *                        "Product": {"OrderItem": "N"},
 *                        "OrderItem": {"Product": "1"}
 *                      }
 *                    }
 * @property done A boolean flag indicating whether the response generation is complete.
 * @property done_reason A string indicating the reason why the response generation is complete.
 * @property context An encoding of the conversation used in this response, this can be sent in the next request to keep a conversational memory.
 * @property total_duration The time spent generating the response.
 * @property load_duration The time spent in nanoseconds loading the model.
 * @property prompt_eval_count The number of tokens in the prompt.
 * @property duration The duration in milliseconds taken to generate the response.
 * @property eval_count The number of tokens in the response.
 * @property eval_duration The time in nanoseconds spent generating the response.
 */
data class AIResponse(
    val model: String,
    val created_at: String,
    val response: String,
    val done: Boolean,
    val done_reason: String,
    val context: List<Int>,
    val total_duration: Long,
    val load_duration: Long,
    val prompt_eval_count: Long,
    val duration: Long,
    val eval_count: Long,
    val eval_duration: Long
)

