package ru.bazalikova.puzzle.data

interface IPuzzleModel {
    fun getExpression(): String
    fun getAnswers(): List<Int>
    fun getAnswersSize(): Int
    fun getExamplesSize(): Int
    fun checkAnswer(answer: String): Boolean
    fun setNextStep()
    fun isLastStep(): Boolean

    fun puzzleCount(): Int
    fun puzzleResId(puzzleIndex: Int): Int
}