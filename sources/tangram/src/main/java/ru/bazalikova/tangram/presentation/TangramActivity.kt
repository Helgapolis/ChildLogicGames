package ru.bazalikova.tangram.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.tangram.ITangramMediator
import ru.bazalikova.tangram.R
import ru.bazalikova.tangram.di.TangramComponent
import javax.inject.Inject

class TangramActivity : AppCompatActivity(), ITangramMediator {
    @Inject
    lateinit var presenter: TangramPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TangramComponent.create().inject(this)

        val contentView = View.inflate(this, R.layout.tangram_view, null)
        setContentView(contentView)

        val view = contentView as TangramView
        presenter.attachView(view)
        presenter.attachMediator(this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }
}