package ru.bazalikova.childlogicgames.tangram

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.tangram_view.view.*
import ru.bazalikova.childlogicgames.R

class TangramView
@kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr), ITangramView
{
    private lateinit var presenter: ITangramPresenter

    fun onFinishInflate(presenter: ITangramPresenter)
    {
        this.presenter = presenter

        tangramViewCancelButton.setOnClickListener{
            presenter.onCancelBtnClicked()
        }
    }

    override fun showHome()
    {
        tangramViewContainer.removeAllViews()
        val view = LayoutInflater.from(context).inflate(R.layout.house_view, null)
        tangramViewContainer.addView(view)

        val houseView = view as? HouseView ?: return
        houseView.listener = presenter
        houseView.setListeners()
    }

    override fun setGameOver()
    {
        tangramViewGoodResultTextView.visibility = View.VISIBLE
        tangramViewGoodResultTextView.text = rootView.context.getString(R.string.act_tangram_txt_clever_boy)
    }
}