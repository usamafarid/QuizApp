package com.example.quizapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp.R
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.model.QuestionModel
import com.example.quizapp.model.LeaderBoardModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [LeaderBoardModel::class, QuestionModel::class, CategoryModel::class], version = 1)
@TypeConverters(Convertor::class)
abstract class QuizDB : RoomDatabase() {

    abstract fun leaderboardDao(): LeaderBoardDAO
    abstract fun questionDao(): QuestionDAO
    abstract fun categoryDao(): CategoryDAO

    companion object {
        @Volatile
        private var INSTANCE: QuizDB? = null

        fun getInstance(context: Context): QuizDB {
            // Using double-checked locking for thread safety to ensure only one instance is created.
            return INSTANCE ?: synchronized(this) {
                val instance = INSTANCE
                if (instance != null) {
                    return instance
                }
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDB::class.java,
                    "Quiz_database"
                )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // This coroutine is scheduled. It runs after the DB is built and INSTANCE is available.
                        INSTANCE ?.let {
                            CoroutineScope(Dispatchers.IO).launch {
                            }
                        }
                    }
                })
                .build()

                INSTANCE = newInstance
                newInstance
            }
        }
    }
}