package ru.bazalikova.childlogicgames.puzzle

interface IPuzzleRepository
{
    fun getExpression(step: Int): String
    fun getAnswers(step: Int): List<Int>
    fun getAnswersSize(): Int
    fun getExamplesSize(): Int
    fun checkAnswer(step: Int, answer: String): Boolean

    fun puzzleCount(): Int
    fun puzzleResId(puzzleIndex: Int): Int
}