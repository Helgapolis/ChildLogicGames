package ru.bazalikova.childlogicgames.puzzle

class PuzzlePresenter(private val view: IPuzzleView, private val repository: IPuzzleRepository, private val navigation: IPuzzleNavigation): IPuzzlePresenter
{
    private var step: Int = 0

    override fun onViewCreated()
    {
        for (i in 0 until repository.puzzleCount())
        {
            val drawableId: Int = repository.puzzleResId(i)
            view.addPuzzleView(drawableId)
        }

        showExample()
    }

    private fun showExample()
    {
        val expression = repository.getExpression(step)
        val answers = repository.getAnswers(step)
        view.setExample(expression, answers.map { it.toString() })
    }

    override fun onStart()
    {
        /* do nothing */
    }

    override fun onStop()
    {
        /* do nothing */
    }

    override fun onAnswerBtnClicked(btnIndex: Int, answer: String)
    {
        for (btn in 0 until repository.getAnswersSize())
        {
            view.setAnswerBtnType(btn, IPuzzleView.AnswerType.UKNOWN)
        }

        val result = repository.checkAnswer(step, answer)

        if (result)
        {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.RIGHT)
            view.showPuzzles(answer.toInt())

            val examplesSize = repository.getExamplesSize()
            if (step == examplesSize - 1)
            {
                view.setGameOver()
            }
            else
            {
                view.setNextButton(true)
            }
        }
        else
        {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.INCORRECT)
        }
    }

    override fun onNextBtnClicked()
    {
        step += 1

        view.setNextButton(false)

        for (btnIndex in 0 until repository.getAnswersSize())
        {
            view.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.UKNOWN)
        }

        showExample()
    }

    override fun onCancelBtnClicked()
    {
        navigation.finish()
    }
}