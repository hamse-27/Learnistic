package com.example.learnistic.Repository

import androidx.lifecycle.MutableLiveData
import com.example.learnistic.model.Users
import com.google.firebase.auth.FirebaseAuth



class UserRepository {
    private val dataset = ArrayList<Users>()
    private lateinit var mAuth: FirebaseAuth

    val userProfile: MutableLiveData<List<Users>>
        get() {
            setUsers()
            val data = MutableLiveData<List<Users>>()
            data.value = dataset
            return data
        }

    val user = mAuth.currentUser?.displayName.toString()
    val email = mAuth.currentUser?.email.toString()
    val profileImage = mAuth.currentUser?.photoUrl.toString()

    private fun setUsers(){
        dataset.add(Users(user,email,profileImage))
    }
}


