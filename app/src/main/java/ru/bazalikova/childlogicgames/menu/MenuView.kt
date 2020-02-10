package ru.bazalikova.childlogicgames.menu

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TableRow
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.act_menu.view.*


class MenuView(context: Context, attrs: AttributeSet? = null): ConstraintLayout(context, attrs), IMenuView
{
    private lateinit var presenter: IMenuPresenter

    fun onFinishInflate(presenter: IMenuPresenter)
    {
        this.presenter = presenter
    }

    override fun setMenuItems(
        menuItems: List<MenuItem>,
        rowCount: Int,
        columnCount: Int
    )
    {
        var menuId = 0
        for (row in 0 until rowCount)
        {
            val tableRow = TableRow(context)
            tableRow.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )
            tableRow.gravity = Gravity.CENTER_HORIZONTAL

            for (column in 0 until rowCount)
            {
                val button = Button(context)

                if (menuItems.count() <= menuId)
                {
                    break
                }

                val menuItem = menuItems.get(menuId)
                button.text = menuItem.caption

                button.setOnClickListener(object : OnClickListener
                {
                    override fun onClick(p0: View?) {
                        presenter.onMenuBtnClicked(menuItem.type)
                    }

                })

                tableRow.addView(button, column)
                menuId++
            }

            actMenuTableLayout.addView(tableRow, row)
        }
    }
}