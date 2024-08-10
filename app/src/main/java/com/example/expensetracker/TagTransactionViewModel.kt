package com.example.expensetracker

import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.TagsRepository
import com.example.expensetracker.data.TransactionRepository
import com.example.expensetracker.data.TransactionTagRepository

class TagTransactionViewModel(
    private val tagsRepository: TagsRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionTagRepository: TransactionTagRepository
) :ViewModel() {




}