package com.example.learnistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnistic.Repository.UserRepository
import com.example.learnistic.model.Users
import com.google.firebase.firestore.auth.User


abstract class UserViewModel : ViewModel(){

    private lateinit var repo: UserRepository
    private var user: MutableLiveData<List<Users>>? = null


    fun getUser(): MutableLiveData<List<Users>>? {
        if (user == null) {
            user = MutableLiveData()
            loadArticles()
        }
        return user
    }

    private fun loadArticles() { // do async operation to fetch articles  private void loadArticles() {
        // do async operation to fetch articles
       repo.userProfile
    }

}