package ru.bazalikova.childlogicgames.puzzle

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.App
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.childlogicgames.di.PuzzleComponent
import javax.inject.Inject

class PuzzleActivity : AppCompatActivity(),
    IPuzzleNavigation {

    @Inject
    lateinit var presenter: PuzzlePresenter

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PuzzleComponent.create((application as App).getAppComponent()).inject(this)

        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_puzzle, null)
        setContentView(contentView)
        val view = contentView as PuzzleView
        presenter.attachView(view)
        presenter.attachNavigation(this)

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