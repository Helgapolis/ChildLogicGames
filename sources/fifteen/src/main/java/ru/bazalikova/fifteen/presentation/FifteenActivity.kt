package ru.bazalikova.fifteen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.fifteen.IFifteenMediator
import ru.bazalikova.fifteen.R
import ru.bazalikova.fifteen.di.FifteenComponent
import javax.inject.Inject

class FifteenActivity : AppCompatActivity(),
    IFifteenMediator {

    @Inject
    lateinit var presenter: FifteenPresenter

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FifteenComponent.create().inject(this)

        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_fifteen, null)
        setContentView(contentView)
        val view = contentView as FifteenView
        presenter.attachView(view)
        presenter.attachMediator(this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}