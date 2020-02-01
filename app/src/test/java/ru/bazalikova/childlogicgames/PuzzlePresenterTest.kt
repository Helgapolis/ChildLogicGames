package ru.bazalikova.childlogicgames

import org.junit.Before
import ru.bazalikova.childlogicgames.puzzle.IPuzzlePresenter
import ru.bazalikova.childlogicgames.puzzle.IPuzzleRepository
import ru.bazalikova.childlogicgames.puzzle.IPuzzleView
import ru.bazalikova.childlogicgames.puzzle.PuzzlePresenter

annotation class Mock

class PuzzlePresenterTest
{
    @Mock
    lateinit var view: IPuzzleView

    @Mock
    lateinit var repository: IPuzzleRepository

    @Mock
    lateinit var navigation: INavigation

    lateinit var presenter: IPuzzlePresenter

    @Before
    fun setUp()
    {
        presenter = PuzzlePresenter(view, repository, navigation)
    }

    /*@Test
    fun*/
}