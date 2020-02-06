package com.example.learnistic.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import com.example.learnistic.R
import com.example.learnistic.view.Util.login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.example.learnistic.view.Util.toast


class RegisterActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        mAuth = FirebaseAuth.getInstance()

        register_button.setOnClickListener {

            val email = user_email_register.text.toString().trim()
            val password = user_password_register.text.toString().trim()

            if (email.isEmpty()) {
                user_email_register.error = "Email Required"
                user_email_register.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_email_register.error = "Valid email Required"
                user_email_register.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                user_password_register.error = "6 char password required"
                user_password_register.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)

        }

        textView_register.setOnClickListener {
            startActivity(Intent(this, loginActivity::class.java))
            finish()


        }
    }
        private fun registerUser(email: String, password: String) {
            progressBar.visibility = View.VISIBLE
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    progressBar.visibility = View.GONE
                    if(task.isSuccessful){
                        login()
                    }else{
                        task.exception?.message?.let {
                            toast(it)
                        }
                    }

                }
        }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }
}
