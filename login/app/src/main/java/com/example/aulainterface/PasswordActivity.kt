package com.example.aulainterface

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

import com.example.aulainterface.databinding.ActivityPasswordBinding

class PasswordActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.BtnEnviar.setOnClickListener {
           if (validarCampo()){
               mostrarSucesso()
           }

        }
        binding.EmailEditText.editText?.doOnTextChanged { _, _, _, _ ->
            binding.EmailEditText.error = null
            binding.EmailEditText.isErrorEnabled = false
        }
        binding.BtnVoltar.setOnClickListener {
            finish()
        }
    }
    private fun validarCampo(): Boolean {
        if (binding.EmailEditText.editText?.text.toString().isEmpty()) {
            binding.EmailEditText.error = "O e-mail é obrigatório"
            binding.EmailEditText.requestFocus()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.EmailEditText.editText?.text.toString()).matches()) {
            binding.EmailEditText.error = "Digite um e-mail válido (ex: nome@email.com)"
            binding.EmailEditText.requestFocus()
            return false
        }
        return true
    }
    private fun mostrarSucesso(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sucesso")
        builder.setMessage("E-mail para resetar a senha enviado com sucesso.")

        builder.setPositiveButton("OK") { _, _ ->
            finish()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}