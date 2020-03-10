package ru.bazalikova.puzzle.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.coreapi.mediator.AppWithFacade
import ru.bazalikova.puzzle.IPuzzleMediator
import ru.bazalikova.puzzle.R
import ru.bazalikova.puzzle.di.PuzzleComponent
import javax.inject.Inject

class PuzzleActivity : AppCompatActivity(),
    IPuzzleMediator {

    @Inject
    lateinit var presenter: PuzzlePresenter

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PuzzleComponent.create((application as AppWithFacade).getFacade()).inject(this)

        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_puzzle, null)
        setContentView(contentView)
        val view = contentView as PuzzleView
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