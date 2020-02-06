package com.example.learnistic.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.GridLayout.HORIZONTAL
import android.widget.GridLayout.VERTICAL
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.learnistic.R
import com.example.learnistic.model.Courses
import com.example.learnistic.view.adapters.courses_custom_adapter
import com.example.learnistic.view.adapters.user_courses_custom_adapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.imageView1
import kotlinx.android.synthetic.main.activity_profile.*


class HomeActivity : AppCompatActivity() {

    lateinit var recyclerview:RecyclerView
    lateinit var recyclerview1:RecyclerView
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mAuth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
   lateinit var homeActivity: HomeActivity


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        recyclerview = findViewById(R.id.courses_recyclerview)
        recyclerview1 = findViewById(R.id.user_course_recyclerView)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        val recyclerView = findViewById<RecyclerView>(R.id.courses_recyclerview)
        val recyclerView1 = findViewById<RecyclerView>(R.id.user_course_recyclerView)

        recyclerView.layoutManager = GridLayoutManager(this, 2, VERTICAL, false)
        recyclerView1.layoutManager = GridLayoutManager(this,1,HORIZONTAL,false)


        val courses = ArrayList<Courses>()

        courses.add(Courses("programming"))
        courses.add(Courses("data structures"))
        courses.add(Courses("database"))
        courses.add(Courses("software architecture"))
        courses.add(Courses("driving theory"))
        courses.add(Courses("basic english"))
        courses.add(Courses("basic maths"))
        courses.add(Courses("java"))
        courses.add(Courses("php"))
        courses.add(Courses("chemistry"))

        val adapter = courses_custom_adapter(courses)
        val adapter1= user_courses_custom_adapter(courses)

        recyclerview.adapter =adapter
        recyclerview1.adapter =adapter1

        mAuth = FirebaseAuth.getInstance()

        firebaseAuth = FirebaseAuth.getInstance()
        var email = firebaseAuth.currentUser?.email.toString()
        var uid = firebaseAuth.currentUser?.uid.toString()

        fstore = FirebaseFirestore.getInstance()

        var firstname = firebaseAuth.currentUser?.displayName



        var profileImage = firebaseAuth.currentUser?.photoUrl.toString()
//        imageView1.setImageURI(profileImage)

        val mPhotoUri = firebaseAuth.currentUser?.photoUrl
       // imageView1.setImageURI(null)
      //  imageView1.setImageURI(mPhotoUri)
        Picasso.get().load(mPhotoUri).into(imageView1)
        val email1 = hashMapOf("user_Email" to email, "firstname" to firstname)
        fstore.collection("Users").document(uid).set(email1)

        textView2.setText(email)


        imageView1.setOnClickListener {
            startActivity(Intent(this@HomeActivity, ProfileActivity::class.java))
        }

    }


}
