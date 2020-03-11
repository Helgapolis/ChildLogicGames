package ru.bazalikova.menu.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.menu.IMenuMediator
import ru.bazalikova.menu.R
import ru.bazalikova.menu.di.MenuComponent
import ru.bazalikova.puzzle.presentation.PuzzleActivity
import ru.bazalikova.tangram.presentation.TangramActivity
import javax.inject.Inject

class MenuActivity : AppCompatActivity(), IMenuMediator {

    @Inject
    lateinit var presenter: MenuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MenuComponent.create().inject(this)

        val contentView = View.inflate(this, R.layout.activity_menu, null)
        setContentView(contentView)

        val view = contentView as MenuView
        presenter.attachView(view)
        presenter.attachMediator(this)

        view.onFinishInflate(presenter)
    }

    override fun openCounting() {
        val intent = Intent(this@MenuActivity, PuzzleActivity::class.java)
        startActivity(intent)
    }

    override fun openFifteen() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTangram() {
        val intent = Intent(this@MenuActivity, TangramActivity::class.java)
        startActivity(intent)
    }
}