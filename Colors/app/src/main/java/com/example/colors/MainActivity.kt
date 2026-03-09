package com.example.colors

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cor = findViewById<EditText>(R.id.varCor)
        val btn = findViewById<Button>(R.id.btnEnviar)

        btn.setOnClickListener {
            val corDigitada = cor.text.toString()

            // Cria a Intent para mudar de tela
            val intent = Intent(this, SecondActivity::class.java)

            // Passa o texto da cor para a próxima tela
            intent.putExtra("COR_ESCOLHIDA", corDigitada)

            startActivity(intent)


        }

    }
}