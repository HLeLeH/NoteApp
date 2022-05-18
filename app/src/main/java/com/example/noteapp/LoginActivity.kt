package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSigup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.etEmailAddress)
        edtPass = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSigup = findViewById(R.id.tvRedirectSignUp)

        btnSigup.setOnClickListener {
            var intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPass.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
        // nguoi dung login

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code nguoi dung login

                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Nguoi dung khong ton tai", Toast.LENGTH_SHORT).show()
                }
            }

    }
}