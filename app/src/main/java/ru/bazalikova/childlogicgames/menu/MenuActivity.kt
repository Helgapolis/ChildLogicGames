package ru.bazalikova.childlogicgames.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.childlogicgames.puzzle.PuzzleActivity

class MenuActivity : AppCompatActivity(), IMenuNavigation
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.act_menu,null)
        setContentView(contentView)

        val view = contentView as MenuView
        val repository = MenuRepository(this)
        val presenter = MenuPresenter(view, repository, this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }

    override fun openCounting()
    {
        val intent = Intent(this@MenuActivity, PuzzleActivity::class.java)
        startActivity(intent)
    }

    override fun openFifteen()
    {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openTangram()
    {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}