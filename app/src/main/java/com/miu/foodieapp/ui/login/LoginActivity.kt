package com.miu.foodieapp.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.miu.foodieapp.ui.main.MainActivity
import com.miu.foodieapp.databinding.ActivityLoginBinding

import com.miu.foodieapp.R
import com.miu.foodieapp.model.User

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
  // login data demo
    private val users = ArrayList<User>(listOf(
        User("admin", "Admin", "admin"),

    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("LoggedInUserPreferences", Context.MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(isLoggedIn()) {

        }

        binding.login.setOnClickListener {
            val userId = binding.username.text.toString()
            val password = binding.password.text.toString()

            val validUser = users.filter { user -> user.userId == userId && user.password == password }.firstOrNull()
            if(validUser == null)
                showLoginFailed("Sorry invalid username or password.")
            else
                handleLogin(validUser)
        }
    }

    private fun handleLogin(user: User) {
        val welcome = getString(R.string.welcome)
        val displayName = user.displayName
        Toast.makeText(this, "$welcome $displayName", Toast.LENGTH_LONG).show()
        saveLoggedInUser(user)
        redirectToMainActivity()
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_LONG).show()
    }

    private fun saveLoggedInUser(user: User){
        val editor = sharedPreferences.edit()
        editor.putString("userId", user.userId)
        editor.putString("displayName", user.displayName)
        editor.apply()
    }

    private fun redirectToMainActivity() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        finish()
    }

    private fun isLoggedIn(): Boolean {
        return !sharedPreferences.getString("userId", null).isNullOrBlank()
    }
}