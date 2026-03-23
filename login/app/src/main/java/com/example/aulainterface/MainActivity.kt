package com.example.aulainterface

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import android.view.View
import com.example.aulainterface.databinding.ActivityMainBinding
import com.example.aulainterface.viewModel.loginViewModel
import kotlin.getValue
import android.text.InputType
import androidx.core.widget.doOnTextChanged
import com.example.aulainterface.model.User


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: loginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        setupListeners()
        binding.BtCadastrar.setOnClickListener {
            GoCadastrarScreen(it)
        }
        binding.BtnEsqueceuSenha.setOnClickListener {
            GoPasswordScreen(it)
        }
        val campos = listOf(
            binding.UsuarioEditText,
            binding.SenhaEditText
        )
        campos.forEach { campo ->
            campo.editText?.doOnTextChanged { _, _, _, _ ->
                campo.error = null
                campo.isErrorEnabled = false
            }
        }

    }
    private fun setupObservers(){
        viewModel.loginResult.observe(this) {
            success -> success?.let{
            if (success) {
                val intent = Intent(this, LogedActivity::class.java)
                intent.putExtra("username", binding.UsuarioEditText.editText?.text.toString())
                startActivity(intent)

            } else {
                binding.TextLoginError.visibility = View.VISIBLE
            }
        }
}
}

    private fun setupListeners() {
        binding.BtnEntrar.setOnClickListener {
            val username = binding.UsuarioEditText.editText?.text.toString()
            val password = binding.SenhaEditText.editText?.text.toString()
            if (validarCampos(User(username, password))){
                viewModel.login(username, password)
            }
        }

    }
    fun GoCadastrarScreen(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
    fun GoPasswordScreen(view: View) {
        val intent = Intent(this, PasswordActivity::class.java)
        startActivity(intent)
    }
    private fun validarCampos(user: User): Boolean {
        if (user.username.isEmpty()) {
            binding.UsuarioEditText.error = "O nome de usuário é obrigatório"
            binding.UsuarioEditText.requestFocus()
            return false
        } else if (user.password.isEmpty()) {
            binding.SenhaEditText.error = "A senha é obrigatória"
            binding.SenhaEditText.requestFocus()
            return false
        }
        return true
    }
}


