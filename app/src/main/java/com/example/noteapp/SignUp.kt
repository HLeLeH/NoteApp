package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.noteapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var DbRef: DatabaseReference
    private lateinit var login: TextView

    // create Firebase authentication object
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialising auth object
        mAuth = FirebaseAuth.getInstance()

        // View Bindings
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        login = findViewById(R.id.btnSSigned)

        login.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPass.text.toString()
            val confpass = etPass.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confpass)) {
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(applicationContext,"Chưa nhập Email", Toast.LENGTH_SHORT).show()
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(applicationContext,"Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show()
                }
                if (TextUtils.isEmpty(confpass)){
                    Toast.makeText(applicationContext,"Chưa nhập mật khẩu", Toast.LENGTH_SHORT).show()
                }
            }
             else {
                signup(email,pass)
            }


        }
    }
    private fun signup(email: String, password: String) {
        //tao nguoi dung moi
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // code chuyen sang trang chu

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Bi loi", Toast.LENGTH_SHORT).show()
                }
            }

    }


}