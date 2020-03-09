package ru.bazalikova.childlogicgames.menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.App
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.childlogicgames.di.MenuComponent
import ru.bazalikova.childlogicgames.puzzle.PuzzleActivity
import ru.bazalikova.childlogicgames.tangram.TangramActivity
import javax.inject.Inject

class MenuActivity : AppCompatActivity(), IMenuNavigation {

    @Inject
    lateinit var presenter:MenuPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MenuComponent.create((application as App).getAppComponent()).inject(this)

        val contentView = View.inflate(this, R.layout.activity_menu, null)
        setContentView(contentView)

        val view = contentView as MenuView
        val menuModel = MenuModel(this)
        val presenter = MenuPresenter(menuModel)
        presenter.attachView(view)
        presenter.attachNavigation(this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
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