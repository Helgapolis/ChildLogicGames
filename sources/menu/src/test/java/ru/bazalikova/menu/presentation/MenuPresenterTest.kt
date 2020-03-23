package ru.bazalikova.menu.presentation

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import ru.bazalikova.menu.IMenuMediator
import ru.bazalikova.menu.data.MenuType

class MenuPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mediator: IMenuMediator

    private lateinit var presenter: MenuPresenter

    @Test
    fun `should be open counting`() {
        presenter = MenuPresenter()
        presenter.attachMediator(mediator)

        presenter.onMenuBtnClicked(MenuType.COUNTING)

        Mockito.verify(mediator).openCounting()
    }

    @Test
    fun `should be open tangram`() {
        presenter = MenuPresenter()
        presenter.attachMediator(mediator)

        presenter.onMenuBtnClicked(MenuType.TANGRAM)

        Mockito.verify(mediator).openTangram()
    }

    @Test
    fun `should be open fifteen`() {
        presenter = MenuPresenter()
        presenter.attachMediator(mediator)

        presenter.onMenuBtnClicked(MenuType.FIFTEEN)

        Mockito.verify(mediator).openFifteen()
    }
}