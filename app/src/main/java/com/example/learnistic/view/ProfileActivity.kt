package com.example.learnistic.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.learnistic.R
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.imageView1
import kotlinx.android.synthetic.main.activity_profile.*
import com.example.learnistic.view.HomeActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        mAuth = FirebaseAuth.getInstance()


        var displayname = mAuth.currentUser?.displayName
        var email = mAuth.currentUser?.email.toString()
        val mPhotoUri = mAuth.currentUser?.photoUrl


        username.setText(displayname)
        emailtxt.setText(email)
        Picasso.get().load(mPhotoUri).into(imageView1)

        sign_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@ProfileActivity, loginActivity::class.java))
            finish()

            val i = Intent(applicationContext, HomeActivity::class.java)



        }

    }
}
