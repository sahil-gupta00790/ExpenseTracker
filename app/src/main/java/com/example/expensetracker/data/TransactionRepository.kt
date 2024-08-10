package com.example.expensetracker.data

class TransactionRepository(private val dao:TransactionDao) {
    val getTransaction=dao.getAllTransaction()

    suspend fun insertTransaction(transaction: Transaction){
        dao.insertTransaction(transaction)
    }
    suspend fun updateTransaction(transaction: Transaction){
        dao.updateTransaction(transaction)
    }
    suspend fun deleteTransaction(transaction: Transaction){
        dao.deleteTransaction(transaction)
    }
    suspend fun deleteAllTransaction(){
        dao.deleteAll()
    }
}