package com.example.quizapp.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp.model.CategoryModel
@Dao
interface CategoryDAO {

    @Insert
    suspend fun insertCategoryList(categoryModel: List<CategoryModel>)

    @Query("Select * from Category")
    fun getCategory(): LiveData<List<CategoryModel>>

    @Insert
    suspend fun insertCategory(categoryModel: CategoryModel)
}