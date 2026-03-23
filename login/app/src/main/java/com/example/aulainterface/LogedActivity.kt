package com.example.aulainterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aulainterface.databinding.ActivityLogedBinding

class LogedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username") ?: "Usuário"
        binding.WelcomeText.text = getString(R.string.hello_user, username)
        binding.BtnVoltar.setOnClickListener {
            finish()
        }
    }

}
