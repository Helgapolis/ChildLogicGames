package ru.bazalikova.childlogicgames

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ru.bazalikova.childlogicgames.puzzle.PuzzleActivity

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        val button = findViewById<Button>(R.id.act_main_count_btn)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, PuzzleActivity::class.java)
            startActivity(intent)
        }
    }
}