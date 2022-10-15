package com.rehan.roomdatabase.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.rehan.roomdatabase.database.UserDatabase
import com.rehan.roomdatabase.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository {

    // We don't want to create the object of this UserRepository class in ViewModel class. So for this we use companion keyword
    companion object{
        private var userDatabase: UserDatabase? = null

        private fun initializeDB(context: Context) : UserDatabase?{
            return UserDatabase.getInstance(context)
        }

        fun insert(context: Context, user: User){
            userDatabase = initializeDB(context)
            // performing this insertion task in background thread. So for this we use coroutines
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.insert(user)
            }
        }

        fun getAllUserData(context: Context): LiveData<List<User>>?{
            userDatabase = initializeDB(context)
            // For displaying data we don't need to use coroutines. We will simply show data from database
            return userDatabase?.getDao()?.getAllUserData()
        }
    }
}