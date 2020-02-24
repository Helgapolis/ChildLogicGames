package ru.bazalikova.childlogicgames.puzzle

class PuzzlePresenter(
    private val view: IPuzzleView,
    private val model: IPuzzleModel,
    private val navigation: IPuzzleNavigation) {

    fun onViewCreated() {
        for (i in 0 until model.puzzleCount()) {
            val drawableId: Int = model.puzzleResId(i)
            view.addPuzzleView(drawableId)
        }

        showExample()
    }

    private fun showExample() {
        val expression = model.getExpression()
        val answers = model.getAnswers()
        view.setExample(expression, answers.map { it.toString() })
    }

    fun onStart() {
        /* do nothing */
    }

    fun onStop() {
        /* do nothing */
    }

    fun onAnswerBtnClicked(btnIndex: Int, answer: String) {
        for (btn in 0 until model.getAnswersSize()) {
            view.setAnswerBtnType(btn, IPuzzleView.AnswerType.UKNOWN)
        }

        val result = model.checkAnswer(answer)

        if (result) {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.RIGHT)
            view.showPuzzles(answer.toInt())

            if (model.isLastStep()) {
                view.setGameOver()
            } else {
                view.setNextButton(true)
            }
        } else {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.INCORRECT)
        }
    }

    fun onNextBtnClicked() {
        model.setNextStep()

        view.setNextButton(false)

        for (btnIndex in 0 until model.getAnswersSize()) {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.UKNOWN)
        }

        showExample()
    }

    fun onCancelBtnClicked() {
        navigation.finish()
    }
}