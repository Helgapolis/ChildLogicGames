package ru.bazalikova.childlogicgames.puzzle

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import ru.bazalikova.childlogicgames.R
import kotlin.random.Random

class PuzzleView(private val rootView: View): IPuzzleView
{
    private val expressionTextView: TextView = rootView.findViewById(R.id.act_puzzle_expr_txt) as TextView
    private val contentLayout: FrameLayout = rootView.findViewById(R.id.act_puzzle_frm) as FrameLayout

    private val hiddenPuzzles: MutableList<ImageView> = arrayListOf()
    private val answersBtn: List<Button>

    private val captionTextView: TextView
    private val nextButton: Button
    private val cancelButton: Button

    private lateinit var presenter: IPuzzlePresenter

    init
    {

        val btns: MutableList<Button> = arrayListOf()
        btns.add(rootView.findViewById(R.id.act_puzzle_btn_answ_1) as Button)
        btns.add(rootView.findViewById(R.id.act_puzzle_btn_answ_2) as Button)
        btns.add(rootView.findViewById(R.id.act_puzzle_btn_answ_3) as Button)
        btns.add(rootView.findViewById(R.id.act_puzzle_btn_answ_4) as Button)

        answersBtn = btns

        captionTextView = rootView.findViewById(R.id.good_result_title_txt) as TextView
        nextButton = rootView.findViewById(R.id.act_puzzle_btn_next) as Button
        cancelButton = rootView.findViewById(R.id.act_puzzle_btn_cancel) as Button
    }

    fun onFinishInflate(presenter: IPuzzlePresenter)
    {
        this.presenter = presenter

        nextButton.setOnClickListener{
            presenter.onNextBtnClicked()
        }

        cancelButton.setOnClickListener{
            presenter.onCancelBtnClicked()
        }
    }

    override fun addPuzzleView(imageId: Int)
    {
        val imageView = ImageView(rootView.context)
        imageView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        imageView.setImageResource(imageId)
        imageView.visibility = View.INVISIBLE
        hiddenPuzzles.add(imageView)
        contentLayout.addView(imageView)
    }

    override fun setExample(expression: String, answers: List<String>)
    {
        expressionTextView.visibility = View.VISIBLE
        expressionTextView.text = expression

        for (btnIndex in answersBtn.indices)
        {
            answersBtn[btnIndex].text = answers[btnIndex]
            answersBtn[btnIndex].visibility = View.VISIBLE

            answersBtn[btnIndex].setOnClickListener{
                presenter.onAnswerBtnClicked(btnIndex, answers[btnIndex])
            }
        }
    }

    override fun setAnswerBtnType(btnIndex: Int, answerType: IPuzzleView.AnswerType)
    {
        when (answerType) {
            IPuzzleView.AnswerType.RIGHT ->
                answersBtn[btnIndex].setBackgroundColor(
                    getColor(
                        rootView.context,
                        R.color.btn_right_answer_color
                    )
                )
            IPuzzleView.AnswerType.INCORRECT ->
                answersBtn[btnIndex].setBackgroundColor(getColor(rootView.context, R.color.btn_fail_answer_color))
            IPuzzleView.AnswerType.UKNOWN ->
                answersBtn[btnIndex].setBackgroundColor(getColor(rootView.context, android.R.color.transparent))
        }
    }

    override fun setNextButton(visibility: Boolean)
    {
        nextButton.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun showPuzzles(answer: Int)
    {
        for (puzzleIndex in 0 until answer)
        {
            val puzzleView = hiddenPuzzles[Random.nextInt(0, hiddenPuzzles.size)]
            puzzleView.visibility = View.VISIBLE
            hiddenPuzzles.remove(puzzleView)
        }
    }

    override fun setGameOver()
    {
        captionTextView.visibility = View.VISIBLE
        captionTextView.text = rootView.context.getString(R.string.act_puzzle_txt_clever_boy)
    }
}