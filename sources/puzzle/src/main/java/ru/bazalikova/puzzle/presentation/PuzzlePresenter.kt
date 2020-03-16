package ru.bazalikova.puzzle.presentation

import ru.bazalikova.puzzle.IPuzzleMediator
import ru.bazalikova.puzzle.data.IPuzzleRepository
import javax.inject.Inject

class PuzzlePresenter @Inject constructor(private val repository: IPuzzleRepository) {

    private var view: IPuzzleView? = null
    private var mediator: IPuzzleMediator? = null

    fun attachView(view: IPuzzleView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachMediator(mediator: IPuzzleMediator?)
    {
        this.mediator = mediator
    }

    fun detachMediator()
    {
        this.mediator = null
    }

    fun onViewCreated() {
        repository.buildLessons()
        view?.addPuzzleViews(repository.puzzlePrefix(), repository.puzzleCount())
        showExample()
    }

    private fun showExample() {
        val expression = repository.getExpression()
        val answers = repository.getAnswers()
        view?.setExample(expression, answers.map { it.toString() })
    }

    fun onStart() {
        /* do nothing */
    }

    fun onStop() {
        /* do nothing */
    }

    fun onAnswerBtnClicked(btnIndex: Int, answer: String) {
        for (btn in 0 until repository.getAnswersSize()) {
            view?.setAnswerBtnType(btn, IPuzzleView.AnswerType.UKNOWN)
        }

        val result = repository.checkAnswer(answer)

        if (result) {
            view?.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.RIGHT)
            view?.showPuzzles(answer.toInt())

            if (repository.isLastSample()) {
                view?.setGameOver(true)

                if (repository.hasNextLesson())
                {
                    view?.setNextButton(true)
                }
            } else {
                view?.showCorrectAnswerAnimation(action = Runnable { onNextBtnClicked() })
            }
        } else {
            view?.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.INCORRECT)
        }
    }

    fun onNextBtnClicked() {
        if (repository.isLastSample()) {
            repository.setNextLesson()
            view?.setGameOver(false)
            view?.addPuzzleViews(repository.puzzlePrefix(), repository.puzzleCount())
        }
        else {
            repository.setNextStep()
        }

        view?.setNextButton(false)

        for (btnIndex in 0 until repository.getAnswersSize()) {
            view?.setAnswerBtnType(btnIndex, IPuzzleView.AnswerType.UKNOWN)
        }

        showExample()
    }

    fun onCancelBtnClicked() {
        mediator?.finish()
    }
}