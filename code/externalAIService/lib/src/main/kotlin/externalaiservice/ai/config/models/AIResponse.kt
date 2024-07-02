package externalaiservice.ai.config.models

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
