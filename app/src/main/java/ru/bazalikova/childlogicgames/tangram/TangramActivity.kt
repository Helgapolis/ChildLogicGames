package ru.bazalikova.childlogicgames.tangram

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.childlogicgames.di.TangramComponent
import javax.inject.Inject

class TangramActivity : AppCompatActivity(), ITangramNavigation {
    @Inject
    lateinit var presenter: TangramPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TangramComponent.create().inject(this)

        val contentView = View.inflate(this, R.layout.tangram_view, null)
        setContentView(contentView)

        val view = contentView as TangramView
        presenter.attachView(view)
        presenter.attachNavigation(this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }
}