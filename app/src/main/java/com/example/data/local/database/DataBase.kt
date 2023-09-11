package com.example.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.models.CharacterDbModel

@Database(entities = [CharacterDbModel::class], version = 1, exportSchema = false)
@TypeConverters(LocationTypeConverter::class)
abstract class DataBase : RoomDatabase() {
    companion object {

        private var db: DataBase? = null
        private const val DB_NAME = "character.db"
        private val LOCK = Any()

        fun getInstance(context: Context): DataBase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    context,
                    DataBase::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }

    abstract fun characterDao(): CharacterDao
}