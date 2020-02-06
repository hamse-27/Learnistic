package com.example.learnistic.view

//import com.facebook.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.learnistic.R
import com.example.learnistic.view.Util.toast
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class loginActivity : AppCompatActivity() {
    var googleSignInClient : GoogleSignInClient? = null
    var RC_SIGN_IN = 1
    var callbackManager : CallbackManager?=null
    var firebaseAuth : FirebaseAuth?=null


    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        overridePendingTransition(0, 0);

        setContentView(R.layout.activity_login)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )



        firebaseAuth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()
        mAuth = FirebaseAuth.getInstance()

//        login_button.setPermissions("email")
//        login_button.setOnClickListener {
//                   signIn()
//            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
//        }

        email_login_button.setOnClickListener {
            val email = user_email_login.text.toString().trim()
            val password = user_password_login.text.toString().trim()

            if (email.isEmpty()) {
                user_email_login.error = "Email Required"
                user_email_login.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                user_email_login.error = "Valid email Required"
                user_email_login.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                user_password_login.error = "6 char password required"
                user_password_login.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)

        }


        create_account_button.setOnClickListener {
            startActivity(Intent(this@loginActivity, RegisterActivity::class.java))
            finish()


        }



        sign_in_button.setOnClickListener {
            GoogleSignInOptions()
            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }



    }


    private fun loginUser(email: String, password: String) {

        progressBar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    login()
                    startActivity(Intent(this@loginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    task.exception?.message?.let {
                        toast(it)
                    }
                }
            }

    }

//    override fun onStart() {
//        super.onStart()
//        mAuth.currentUser?.let {
//            login()
//
//            val user = FirebaseAuth.getInstance().currentUser
//            if (user != null) {
//                startActivity(Intent(this@loginActivity, HomeActivity::class.java))
//                finish()
//            }
//        }
//    }

    fun Context.login(){
        val intent = Intent(this@loginActivity, HomeActivity::class.java).apply{
            flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
    }

//    private fun signIn() {
//        login_button.registerCallback(callbackManager,object: FacebookCallback<LoginResult>{
//            override fun onSuccess(result: LoginResult?) {
//                handeFacebookAccessToken(result!!.accessToken)
//            }
//
//            override fun onCancel() {
//
//            }
//
//            override fun onError(error: FacebookException?) {
//
//            }
//
//
//        }
//        )
//    }
//
//    private fun handeFacebookAccessToken(accessToken: AccessToken?) {
//        Getting credentials
//        var credential = FacebookAuthProvider.getCredential(accessToken!!.token)
//        firebaseAuth!!.signInWithCredential(credential).addOnFailureListener{ e->
//            Toast.makeText(this, e.message,Toast.LENGTH_LONG).show()
//        }.addOnSuccessListener { result->
//            getting email
//            val email = result.user?.email
//            Toast.makeText(this, "You logged with email: $email",Toast.LENGTH_LONG).show()
//
//        }
//
//    }




    //Start of google sign in code
    fun GoogleSignInOptions() {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        progressBar.visibility = View.VISIBLE
        var credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            progressBar.visibility = View.GONE
            if (task.isSuccessful) {
                startActivity(Intent(this@loginActivity, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }




    //end of google sign in code
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //facebook
//        callbackManager?.onActivityResult(requestCode,resultCode,data)
        if (requestCode == RC_SIGN_IN) {
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)
            var account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account)
            googleSignInClient?.revokeAccess()
        }
    }


}

