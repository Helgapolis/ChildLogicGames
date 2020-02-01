package ru.bazalikova.childlogicgames.puzzle

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.INavigation
import ru.bazalikova.childlogicgames.R

class PuzzleActivity : AppCompatActivity(), INavigation
{
    private lateinit var presenter: IPuzzlePresenter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val contentView = LayoutInflater.from(this).inflate(R.layout.act_puzzle, null)
        setContentView(contentView)

        val view = PuzzleView(contentView)
        val repository = PuzzleRepository(this)
        presenter = PuzzlePresenter(view, repository, this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }

    override fun onStart()
    {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop()
    {
        super.onStop()
        presenter.onStop()
    }
}