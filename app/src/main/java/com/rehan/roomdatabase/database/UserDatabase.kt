package com.rehan.roomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rehan.roomdatabase.dao.UserDAO
import com.rehan.roomdatabase.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)      // exportSchema means whenever database version increments by 1, the previous version of data would stored in file. Inorder to remove that previous version data from table, we use exportSchema=false. False means it will remove and True means it will retain data in table
abstract class UserDatabase: RoomDatabase() {        // Always remember Database class should be abstract

    // Accessing DAO interface
    abstract fun getDao(): UserDAO

    // With companion keyword, we create single object of database throughout application
    companion object {
        private const val DATABASE_NAME = "UserDatabase"


        // Create single instance of this database class
        @Volatile       // Whenever we close application, we want data to be close also. Similarly whenever we open application, we want data to object to be created again
        var userDatabase: UserDatabase? = null

        // Creating function for database
        fun getInstance(context: Context): UserDatabase? {  // ? denotes Type safety

            if (userDatabase == null) {
                synchronized(UserDatabase::class.java) {     // synchronized denotes single object of database throughout application
                    if (userDatabase == null) {
                        // Storing single object of database in this database class and building room database
                        userDatabase =
                            Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
                                .build()
                    }
                }
            }
            return userDatabase
        }
    }
}