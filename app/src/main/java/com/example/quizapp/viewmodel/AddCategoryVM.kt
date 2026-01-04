package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.model.CategoryModel
import com.example.quizapp.repository.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCategoryVM(val repository: QuizRepository): ViewModel() {

   // val category: LiveData<CategoryModel> =repository.insertCategory()


  private var _category= MutableLiveData<CategoryModel>()

    val categoryInsert : LiveData <CategoryModel>
        get() = _category


    fun category(cid: Int,name: String,text: String,imagePath: String): CategoryModel{
        return CategoryModel(cid=cid,name=name,text=text,imagePath=imagePath)
    }

    fun insertCategory(categoryModel: CategoryModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertCategory(categoryModel)
        }
    }
    fun getCategory(){
        viewModelScope.launch(Dispatchers.IO){
            repository.getCategory()
        }
    }


}