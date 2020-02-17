package com.example.learnistic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.learnistic.Repository.UserRepository




class UserViewModel : ViewModel(){

    private var user: MutableLiveData<List<UserRepository>>? = null

    fun getUser(): MutableLiveData<List<UserRepository>>? {
        if (user == null) {
            user = MutableLiveData()
            loadArticles()
        }
        return user
    }

    private fun loadArticles() { // do async operation to fetch articles  private void loadArticles() {
        // do async operation to fetch articles
        user
    }

}