package com.example.expensetracker.data

class TransactionTagRepository(private val transactionTagDao: TransactionTagDao) {

    val transactionTag=transactionTagDao.getALlTags()
    suspend fun insertTransactionTag(transactionTag: TransactionTag) {
        transactionTagDao.insert(transactionTag)
    }

    suspend fun getTransactionsForTag(tagId: Int): List<TransactionTag> {
        return transactionTagDao.getTransactionsForTag(tagId)
    }

    suspend fun getTagsForTransaction(transactionId: Int): List<TransactionTag> {
        return transactionTagDao.getTagsForTransaction(transactionId)
    }
}