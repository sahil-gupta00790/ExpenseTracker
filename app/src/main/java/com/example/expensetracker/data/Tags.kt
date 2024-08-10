package com.example.expensetracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tags")
data class Tags(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("Id")
    val id:Int ,
    @ColumnInfo("Tags")
    val tags:String

)