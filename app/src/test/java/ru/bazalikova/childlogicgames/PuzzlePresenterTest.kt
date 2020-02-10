package ru.bazalikova.childlogicgames

import org.junit.Before
import ru.bazalikova.childlogicgames.puzzle.*

annotation class Mock

class PuzzlePresenterTest
{
    @Mock
    lateinit var view: IPuzzleView

    @Mock
    lateinit var repository: IPuzzleRepository

    @Mock
    lateinit var navigation: IPuzzleNavigation

    lateinit var presenter: IPuzzlePresenter

    @Before
    fun setUp()
    {
        presenter = PuzzlePresenter(view, repository, navigation)
    }

    /*@Test
    fun*/
}