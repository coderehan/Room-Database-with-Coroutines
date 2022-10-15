package com.rehan.roomdatabase.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rehan.roomdatabase.models.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)        // It means it will replace the duplicate values
    suspend fun insert(user: User)                          // We are getting data i.e. name and age from model class

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getAllUserData(): LiveData<List<User>>      // We have to return LiveData which will hold the list of user class data
}