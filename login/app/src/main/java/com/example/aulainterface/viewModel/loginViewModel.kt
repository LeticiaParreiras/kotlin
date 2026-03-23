package com.example.aulainterface.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aulainterface.model.User
import com.example.aulainterface.model.loginModel

class loginViewModel: ViewModel() {
    private val loginModel = loginModel()

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    fun login(username: String, password: String) {
    _loginResult.value = null
        val user = User(username, password)
        val resultLogin = loginModel.login(user)
        _loginResult.value = resultLogin
    }

}