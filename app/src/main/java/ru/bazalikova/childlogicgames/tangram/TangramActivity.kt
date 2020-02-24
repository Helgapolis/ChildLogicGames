package ru.bazalikova.childlogicgames.tangram

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.R

class TangramActivity : AppCompatActivity(), ITangramNavigation {
    lateinit var presenter: TangramPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contentView = LayoutInflater.from(this).inflate(R.layout.tangram_view, null)
        setContentView(contentView)

        val view = contentView as TangramView
        presenter = TangramPresenter(view, this)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }
}