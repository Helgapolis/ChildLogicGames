package ru.bazalikova.childlogicgames

import org.junit.Before
import ru.bazalikova.childlogicgames.puzzle.*

annotation class Mock

class PuzzlePresenterTest
{
    @Mock
    lateinit var view: IPuzzleView

    @Mock
    lateinit var model: IPuzzleModel

    @Mock
    lateinit var navigation: IPuzzleNavigation

    lateinit var presenter: PuzzlePresenter

    @Before
    fun setUp()
    {
        presenter = PuzzlePresenter(view, model, navigation)
    }

    /*@Test
    fun*/
}