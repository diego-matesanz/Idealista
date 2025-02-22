package com.diego.matesanz.idealista

import android.app.Application
import androidx.room.Room
import com.diego.matesanz.idealista.framework.database.IdealistaDatabase

class App : Application() {

    lateinit var db: IdealistaDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, IdealistaDatabase::class.java, "idealista-db")
            .build()
    }
}
