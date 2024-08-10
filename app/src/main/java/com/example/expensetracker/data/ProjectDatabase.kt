package com.example.expensetracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tags::class, Transaction::class, TransactionTag::class], version = 1)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun tagsDao(): TagsDao
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionTagDao(): TransactionTagDao

    companion object {
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getInstance(context: Context): ProjectDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "ExpenseTracker"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
