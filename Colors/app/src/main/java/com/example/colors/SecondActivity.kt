package com.example.colors

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Define o layout correto da segunda tela
        setContentView(R.layout.activity_second)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SecondActivity)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val texto = findViewById<TextView>(R.id.TextColor)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {
            finish()
        }


        // Recupera o valor enviado pela MainActivity
        val corRecebida = intent.getStringExtra("COR_ESCOLHIDA")

        if (corRecebida != null) {
            texto.text = corRecebida
            val colorResId = resources.getIdentifier(corRecebida, "color", packageName)
            if (colorResId != 0) {
                // Se o ID for diferente de 0, significa que a cor existe no seu colors.xml
                val corEncontrada = ContextCompat.getColor(this, colorResId)
                texto.setBackgroundColor(corEncontrada)
            } else {
                // 2. Se não estiver no colors.xml, tenta usar as cores padrão do sistema (ex: red, blue)
                try {

                } catch (e: Exception) {
                    // Caso não encontre de jeito nenhum
                    texto.text = "Cor '$corRecebida' não encontrada no colors.xml"
                    texto.setBackgroundColor(Color.LTGRAY)
                }
            }
        }
    }
}