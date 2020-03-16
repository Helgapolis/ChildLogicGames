package ru.bazalikova.puzzle.presentation

interface IPuzzleView {
    fun addPuzzleViews(imagePrefix: String, puzzleSize: Int)
    fun setExample(expression: String, answers: List<String>)
    fun setAnswerBtnType(
        btnIndex: Int,
        answerType: AnswerType
    )

    fun showCorrectAnswerAnimation(action: Runnable)
    fun setNextButton(visibility: Boolean)
    fun showPuzzles(answer: Int)
    fun setGameOver(visibility: Boolean)

    enum class AnswerType {
        RIGHT,
        INCORRECT,
        UKNOWN
    }
}