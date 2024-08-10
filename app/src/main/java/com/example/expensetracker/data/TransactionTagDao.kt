package com.example.expensetracker.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionTagDao {
    @Query("SELECT * FROM TransactionTags")
    fun getALlTags(): LiveData<List<TransactionTag>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactionTag: TransactionTag)

    @Query("DELETE FROM TransactionTags WHERE TagId = :tagId AND TransactionId = :transactionId")
    suspend fun delete(tagId: Int, transactionId: Int)

    @Query("SELECT * FROM TransactionTags WHERE TagId = :tagId")
    suspend fun getTransactionsForTag(tagId: Int): List<TransactionTag>

    @Query("SELECT * FROM TransactionTags WHERE TransactionId = :transactionId")
    suspend fun getTagsForTransaction(transactionId: Int): List<TransactionTag>
}
