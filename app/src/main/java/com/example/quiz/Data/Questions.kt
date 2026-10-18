package com.example.quiz

sealed interface Question {
    val text: String
}

data class TextQuestion(
    override val text: String,
    val acceptedAnswers: List<String>
) : Question {
    fun isCorrect(input: String): Boolean {
        val normalized = input.trim()
        return acceptedAnswers.any { it.equals(normalized, ignoreCase = true) }
    }
}

data class SingleChoiceQuestion(
    override val text: String,
    val options: List<String>,
    val correctIndex: Int
) : Question {
    fun isCorrect(selectedIndex: Int): Boolean = selectedIndex == correctIndex
}

data class MultiChoiceQuestion(
    override val text: String,
    val options: List<String>,
    val correctIndices: Set<Int>
) : Question {
    fun isCorrect(selectedIndices: Set<Int>): Boolean = selectedIndices == correctIndices
}