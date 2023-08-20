package com.skynoff.mymedicapp.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.skynoff.mymedicapp.AppDatabase
import com.skynoff.mymedicapp.UserEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    fun validateUser(context: Context){

        val user = UserEntity(firstName = "Cesar2", lastName = "Smith2", isFirstTime = false)

        viewModelScope.launch {
            val db = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database-name"
            ).build()
            db.userDao().insertAll(user)
        }
    }
}