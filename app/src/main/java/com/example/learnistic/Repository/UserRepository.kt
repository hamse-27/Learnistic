package com.example.learnistic.Repository

import com.google.firebase.auth.FirebaseAuth



class UserRepository {
val user = FirebaseAuth.getInstance().currentUser?.displayName.toString()
val email = FirebaseAuth.getInstance().currentUser?.email
val profileImage = FirebaseAuth.getInstance().currentUser?.photoUrl
}


