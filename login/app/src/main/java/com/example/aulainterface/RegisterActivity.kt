package com.example.aulainterface

import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import android.util.Patterns
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aulainterface.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Correct way to use ViewBinding:
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BtnCadastrar.setOnClickListener {
            Cadastrar()
        }
        // listamos os campos para remover mensagem de erro ao mudar o texto
        val layouts = listOf(
            binding.UsuarioEditText,
            binding.EmailEditText,
            binding.SenhaEditText,
            binding.TelefoneEditText
        )

        // Para cada campo, acessamos o editText interno para detectar a digitação
        layouts.forEach { layout ->
            layout.editText?.doOnTextChanged { _, _, _, _ ->
                layout.error = null // Limpa o erro do CONTAINER
                layout.isErrorEnabled = false // Remove o espaço do erro
            }
        }
        binding.btnVoltar.setOnClickListener {
            finish()
        }
    }

    private fun validarCampos(user: UserRegister): Boolean {
        if(user.userName.isEmpty()){
            binding.UsuarioEditText.error = "O nome de usuário é obrigatório"
            binding.UsuarioEditText.requestFocus()
            return false
        }
        else if (user.email.isEmpty()) {
            binding.EmailEditText.error = "O e-mail é obrigatório"
            binding.EmailEditText.requestFocus()
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            binding.EmailEditText.error = "Digite um e-mail válido (ex: nome@email.com)"
            binding.EmailEditText.requestFocus()
            return false

        } else if (user.telefone.isEmpty()) {
            binding.TelefoneEditText.error = "O telefone é obrigatório"
            binding.TelefoneEditText.requestFocus()
            return false

        }else if (!Patterns.PHONE.matcher(user.telefone).matches()){
            binding.TelefoneEditText.error = "Digite um telefone válido (ex: 1234567890)"
            binding.TelefoneEditText.requestFocus()
            return false

        } else if (user.password.isEmpty()) {
            binding.SenhaEditText.error = "A senha é obrigatória"
            binding.SenhaEditText.requestFocus()
            return false
        }
        return true
    }

    private fun mostrarSucesso() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sucesso")
        builder.setMessage("Registro realizado com sucesso.")
        
        builder.setPositiveButton("OK") { _, _ ->
            finish()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun Cadastrar(){
        val userName = binding.UsuarioEditText.editText?.text.toString().trim()
        val email = binding.EmailEditText.editText?.text.toString().trim()
        val password = binding.SenhaEditText.editText?.text.toString().trim()
        val telefone = binding.TelefoneEditText.editText?.text.toString().trim()
        val user = UserRegister(userName, email, password, telefone)

        if (validarCampos(user)) {
            mostrarSucesso()
        }
    }

    data class UserRegister(
        val userName: String,
        val email: String,
        val password: String,
        val telefone: String)
}