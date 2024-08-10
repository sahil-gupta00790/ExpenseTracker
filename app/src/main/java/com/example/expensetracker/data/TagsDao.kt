package com.example.expensetracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TagsDao {
    @Insert(onConflict= OnConflictStrategy.IGNORE)
    suspend fun insertTag(tags: Tags)

    @Update
    suspend fun updateTag(tag:Tags)

    @Delete
    suspend fun deleteTag(tag: Tags)

    @Query("DELETE FROM Tags")
    suspend fun deleteAll()

    @Query("SELECT * FROM Tags")
    fun getALlTags():LiveData<List<Tags>>



}