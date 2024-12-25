package com.coral.fithub.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coral.fithub.MainActivity
import com.coral.fithub.R
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val hashedPassword = hashWithSHA256(password)
            println("Hashed password: $hashedPassword")

            if (validateCredentials(username, password)) {
                // Credenciales válidas, navegar a la siguiente pantalla
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Mostrar mensaje de error
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hashWithSHA256(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashBytes = digest.digest(password.toByteArray())
        return hashBytes.joinToString("") { "%02x".format(it) } // Convertir a hexadecimal
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        // Valida las credenciales contra datos locales o remotos (backend)
        // Ejemplo simple con datos quemados (no recomendado para producción)
        return username == "admin" && password == "1234"
    }
}
