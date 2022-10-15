package com.rehan.roomdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rehan.roomdatabase.models.User
import com.rehan.roomdatabase.repositories.UserRepository

class UserViewModel: ViewModel() {

    fun insert(context: Context, user: User){
        UserRepository.insert(context, user)
    }

    fun getAllUserData(context: Context) : LiveData<List<User>>? {
        return UserRepository.getAllUserData(context)
    }

}