package ru.bazalikova.puzzle.presentation

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getColor
import kotlinx.android.synthetic.main.activity_puzzle.view.*
import ru.bazalikova.puzzle.R
import javax.inject.Inject
import kotlin.random.Random

class PuzzleView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr), IPuzzleView {
    private val hiddenPuzzles: MutableList<ImageView> = mutableListOf()
    private lateinit var answersBtn: List<Button>

    @Inject
    lateinit var presenter: PuzzlePresenter

    fun onFinishInflate(presenter: PuzzlePresenter) {
        this.presenter = presenter

        val btns: MutableList<Button> = arrayListOf()
        btns.add(actPuzzleBtnAnsw1)
        btns.add(actPuzzleBtnAnsw2)
        btns.add(actPuzzleBtnAnsw3)
        btns.add(actPuzzleBtnAnsw4)

        answersBtn = btns

        actPuzzleNextButton.setOnClickListener {
            presenter.onNextBtnClicked()
        }

        actPuzzleCancelButton.setOnClickListener {
            presenter.onCancelBtnClicked()
        }
    }

    override fun addPuzzleViews(imagePrefix: String, puzzleSize: Int) {
        actPuzzleContentLayout.removeAllViews()
        hiddenPuzzles.clear()

        for (puzzleIndex in 0 until puzzleSize) {
            val drawableId = context.resources.getIdentifier(
                imagePrefix + puzzleIndex.toString(),
                "drawable",
                context.packageName
            )

            addPuzzleView(drawableId)
        }
    }

    private fun addPuzzleView(imageId: Int) {
        val imageView = ImageView(rootView.context)
        imageView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        imageView.setImageResource(imageId)
        imageView.visibility = View.INVISIBLE
        hiddenPuzzles.add(imageView)
        actPuzzleContentLayout.addView(imageView)
    }

    override fun setExample(expression: String, answers: List<String>) {
        actPuzzleExprTxt.visibility = View.VISIBLE
        actPuzzleExprTxt.text = expression

        for (btnIndex in answersBtn.indices) {
            answersBtn[btnIndex].text = answers[btnIndex]
            answersBtn[btnIndex].visibility = View.VISIBLE

            answersBtn[btnIndex].setOnClickListener {
                presenter.onAnswerBtnClicked(btnIndex, answers[btnIndex])
            }
        }
    }

    override fun setAnswerBtnType(btnIndex: Int, answerType: IPuzzleView.AnswerType) {
        when (answerType) {
            IPuzzleView.AnswerType.RIGHT ->
                answersBtn[btnIndex].setBackgroundColor(
                    getColor(
                        rootView.context,
                        R.color.btn_right_answer_color
                    )
                )
            IPuzzleView.AnswerType.INCORRECT ->
                answersBtn[btnIndex].setBackgroundColor(
                    getColor(
                        rootView.context,
                        R.color.btn_fail_answer_color
                    )
                )
            IPuzzleView.AnswerType.UKNOWN ->
                answersBtn[btnIndex].setBackgroundColor(
                    getColor(
                        rootView.context,
                        android.R.color.transparent
                    )
                )
        }
    }

    override fun showCorrectAnswerAnimation(action: Runnable) {
        Handler().postDelayed(action, 200) //TODO: animation will be here
    }

    override fun setNextButton(visibility: Boolean) {
        actPuzzleNextButton.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun showPuzzles(answer: Int) {
        if (hiddenPuzzles.size < answer)
        {
            return
        }

        for (puzzleIndex in 0 until answer) {
            val puzzleView = hiddenPuzzles[Random.nextInt(0, hiddenPuzzles.size)]
            puzzleView.visibility = View.VISIBLE
            hiddenPuzzles.remove(puzzleView)
        }
    }

    override fun setGameOver(visibility: Boolean) {
        actPuzzleGoodResultTextView.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}