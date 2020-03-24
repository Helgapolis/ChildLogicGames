package ru.bazalikova.tangram.presentation

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

    @Test
    fun `should show home view after view is created`() {
        presenter = TangramPresenter()
        presenter.attachView(view)
        presenter.onViewCreated()

        Mockito.verify(view).showHome()
    }

    @Test
    fun `should show game over after tangram is built`() {
        presenter = TangramPresenter()
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onTangramBuild()

        Mockito.verify(view).setGameOver()
    }
}