package ru.bazalikova.menu.presentation

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_menu.view.*
import ru.bazalikova.menu.data.MenuType

class MenuView(context: Context, attrs: AttributeSet? = null) : ConstraintLayout(context, attrs),
    IMenuView {
    private lateinit var presenter: MenuPresenter

    fun onFinishInflate(presenter: MenuPresenter) {
        this.presenter = presenter

        activityPuzzleButton.setOnClickListener{
            presenter.onMenuBtnClicked(MenuType.COUNTING)
        }

        activityPuzzleText.setOnClickListener{
            presenter.onMenuBtnClicked(MenuType.COUNTING)
        }

        activityTangramButton.setOnClickListener{
            presenter.onMenuBtnClicked(MenuType.TANGRAM)
        }

        activityTangramText.setOnClickListener{
            presenter.onMenuBtnClicked(MenuType.TANGRAM)
        }
    }
}