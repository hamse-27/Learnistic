package com.example.learnistic.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.learnistic.R
import com.example.learnistic.Repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.imageView1
import kotlinx.android.synthetic.main.activity_profile.*
import com.example.learnistic.view.HomeActivity
import com.example.learnistic.viewmodel.UserViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    lateinit var model:UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        model.getUser()?.observe(this, Observer<List<UserRepository>>{

        })

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
