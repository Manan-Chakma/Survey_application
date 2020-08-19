package com.assesmenttest.v2survey.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assesmenttest.v2survey.models.Form
import com.assesmenttest.v2survey.models.Survey

@Database(
    entities = [Form::class, Survey::class],
    version = 1,
    exportSchema = false
)
abstract class MyLocalDatabase : RoomDatabase() {
    abstract val formDao: FormDao

    companion object {
        @Volatile
        private var INSTANCE: MyLocalDatabase? = null

        fun getInstance(context: Context): MyLocalDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MyLocalDatabase::class.java, "v2tech.db"
            )
                .build()


    }

}
