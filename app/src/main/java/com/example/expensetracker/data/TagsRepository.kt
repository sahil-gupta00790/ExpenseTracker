package com.example.expensetracker.data

class TagsRepository(private val dao: TagsDao) {

    val tags=dao.getALlTags()

    suspend fun insert(tags: Tags){
        dao.insertTag(tags)
    }
    suspend fun delete(tags: Tags){
        dao.deleteTag(tags)
    }
    suspend fun deleteAllTags(){
        dao.deleteAll()
    }
}