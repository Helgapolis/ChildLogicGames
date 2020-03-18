package ru.bazalikova.puzzle.data

interface IPuzzleRepository {
    fun buildLessons()
    fun getExpression(): String
    fun getAnswers(): List<Int>
    fun getAnswersSize(): Int
    fun checkAnswer(answer: String): Boolean
    fun setNextStep()
    fun setNextLesson()
    fun isLastSample(): Boolean
    fun hasNextLesson(): Boolean

    fun puzzleCount(): Int
    fun puzzlePrefix(): String
}