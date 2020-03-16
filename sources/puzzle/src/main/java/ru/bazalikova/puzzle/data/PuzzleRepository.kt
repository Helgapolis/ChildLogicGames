package ru.bazalikova.puzzle.data

import android.content.Context
import javax.inject.Inject

class PuzzleRepository @Inject constructor(private val context: Context) : IPuzzleRepository {
    private var lessonIndex: Int = 0
    private var sampleIndex: Int = 0
    private var lessons: List<Lesson> = mutableListOf()

    override fun buildLessons()
    {
        val lessonItems = JSONHelper.importFromJSON(context)
        if (lessonItems != null)
        {
            lessons = lessonItems
        }
    }

    override fun getExpression(): String {
        return getCurrentSample().sample
    }

    override fun getAnswers(): List<Int> {
        val sampleAnswers = getCurrentSample().answers
        val answers: MutableList<Int> = mutableListOf()
        answers.addAll(sampleAnswers)

        val result: MutableList<Int> = ArrayList()

        for (index in sampleAnswers.indices) {
            val answer = answers.random()
            result.add(index, answer)
            answers.remove(answer)
        }

        return result
    }

    override fun getAnswersSize(): Int {
        return getCurrentSample().answers.size
    }

    override fun checkAnswer(answer: String): Boolean {
        return answer.equals(
            getCurrentSample().answer.toString(),
            ignoreCase = true
        )
    }

    override fun setNextStep() {
        sampleIndex += 1
    }

    override fun setNextLesson() {
        lessonIndex += 1
        sampleIndex = 0
    }

    override fun isLastSample(): Boolean {
        return sampleIndex == getCurrentLesson().samples.size - 1
    }

    override fun hasNextLesson(): Boolean {
        return lessonIndex < lessons.size - 1
    }

    override fun puzzleCount(): Int {
        return getCurrentLesson().puzzleSize
    }

    override fun puzzlePrefix(): String {
        return getCurrentLesson().prefixName
    }

    private fun getCurrentLesson(): Lesson
    {
        return lessons[lessonIndex]
    }

    private fun getCurrentSample(): Sample
    {
        return getCurrentLesson().samples[sampleIndex]
    }
}