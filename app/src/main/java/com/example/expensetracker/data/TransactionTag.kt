package com.example.expensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "TransactionTags",
    primaryKeys = ["TagId", "TransactionId"],
    foreignKeys = [
        ForeignKey(
            entity = Tags::class,
            parentColumns = ["Id"],
            childColumns = ["TagId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Transaction::class,
            parentColumns = ["Id"],
            childColumns = ["TransactionId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TransactionTag(
    @ColumnInfo("TagId")
    val tagId: Int,
    @ColumnInfo("TransactionId")
    val transactionId: Int

)