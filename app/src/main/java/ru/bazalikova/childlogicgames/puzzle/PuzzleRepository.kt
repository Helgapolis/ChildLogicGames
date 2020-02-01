package ru.bazalikova.childlogicgames.puzzle

import android.content.Context
import ru.bazalikova.childlogicgames.R
import java.util.*
import kotlin.collections.ArrayList

class PuzzleRepository(private val context: Context): IPuzzleRepository
{
    private val termFirstArray: IntArray = context.resources.getIntArray(R.array.term_first)
    private val termSecondArray: IntArray = context.resources.getIntArray(R.array.term_second)
    private val answersSize: Int
    private val puzzleCount: Int
    private val prefixName: String

    init
    {
        answersSize = termFirstArray.size
        puzzleCount = context.resources.getInteger(R.integer.puzzle_size)
        prefixName = context.resources.getString(R.string.puzzle_prefix_name)
    }

    override fun getExpression(step: Int): String
    {
        return String.format(
            Locale.getDefault(),
            "%d + %d = ?",
            getTermFirst(step),
            getTermSecond(step)
        )
    }

    override fun getAnswers(step: Int): List<Int>
    {
        val answers: MutableList<Int> = ArrayList()

        val correctAnswer = getTermFirst(step) + getTermSecond(step)
        answers.add(correctAnswer)
        answers.add(correctAnswer + 1)
        answers.add(correctAnswer + 2)
        answers.add(if (correctAnswer > 0) correctAnswer - 1 else correctAnswer + 3)

        val result: MutableList<Int> = ArrayList()

        for (index in 0 until getAnswersSize())
        {
            val answer = answers.random()
            result.add(index, answer)
            answers.remove(answer)
        }

        return result
    }

    override fun getAnswersSize(): Int
    {
        return 4
    }

    override fun getExamplesSize(): Int
    {
        return answersSize
    }

    override fun checkAnswer(step: Int, answer: String): Boolean
    {
        return answer.equals((getTermFirst(step) + getTermSecond(step)).toString(), ignoreCase = true)
    }

    override fun puzzleCount(): Int
    {
        return puzzleCount
    }

    override fun puzzleResId(puzzleIndex: Int): Int
    {
        return context.resources.getIdentifier(prefixName + puzzleIndex.toString(), "drawable", context.packageName)
    }

    private fun getTermFirst(step: Int): Int
    {
        return termFirstArray[step]
    }

    private fun getTermSecond(step: Int): Int
    {
        return termSecondArray[step]
    }
}