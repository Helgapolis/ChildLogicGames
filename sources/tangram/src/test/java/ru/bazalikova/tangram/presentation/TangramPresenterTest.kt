package ru.bazalikova.tangram.presentation

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class TangramPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: ITangramView

    private lateinit var presenter: TangramPresenter

    @Before
    fun setUp() {
        presenter = TangramPresenter()
    }

    @Test
    fun onViewCreated() {
        presenter.attachView(view)
        presenter.onViewCreated()

        Mockito.verify(view).showHome()
    }

    @Test
    fun onTangramBuild() {
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onTangramBuild()
        Mockito.verify(view).setGameOver()
    }
}