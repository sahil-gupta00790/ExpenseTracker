package com.example.expensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity("Transaction")
@TypeConverters(DateConverter::class)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id : Int ,
    @ColumnInfo("Amount")
    val amount : Float,
    @ColumnInfo("Time")
    val time: Date
)