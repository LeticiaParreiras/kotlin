package com.example.aulainterface.model
data class User( val username: String, val password: String)
class loginModel {
    private val userExistente = User("leticia", "123456")

    fun login(user: User): Boolean {
        return user.username == userExistente.username && user.password == userExistente.password
    }
    }
