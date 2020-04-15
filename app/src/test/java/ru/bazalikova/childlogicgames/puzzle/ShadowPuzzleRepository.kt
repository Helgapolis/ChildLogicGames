package ru.bazalikova.childlogicgames.puzzle

import org.robolectric.annotation.Implements
import org.robolectric.annotation.RealObject
import ru.bazalikova.puzzle.data.PuzzleRepository

@Implements(PuzzleRepository::class)
class ShadowPuzzleRepository {

    @RealObject
    lateinit var puzzleRepository: PuzzleRepository

    fun getCorrectAnswer() : String? {

        val answers = puzzleRepository.getAnswers()

        for (answer in answers)
        {
            if (puzzleRepository.checkAnswer(answer.toString()))
            {
                return answer.toString()
            }
        }

        return null
    }
}