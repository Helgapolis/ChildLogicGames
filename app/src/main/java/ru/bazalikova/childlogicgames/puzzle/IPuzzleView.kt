package ru.bazalikova.childlogicgames.puzzle

interface IPuzzleView {
    fun addPuzzleView(imageId: Int)
    fun setExample(expression: String, answers: List<String>)
    fun setAnswerBtnType(
        btnIndex: Int,
        answerType: AnswerType
    )

    fun setNextButton(visibility: Boolean)
    fun showPuzzles(answer: Int)
    fun setGameOver()

    enum class AnswerType {
        RIGHT,
        INCORRECT,
        UKNOWN
    }
}